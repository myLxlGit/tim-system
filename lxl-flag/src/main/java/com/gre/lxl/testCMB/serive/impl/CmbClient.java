package com.gre.lxl.testCMB.serive.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gre.lxl.common.exception.CustomException;
import com.gre.lxl.testCMB.common.FunCodeType;
import com.gre.lxl.testCMB.common.SignatureBody;
import com.gre.lxl.testCMB.config.CmbConfig;
import com.gre.lxl.testCMB.request.HeadReq;
import com.gre.lxl.testCMB.request.RootMidNodeReq;
import com.gre.lxl.testCMB.request.RootReq;
import com.gre.lxl.testCMB.response.HeadResp;
import com.gre.lxl.testCMB.response.RootMidNodeResp;
import com.gre.lxl.testCMB.response.RootResp;
import com.gre.lxl.testCMB.smDemo.DCCryptor;
import com.gre.lxl.testCMB.smDemo.DCHelper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.rmi.server.UID;
import java.security.Security;
import java.util.Base64;
import java.util.HashMap;

/**
 * @author lxl
 * @date 2022/5/27 14:25
 */
@Slf4j
public class CmbClient {

    static {
        // 引入BC库
        Security.addProvider(new BouncyCastleProvider());
    }

    // 银行服务地址
    private static final String URL = "http://cdctest.cmburl.cn/cdcserver/api/v2";
    // 银行公钥
    private static final String bankpubkey = "BNsIe9U0x8IeSe4h/dxUzVEz9pie0hDSfMRINRXc7s1UIXfkExnYECF4QqJ2SnHxLv3z/99gsfDQrQ6dzN5lZj0=";
    //用户私钥
    private static final String privkey = "NBtl7WnuUtA2v5FaebEkU0/Jj1IodLGT6lQqwkzmd2E=";
    // 用户的对称密钥
    private static final String sm4key = "VuAzSWQhsoNqzn0K";

//    private static final String URL = CmbConfig.url;
//    private static final String bankpubkey = CmbConfig.bankPubKey;
//    private static final String privkey = CmbConfig.privateKey;
//    private static final String sm4key = CmbConfig.sm4key;
//    private static final String UID = CmbConfig.uid;
//    private static final String ALG_SM = CmbConfig.sm;

    private static final Base64.Encoder encoder = Base64.getEncoder();
    private static final Base64.Decoder decoder = Base64.getDecoder();

    private static final String ALG_SM = "SM"; // 采用国密算法

    public static String UID = "N002987421"; // 测试的用户编号 N002987459  N003261207
    public static String FUNCODE = "ONB2DCOP"; // 测试的请求名 （查询信用证 ONB2DCQL）

    /**
     * 根据body和功能代码不同调用招商银行接口
     *
     * @param bodyObj boby
     * @param funCode 功能编码
     * @return 响应体
     */
    public static RootResp postToCMB(Object bodyObj, String funCode) {
        String res = null;
        RootResp rootResp = new RootResp();
        try {
            log.info("调用招商银行" + FunCodeType.getFunCodeName(funCode) + "接口，参数 = {}", bodyObj);
            HeadReq headReq = new HeadReq();
            //功能编码 接口名称
            headReq.setFuncode(funCode);
            //todo 配置文件获取 用户id 用户id
            headReq.setUserid(UID);
            //每个请求唯一，前17位必须满足日期格式yyyyMMddHHmmssSSS（年月日时分秒毫秒）后面的自己定义，长度18-51位
            headReq.setReqid(DCHelper.getTime() + "0000001");
            //签名体
            //signature内容，包含sigtim和sigdat两个KEY，sigtim为当前时间（年月日时分秒，24小时制），服务器验签时会和当前时间比较，如果前后相差一个小时会报错，sigdat先预填充"__signature_sigdat__"，
            SignatureBody.SignatureBodyBuilder signatureBodyBuilder = SignatureBody.builder();
            SignatureBody signInit = signatureBodyBuilder.sigdat("__signature_sigdat__")
                    .sigtim(DCHelper.getTime())
                    .build();
            ObjectMapper objectMapper = new ObjectMapper();
            //节点
            RootMidNodeReq rootMidReqV1 = new RootMidNodeReq();
            rootMidReqV1.setBody(bodyObj);
            rootMidReqV1.setHead(headReq);
            //根部
            RootReq obj = new RootReq();
            obj.setRequest(rootMidReqV1);
            obj.setSignature(signInit);

            //对上面请求报文的内容，对KEY按ASSIIC码排序后去掉报文字段中的空格和换行得到待签名字符串
            String source = objectMapper.writeValueAsString(obj);
            log.info("签名原文: {}", source);

            byte[] signatureByte = DCCryptor.CMBSM2SignWithSM3(getID_IV(), decoder.decode(privkey), source.getBytes(StandardCharsets.UTF_8));
            String sigdatSuccess = new String(encoder.encode(signatureByte));
//            log.info("签名结果: {}", sigdatSuccess);
            //不知道原来的会不会被改变obj中的signature
            signInit.setSigdat(sigdatSuccess);
            // SM4-CBC加密
            String plainTxt = objectMapper.writeValueAsString(obj);
//            log.info("加密前req: {}", plainTxt);
            byte[] enInput = DCCryptor.CMBSM4EncryptWithCBC(sm4key.getBytes(), getID_IV(), plainTxt.getBytes(StandardCharsets.UTF_8));
            String reqEnd = new String(encoder.encode(enInput));
//            log.info("加密后req: {}", reqEnd);
            //发送请求
            HashMap<String, String> map = new HashMap<>();
            map.put("UID", UID);
            map.put("FUNCODE", funCode);
            map.put("ALG", ALG_SM);
            map.put("DATA", URLEncoder.encode(reqEnd, "utf-8"));
            //响应
            res = DCHelper.doPostForm(URL, map); //银行demo
            log.info("res响应: {}", res);
            //解密请求
            String resPlain = new String(DCCryptor.CMBSM4DecryptWithCBC(sm4key.getBytes(), getID_IV(), decoder.decode(res)), StandardCharsets.UTF_8);
            log.info("res解密: {}", resPlain);
            // 验签 （老版）
//            JSONObject resObj = JSONObject.parseObject(resPlain);
//            JSONObject resSignObj = (JSONObject) resObj.get("signature");
//            String resSigdat = resSignObj.get("sigdat").toString();
//            resSignObj.put("sigdat", "__signature_sigdat__");
//            resObj.put("signature", resSignObj);
//            String resSignSource = DCHelper.serialJsonOrdered(resObj);
            //验签 （最新版）
            rootResp = objectMapper.readValue(resPlain, RootResp.class);
            SignatureBody signatureResp = rootResp.getSignature();
            //验签签名值
            String resSigdat = signatureResp.getSigdat();
            signatureResp.setSigdat("__signature_sigdat__");
            //验签原文
            String resSignSource = objectMapper.writeValueAsString(rootResp);
//            log.info("验签原文: {}", resSignSource);
//            log.info("验签签名值: {}", resSigdat);
            boolean verify = DCCryptor.CMBSM2VerifyWithSM3(getID_IV(), decoder.decode(bankpubkey), resSignSource.getBytes(StandardCharsets.UTF_8), decoder.decode(resSigdat));
            log.info("验签结果: {}", verify);
            if (verify) {
                return rootResp;
            }
        } catch (Exception e) {
            HeadResp headResp = new HeadResp();
            //自定义异常状态 与 银行不冲突 起因为空字段 银行返回的res直接是非汉字，非加密数据
            headResp.setResultcode("N");
            headResp.setResultmsg(res);
            RootMidNodeResp rootMidNodeResp = new RootMidNodeResp();
            rootMidNodeResp.setHead(headResp);
            rootResp.setResponse(rootMidNodeResp);
            e.printStackTrace();
        }
        return rootResp;
    }

    private static byte[] getID_IV() {
        String uid = UID; // 请替换为实际的用户UID
        String userid = uid + "0000000000000000";
        return userid.substring(0, 16).getBytes();
    }

    public static boolean isJSONValid(String jsonInString ) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(jsonInString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
