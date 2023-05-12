package com.gre.lxl.testCMB.serive.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gre.lxl.common.exception.CustomException;
import com.gre.lxl.testCMB.common.FunCodeType;
import com.gre.lxl.testCMB.common.SignatureBody;
import com.gre.lxl.testCMB.config.CmbConfig;
import com.gre.lxl.testCMB.request.*;
import com.gre.lxl.testCMB.response.*;
import com.gre.lxl.testCMB.serive.ICmbClientService;
import com.gre.lxl.testCMB.smDemo.DCCryptor;
import com.gre.lxl.testCMB.smDemo.DCHelper;
import com.gre.lxl.testCMB.smDemo.ReqIdCreator;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.gre.lxl.testCMB.common.FunCodeType.*;

/**
 * @author lxl
 * @date 2022/5/27 14:01
 */
@Service
@Slf4j
public class CmbClientServiceImpl implements ICmbClientService {

    static {
        // 引入BC库
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final Base64.Encoder encoder = Base64.getEncoder();
    private static final Base64.Decoder decoder = Base64.getDecoder();
    private static final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public List<Onb2dcqlz1> queryLcDetails(List<Onb2dcqlx1> list) {
        LcQueryBodyReq lcQueryBodyReq = new LcQueryBodyReq();
        lcQueryBodyReq.setOnb2dcqlx1(list);
        RootResp rootResp = invokeBank(lcQueryBodyReq, LC_QUERY_DETAILS);
        if (rootResp != null) {
            String resultCode = getResultCode(rootResp);
            if (SUCCESS.equals(resultCode)) {
                try {
                    String string = objectMapper.writeValueAsString(rootResp.getResponse().getBody());
                    LcQueryBodyResp lcQueryBodyResp = objectMapper.readValue(string, LcQueryBodyResp.class);
                    return lcQueryBodyResp.getOnb2dcqlz1();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                log.error("调用招商银行信用证查询失败,funCode = {}", LC_QUERY_DETAILS);
                throw new CustomException(getResultMsg(rootResp));
            }
        }
        return Collections.emptyList();
    }

    @Override
    public RootResp LcIssueInit(LcIssueInitBodyReq lcIssueInitBodyReq) {
        return invokeBank(lcIssueInitBodyReq, LC_ISSUE_INIT);
    }

    @Override
    public List<Onb2dcmdz1> queryLcBusMode(List<Onb2dcmdx1> list) {
        LcBusModQueryBodyReq lcBusModQueryBodyReq = new LcBusModQueryBodyReq();
        lcBusModQueryBodyReq.setOnb2dcmdx1(list);
        RootResp rootResp = invokeBank(lcBusModQueryBodyReq, LC_BUS_MOD);
        if (rootResp != null) {
            String resultCode = getResultCode(rootResp);
            if (SUCCESS.equals(resultCode)) {
                try {
                    String string = objectMapper.writeValueAsString(rootResp.getResponse().getBody());
                    LcBusModQueryBodyResp lcBusModQueryBodyResp = objectMapper.readValue(string, LcBusModQueryBodyResp.class);
                    return lcBusModQueryBodyResp.getOnb2dcmdz1();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                log.error("调用招商银行模式列表查询失败,funCode = {},参数 = {}", LC_BUS_MOD, list);
                throw new CustomException(getResultMsg(rootResp));
            }
        }
        return Collections.emptyList();
    }

    @Override
    public List<Onb2dcqtz1> queryLcAgreementNumber(List<Onb2dcqtx1> list) {
        LcAgreementNumberQueryBodyReq lcAgreementNumberQueryBodyReq = new LcAgreementNumberQueryBodyReq();
        lcAgreementNumberQueryBodyReq.setOnb2dcqtx1(list);
        RootResp rootResp = invokeBank(lcAgreementNumberQueryBodyReq, LC_AGREEMENT_NUMBER);
        if (rootResp != null) {
            String resultCode = getResultCode(rootResp);
            if (SUCCESS.equals(resultCode)) {
                try {
                    String string = objectMapper.writeValueAsString(rootResp.getResponse().getBody());
                    LcAgreementNumberQueryBodyResp lcBusModQueryBodyResp = objectMapper.readValue(string, LcAgreementNumberQueryBodyResp.class);
                    return lcBusModQueryBodyResp.getOnb2dcqtz1();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                log.error("调用招商银行协议编号列表查询失败,funCode = {},参数 = {}", LC_AGREEMENT_NUMBER, list);
                throw new CustomException(getResultMsg(rootResp));
            }
        }
        return Collections.emptyList();
    }

    private String getResultCode(RootResp rootResp) {
        return rootResp.getResponse().getHead().getResultcode();
    }

    private String getResultMsg(RootResp rootResp) {
        return rootResp.getResponse().getHead().getResultmsg();
    }

    /**
     * 根据body和功能代码不同调用招商银行接口
     *
     * @param bodyObj boby
     * @param funCode 功能编码
     * @return 响应体
     */
    public static RootResp invokeBank(Object bodyObj, String funCode) {
        String res = null;
        RootResp rootResp = new RootResp();
        try {
            log.info("调用招商银行" + FunCodeType.getFunCodeName(funCode) + "接口，参数 = {}", bodyObj);
            HeadReq headReq = new HeadReq();
            //功能编码 接口名称
            headReq.setFuncode(funCode);
            //todo 配置文件获取 用户id 用户id
            headReq.setUserid(CmbConfig.uid);
            //每个请求唯一，前17位必须满足日期格式yyyyMMddHHmmssSSS（年月日时分秒毫秒）后面的自己定义，长度18-51位
            headReq.setReqid(ReqIdCreator.getReqId(21));
            //签名体
            //signature内容，包含sigtim和sigdat两个KEY，sigtim为当前时间（年月日时分秒，24小时制），服务器验签时会和当前时间比较，如果前后相差一个小时会报错，sigdat先预填充"__signature_sigdat__"，
            SignatureBody.SignatureBodyBuilder signatureBodyBuilder = SignatureBody.builder();
            SignatureBody signInit = signatureBodyBuilder.sigdat("__signature_sigdat__")
                    .sigtim(DCHelper.getTime())
                    .build();
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

            byte[] signatureByte = DCCryptor.CMBSM2SignWithSM3(getID_IV(), decoder.decode(CmbConfig.privateKey), source.getBytes(StandardCharsets.UTF_8));
            String sigdatSuccess = new String(encoder.encode(signatureByte));
//            log.info("签名结果: {}", sigdatSuccess);
            //不知道原来的会不会被改变obj中的signature
            signInit.setSigdat(sigdatSuccess);
            // SM4-CBC加密
            String plainTxt = objectMapper.writeValueAsString(obj);
//            log.info("加密前req: {}", plainTxt);
            byte[] enInput = DCCryptor.CMBSM4EncryptWithCBC(CmbConfig.sm4key.getBytes(), getID_IV(), plainTxt.getBytes(StandardCharsets.UTF_8));
            String reqEnd = new String(encoder.encode(enInput));
//            log.info("加密后req: {}", reqEnd);
            //发送请求
            HashMap<String, String> map = new HashMap<>();
            map.put("UID", CmbConfig.uid);
            map.put("FUNCODE", funCode);
            map.put("ALG", CmbConfig.sm);
            map.put("DATA", URLEncoder.encode(reqEnd, "utf-8"));
            //响应
            res = DCHelper.doPostForm(CmbConfig.url, map); //银行demo
            log.info("res响应: {}", res);
            //解密请求
            String resPlain = new String(DCCryptor.CMBSM4DecryptWithCBC(CmbConfig.sm4key.getBytes(), getID_IV(), decoder.decode(res)), StandardCharsets.UTF_8);
            log.info("res解密: {}", resPlain);
            //验签 （最新版）
            rootResp = objectMapper.readValue(resPlain, RootResp.class);
            SignatureBody signatureResp = rootResp.getSignature();
            //验签签名值
            String resSigdat = signatureResp.getSigdat();
            signatureResp.setSigdat("__signature_sigdat__");
            //验签原文
            String resSignSource = objectMapper.writeValueAsString(rootResp);
            boolean verify = DCCryptor.CMBSM2VerifyWithSM3(getID_IV(), decoder.decode(CmbConfig.bankPubKey), resSignSource.getBytes(StandardCharsets.UTF_8), decoder.decode(resSigdat));
            log.info("验签结果: {}", verify);
            if (verify) {
                return rootResp;
            }
        } catch (Exception e) {
            log.error("错误信息：{}", res);
            HeadResp headResp = new HeadResp();
            //自定义异常状态 与 银行不冲突 起因为空字段 银行返回的res直接是非汉字，非加密数据
            headResp.setResultcode("N");
            headResp.setResultmsg(res);
            RootMidNodeResp rootMidNodeResp = new RootMidNodeResp();
            rootMidNodeResp.setHead(headResp);
            rootResp.setResponse(rootMidNodeResp);
//            e.printStackTrace();
        }
        return rootResp;
    }

    private static byte[] getID_IV() {
        String uid = CmbConfig.uid; // 请替换为实际的用户UID
        String userid = uid + "0000000000000000";
        return userid.substring(0, 16).getBytes();
    }

    public static boolean isJSONValid(String jsonInString) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(jsonInString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
