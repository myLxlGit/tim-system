package com.gre.lxl.utils;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author lxl
 * @date 2022/5/25 18:40
 */
public class sm4Demo {
    //key必须是16字节，即128位
    final static String key = "sm4demo123456789";

    //指明加密算法和秘钥
    static SymmetricCrypto sm4 = new SymmetricCrypto("SM4/ECB/PKCS5Padding", key.getBytes());

    //加密为16进制，也可以加密成base64/字节数组
    public static String encryptSm4(String plaintext) {
        return sm4.encryptHex(plaintext);
    }

    //解密
    public static String decryptSm4(String ciphertext) {
        return sm4.decryptStr(ciphertext);
    }

    public static void main(String[] args) throws Exception {
//        String content = "hello sm4";
//        String plain = encryptSm4(content);
//        String cipher = decryptSm4(plain);
//        System.out.println(plain + "\n" + cipher);

        String sm4Key = "2222222222222222";
        String plainText = "{\"name\":\"静瑶\",\"描述\":\"测试SM4加密解密\"}";
        String cipherText = null;

//        System.out.println("----------------------- 获取SM4秘钥 -------------------------");
//        sm4Key = SM4Util.generateSm4Key();
//        System.out.println("秘钥：" + sm4Key);
//        System.out.println();
        System.out.println("----------------------- 文本加解密测试 -------------------------");
        // 文本加解密测试.
        System.out.println("明文：" + plainText);
        cipherText = SM4Util.encodeText(plainText, sm4Key);
        System.out.println("密文：" + cipherText);
        plainText = SM4Util.decodeText(cipherText, sm4Key);
        System.out.println("解密明文：" + plainText);
        System.out.println();

        SymmetricCrypto sm4 = SmUtil.sm4(sm4Key.getBytes(StandardCharsets.UTF_8));
        byte[] ciphertext = sm4.encrypt(plainText);//库加密结果
        System.out.println("库函数加密结果\t\t" + new String(HexUtil.encodeHex(ciphertext)));

//        System.out.println("----------------------- 字节数组加解密测试 -------------------------");
//        // 字节数组加解密测试.
//        byte[] plainBytes = plainText.getBytes("UTF-8");
//        byte[] cipherBytes = null;
//        System.out.println("明文：" + Arrays.toString(plainBytes));
//        cipherBytes = SM4Util.encodeBytes(plainBytes, sm4Key);
//        System.out.println("密文：" + Arrays.toString(cipherBytes));
//        plainBytes = SM4Util.decodeBytes(cipherBytes, sm4Key);
//        System.out.println("解密明文：" + Arrays.toString(plainBytes));
//        System.out.println();

    }
}
