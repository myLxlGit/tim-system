package com.gre.lxl.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lxl
 * @date 2022/4/12 9:03
 */
public class SignatureUtils {
    public static final String KEY_ALGORITHM = "RSA";

    // 数字签名算法
    public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

    private static final String PUBLIC_KEY = "RSAPublicKey";

    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * RSA密钥长度默认为1024
     * 密钥长度必须是64的倍数
     * 方位在512~65536之间
     */
    private static final int KEY_SIZE = 512;


    /**
     * 签名
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     * @return 数字签名
     * @throws Exception Exception
     */
    public static byte[] sign(byte[] data, byte[] privateKey) throws Exception {
        // 转换密钥材料
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey priKey = keyFactory.generatePrivate(keySpec);
        // 实例化
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);
        return signature.sign();
    }

    /**
     * 校验
     *
     * @param data      待校验数据
     * @param publicKey 公钥
     * @param sign      数字签名
     * @return 校验成功返回true, 失败返回false
     * @throws Exception Exception
     */
    public static boolean verify(byte[] data, byte[] publicKey, byte[] sign) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
        KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey pubKey = factory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data);
        return signature.verify(sign);
    }

    /**
     * 获取私钥
     *
     * @param keyMap keyMap
     * @return 私钥
     */
    public static byte[] getPrivateKey(Map<String, Object> keyMap) {
        return ((Key) keyMap.get(PRIVATE_KEY)).getEncoded();
    }

    /**
     * 获取公钥
     *
     * @param keyMap keyMap
     * @return 公钥
     */
    public static byte[] getPublicKey(Map<String, Object> keyMap) {
        return ((Key) keyMap.get(PUBLIC_KEY)).getEncoded();
    }

    /**
     * 初始化密钥
     *
     * @return map
     * @throws Exception Exception
     */
    public static Map<String, Object> initKey() throws Exception {
        // 实例化密钥对生成器
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        // 初始化密钥对生成器
        keyPairGen.initialize(KEY_SIZE);
        // 生成密钥对
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 公钥
        RSAPublicKey pubKey = (RSAPublicKey) keyPair.getPublic();
        // 私钥
        RSAPrivateKey priKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<>();
        keyMap.put(PUBLIC_KEY, pubKey);
        keyMap.put(PRIVATE_KEY, priKey);
        return keyMap;
    }

    /**
     * 私钥签名
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     * @return 十六进制签名字符串
     * @throws Exception Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        return Hex.encodeHexString(sign(data, getKey(privateKey)));
    }

    /**
     * 验证签名
     *
     * @param data      待验证数据
     * @param publicKey 公钥
     * @param sign      签名数据
     * @return true or false
     * @throws Exception Exception
     */
    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
        return verify(data, getKey(publicKey), Hex.decodeHex(sign.toCharArray()));
    }


    /**
     * 获取私钥
     *
     * @param keyMap keyMap
     * @return 十六进制私钥
     */
    public static String getPrivateKeyStr(Map<String, Object> keyMap) {
        return Hex.encodeHexString(getPrivateKey(keyMap));
    }

    /**
     * 获取公钥
     *
     * @param keyMap keyMap
     * @return 十六进制公钥
     */
    public static String getPublicKeyStr(Map<String, Object> keyMap) {
        return Hex.encodeHexString(getPublicKey(keyMap));
    }

    /**
     * 获取密钥
     *
     * @param key 密钥
     * @return 密钥
     */
    public static byte[] getKey(String key) throws DecoderException {
        return Hex.decodeHex(key.toCharArray());
    }

}
