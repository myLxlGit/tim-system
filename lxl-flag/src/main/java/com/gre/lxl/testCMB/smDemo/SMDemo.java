package com.gre.lxl.testCMB.smDemo;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.*;

/**
 * @version v1.0
 * @Package: CMB_SM
 * @Description: 国密免前置/SaaS对接示例，本示例仅供参考，不保证各种异常场景运行，请勿直接使用，如有错漏请联系对接人员。
 * 运行时，请使用所获取的测试资源替换 用户编号、公私钥、对称密钥、服务商编号等信息。
 * @author:
 * @date: 2022年1月20日 上午10:25:29
 * @inspector:
 * @inspectdate:
 */
public class SMDemo {

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

    private static final Base64.Encoder encoder = Base64.getEncoder();
    private static final Base64.Decoder decoder = Base64.getDecoder();

    private static final String ALG_SM = "SM"; // 采用国密算法

    public static String UID = "N002987421"; // 测试的用户编号 N002987459  N003261207
    public static String FUNCODE = "ONB2DCOP"; // 测试的请求名 （查询信用证 ONB2DCQL）

    public static void main(String[] args) throws Exception {
        // 引入BC库
        Security.addProvider(new BouncyCastleProvider());

        //组织发送报文
        JsonObject obj = new JsonObject();
        JsonObject req = new JsonObject();
        JsonObject body = new JsonObject();
        JsonObject head = new JsonObject();

        head.addProperty("funcode", FUNCODE);
        head.addProperty("userid", UID);
        head.addProperty("reqid", DCHelper.getTime() + "0000001");

        //字段名
//        body.addProperty("buscod", "N02030");
//        body.addProperty("TEST", "中文");
//        body.addProperty("TEST2", "!@#$%^&*()\\\\///");
//        body.addProperty("TEST3", 12345);
        //数组
        //查询
//        JsonArray array = new JsonArray();
//        JsonObject item = new JsonObject();
//        item.addProperty("ilcIdx", "1234567");
//        array.add(item);
//
//        body.add("onb2dcqlx1", array);
        //经办
        JsonObject item = new JsonObject();
        item.addProperty("conamt","50");
        item.addProperty("conccy","10");
        item.addProperty("connbr","2016222050");
        JsonArray array = new JsonArray();
        array.add(item);
        body.add("onbdcconx1",array);

        JsonObject item1 = new JsonObject();
        item1.addProperty("temtxt","123");
        item1.addProperty("temtyp","G");
        JsonObject item2 = new JsonObject();
        item2.addProperty("temtxt","123");
        item2.addProperty("temtyp","D");
        JsonArray array1 = new JsonArray();
        array1.add(item1);
        array1.add(item2);
        body.add("onbdcgodx1",array1);


        JsonObject item3 = new JsonObject();
        item3.addProperty("appadd","12");
        item3.addProperty("benadd","adddd");
        item3.addProperty("bencnr","CN");
        item3.addProperty("bennam","12");
        item3.addProperty("busmod","0000462162");
        item3.addProperty("cfmins","Y");
        item3.addProperty("cfmprt","BEN");
        item3.addProperty("cfmrim","Y");
        item3.addProperty("cltnbr","7559360487");
        item3.addProperty("ctanam","苏美达");
        item3.addProperty("ctatel","123456");
        item3.addProperty("dctacc","755936048710801");
        item3.addProperty("detmod","FULL");
        item3.addProperty("draact","N");
        item3.addProperty("expdat","20230530");
        item3.addProperty("extidx","20220525SUME00001");
        item3.addProperty("feetem","fee");
        item3.addProperty("fomtrs","N");
//        item3.addProperty("librat","1");
        item3.addProperty("opnamt","55");
        item3.addProperty("opncur","CNY");
        item3.addProperty("oprtyp","C");
        item3.addProperty("patsub","00002");
        item3.addProperty("paydrt","ST");
        item3.addProperty("paytyp","NG");
        item3.addProperty("plaprt","sss");
        item3.addProperty("ptlshp","A");
        item3.addProperty("reptyp","PWV");
        item3.addProperty("spebnk","xxx");
        item3.addProperty("trsbrn","123456");
        item3.addProperty("trscod","1231");
        item3.addProperty("trstem","A");



        JsonArray array2 = new JsonArray();
        array2.add(item3);
        body.add("onbdcmstx1",array2);

        //通用部分
        req.add("head", head);
        req.add("body", body);
        obj.add("request", req);

        // 请求发送接收
        doProcess(obj);
    }

    private static void doProcess(JsonObject jObject) throws Exception {
        JsonObject object = new JsonObject();
        // 签名
        object.addProperty("sigdat", "__signature_sigdat__");
        object.addProperty("sigtim", DCHelper.getTime());
        jObject.add("signature", object);

        String source = DCHelper.serialJsonOrdered(jObject);
        System.out.println("签名原文: " + source);


        byte[] signature1 = DCCryptor.CMBSM2SignWithSM3(getID_IV(), decoder.decode(privkey), source.getBytes(StandardCharsets.UTF_8));
        String sigdat1 = new String(encoder.encode(signature1));
        System.out.println("签名结果: " + sigdat1);
        object.addProperty("sigdat", sigdat1);

        // SM4-CBC加密
        String plaintxt = jObject.toString();
        System.out.println("加密前req:  " + plaintxt);
        byte[] enInput = DCCryptor.CMBSM4EncryptWithCBC(sm4key.getBytes(), getID_IV(), plaintxt.getBytes(StandardCharsets.UTF_8));
        String req = new String(encoder.encode(enInput));
        System.out.println("加密后req:  " + req);

        // 发送请求
        HashMap<String, String> map = new HashMap<>();
        map.put("UID", UID);
        map.put("FUNCODE", FUNCODE);
        map.put("ALG", ALG_SM);
        map.put("DATA", URLEncoder.encode(req, "utf-8"));
        String res = DCHelper.doPostForm(URL, map);
        System.out.println("res:  " + res);

        // 解密请求
        String resplain = new String(DCCryptor.CMBSM4DecryptWithCBC(sm4key.getBytes(), getID_IV(), decoder.decode(res)), StandardCharsets.UTF_8);
        System.out.println("res decrypt: " + resplain);

        // 验签
        JsonObject object2 = new GsonBuilder().create().fromJson(resplain, JsonObject.class);
        JsonObject object3 = object2.getAsJsonObject("signature");
        String resSign = object3.get("sigdat").getAsString();
        object3.addProperty("sigdat", "__signature_sigdat__");
        object2.add("signature", object3);
        String resSignSource = DCHelper.serialJsonOrdered(object2);
        System.out.println("验签原文: " + resSignSource);
        System.out.println("验签签名值: " + resSign);
        boolean verify = DCCryptor.CMBSM2VerifyWithSM3(getID_IV(), decoder.decode(bankpubkey), resSignSource.getBytes(StandardCharsets.UTF_8), decoder.decode(resSign));
        System.out.println("验签结果: " + verify);
    }

    private static byte[] getID_IV() {
        String uid = SMDemo.UID; // 请替换为实际的用户UID
        String userid = uid + "0000000000000000";
        return userid.substring(0, 16).getBytes();
    }


    /**
     * 通过JSONObject 和 功能编码传递 map中 必传 request
     *
     * @param bodyMap    body
     * @param funCode 功能编码
     */
//    public static void postToCMB(Map<String,Object> bodyMap, String funCode) {
//        try {
//            HeadReq headReq = new HeadReq();
//            //功能编码 接口名称
//            headReq.setFuncode(funCode);
//            //todo 配置文件获取 用户id 用户id
//            headReq.setUserid(UID);
//            //每个请求唯一，前17位必须满足日期格式yyyyMMddHHmmssSSS（年月日时分秒毫秒）后面的自己定义，长度18-51位
//            headReq.setReqid(DCHelper.getTime() + "0000001");
//            //签名体
//            SignatureBody.SignatureBodyBuilder signatureBodyBuilder = SignatureBody.builder();
//            SignatureBody signInit = signatureBodyBuilder.sigdat("__signature_sigdat__")
//                    .sigtim(DCHelper.getTime())
//                    .build();
//            //signature内容，包含sigtim和sigdat两个KEY，sigtim为当前时间（年月日时分秒，24小时制），服务器验签时会和当前时间比较，如果前后相差一个小时会报错，sigdat先预填充"__signature_sigdat__"，
//            //初始化
//            JSONObject obj = new JSONObject();
//            //请求体
//            JSONObject req = new JSONObject();
//            JSONObject bodyReq = JSONObject.parseObject(JSON.toJSONString(bodyMap.get("body")));
//            req.put("body",bodyReq);
//            req.put("head", JSONObject.parseObject(JSON.toJSONString(headReq)));
//
//            obj.put("request", req);
//            obj.put("signature", JSONObject.parseObject(JSON.toJSONString(signInit)));
//
//            //对上面请求报文的内容，对KEY按ASSIIC码排序后去掉报文字段中的空格和换行得到待签名字符串
//            String source = DCHelper.serialJsonOrdered(obj);
//            System.out.println("签名原文: " + source);
//
//            byte[] signatureByte = DCCryptor.CMBSM2SignWithSM3(getID_IV(), decoder.decode(privkey), source.getBytes(StandardCharsets.UTF_8));
//            String sigdatSuccess = new String(encoder.encode(signatureByte));
//            System.out.println("签名结果: " + sigdatSuccess);
//            //不知道原来的会不会被改变obj中的signature
////            JSONObject signObj1 = new JSONObject(new LinkedHashMap<>());
////            signObj1.put("sigdat",sigdatSuccess);
////            signObj1.put("sigtim",signInit.getSigtim());
////            obj.put("signature", signObj1);
//            signInit.setSigdat(sigdatSuccess);
//            obj.put("signature", JSONObject.parseObject(JSON.toJSONString(signInit)));
//
//            // SM4-CBC加密
//            String plainTxt = JSON.toJSONString(obj);
//            System.out.println("加密前req:  " + plainTxt);
//            byte[] enInput = DCCryptor.CMBSM4EncryptWithCBC(sm4key.getBytes(), getID_IV(), plainTxt.getBytes(StandardCharsets.UTF_8));
//            String reqEnd = new String(encoder.encode(enInput));
//            System.out.println("加密后req:  " + reqEnd);
//            // 发送请求
//            HashMap<String, String> map = new HashMap<>();
//            map.put("UID", UID);
//            map.put("FUNCODE", funCode);
//            map.put("ALG", ALG_SM);
//            map.put("DATA", URLEncoder.encode(reqEnd, "utf-8"));
//            String res = DCHelper.doPostForm(URL, map);
//            System.out.println("res:  " + res);
//
//            // 解密请求
//            String resPlain = new String(DCCryptor.CMBSM4DecryptWithCBC(sm4key.getBytes(), getID_IV(), decoder.decode(res)), StandardCharsets.UTF_8);
//            System.out.println("res decrypt: " + resPlain);
//
//            // 验签
//            JSONObject resObj = JSONObject.parseObject(resPlain);
//            JSONObject resSignObj = (JSONObject) resObj.get("signature");
//            String resSigdat = resSignObj.get("sigdat").toString();
//            resSignObj.put("sigdat", "__signature_sigdat__");
//            resObj.put("signature", resSignObj);
//            String resSignSource = DCHelper.serialJsonOrdered(resObj);
//            System.out.println("验签原文: " + resSignSource);
//            System.out.println("验签签名值: " + resSigdat);
//            boolean verify = DCCryptor.CMBSM2VerifyWithSM3(getID_IV(), decoder.decode(bankpubkey), resSignSource.getBytes(StandardCharsets.UTF_8), decoder.decode(resSigdat));
//            System.out.println("验签结果: " + verify);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
