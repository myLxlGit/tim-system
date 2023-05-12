package com.gre.lxl.utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author lxl
 * @date 2022/4/12 10:10
 */
public class RSA {

    private static String src = "imooc security rsa";
    private static String apigwPublicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxSJrF8T/5rKB4NnwMjIxUer+ELf1PQXO2GSdZ/fvuQCclOR9tBlNWL4jFOftebeL+bvMVOJ+JHm/aSes1AN8YNIDGiFUpF6aDkSCaLynDdjK/mQTWhSNa2fO0GGO+ywOBTdYUjVjVtzJ48bbyG3NSylf1EdnBWnMpFa8qpXJXR4ELpVpMkPDC+93HBAlxEgUjhcIJlP5VdKIiudsmhE2T07qtpIQSuE5hntXP6X6GKJReCk+yek2QJITvIBq3cHPw8KDsHHCs7MaR81KI3onJRWyqFtTfVYTiKsd9EcYSxv+Gx5MOF8B/P4iJCD8uzx0FrqoB3k5OYGcz4tXs+h+9wIDAQAB";
    private static String bocomPrdKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAihC4Qj8mlFxlO54qhUOmwWTKqhTCrfzZIeWFqS+QH9BxiChja6Za8oYVhyP+kiRiWxffBTzT25DPUfaDiQVCnlhdqlfOHHZ2Gp291pKrmF/E4WxLk50zYE1d69osw20GY0EVxhpml5bOxumxughpKwPKCrcYtN7MXAeWUEpl7AzqPNUgV+KlmE7TxB9tWcP6jeSn4/PQ47BfYmi2LI25UXfaFrUSNITkePoIYVZnP6FVpsC2grTdnPeUgfaCB3f/fPjEwRPrCHXCMopEWQQGIvqZuaZkFaQAd5XYfQnRytnF8nPofuRCDOHZYV2ldb5fVfsne/PuWmKBnBghebcw+QIDAQAB";
    private static String myPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCER3xnjjYb4na9yf49wJIE/ykXuk9q4QKYJk/+LQuVUmf3HYUmVvngB2jwrMWr0x+Asg3XFyoctmZBDci3mgElQW6u1jNBUEUqbU+9oHT496D1g7KRVZZOWqaSZmFkPYf6Kr+uZgUtQEHeKiTuG3wIR2COSzuEsBvW8Q0RobHiCXXVmKpfpkB2cktwHzfu4sgTNrnvru5EDRFUMk72sMa5M9gasmY8XIVF711LWFreqVTKZPTuMTTIbZuVUd+vADWB7Y48IIEEsdIbbH8qwnTrEIvvWEJkhRYMpPvioLzqlepXZ8xNPD3Hu/SxHRRS+rQHiQvIpkqUK0JxOG+JpD8NAgMBAAECggEAczzrzjOARA6or3K3wQhwrVW1cOxon43i+rX13lNT0gR2ejwHcX04Nsl9zJ5XnvdQbIexsv6FO3pT3rypbfoGoXQ9fqcxA+dwOiP84hBVl0fzu6+98wsC5MPodYYntnI8cd6oGsDaJa/jMi64+Y6MIZI9NfmoolHiDwD1B7U8CNRAZR4XSTd22kmaF222cipgjpxrJozsJWSlwXsaw6ZEYsSraP76ZU1GiKZPKayLxQtTmHmqL/1SgVb2gBnW2FpTTdeanxjhnvE1Ikg28bfZPpA8c0jItjNpFvmyaf/3C2pKJBYKkAuyQSjMexS6anBX4G4LRfcDzrJzY12eHWrb6QKBgQC5iOg9JqxOmDCBN4I7hd3ax9bFPKQe7NO1Hs2cgyOCp3Ph/yK8/OIlGSBDHVlXPOpq80OwmOGU5AXJTBRpgDLC7gF9gphd9l+sIkHtQ5+mWld+jr7qFlW95y+H51PDk0nSg2GIkyID3t3G0NBooDM/XaXyndljBOQwMVpVBdqAWwKBgQC2hKwRCb2C6ZAssw8Cq5LWR2psFONbqAmA6aO9GgFKF0mBsyq0sstz/nE/XBURc+zOnYr72WFMMaoVkNPTxwqXNcHu4vbyepfBSWBDSJxndlMKXcld5+GZCyl9nUFLHGjYbNUsMgahaR5l9eNiMbHzKHdRNw7Trf2gLnvWlH/atwKBgHaP3pj1VbcD2js41ahj61obyktQbTC19rQPcyVJSC/+AjexaumKUJmUbOF2p9jvvH2L5v27NKyI6omwbHreyZF3tswV4HhrMMg3PBn61NLheHgPkEDW9QPd8KnQUd7xCIPWIUW9gEco7GBYoFW7ygP8PEfGR1zXi1qgrzEWBINxAoGBAIe3h6rzrzOVMVvLmbHGmt0zJXNQ5O7WuJpfIR4QJD1Pq50B+RN8j8Skwtj2Q8RCiyfoBdzX+32IwIxgsOKc8scVdiuIIVCU0Bst0KK3b3WPipf+vSlSr3H51tRNBqwvZ9bmtmyyljOX0r7GZFoz0bqZsH93jVLh4eImLneU3K1pAoGAMUCNCHIpMSd+d5ewR9VxXY1DjGHvIC98EdsjL0S7QcVrWb9mh6Br5G6zvlIJcMhIaQm3C1ETLaHG+K9XRrA+HcJo1ApeMT7MgBmIC+cG1aepZREDruNI3G8r4bDS6pHSDKk+Iu8shpEUurxZCzjBX2n5OeMJx+xDgYUq5H4Bn5I=";
    private static String lxlPrdKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCW6rZ9I4EaMLd1HsIo3ZfqTr0nzhKjIYwVe3mjcqwbVw0wdQ/35Bd7k/WL2RzTl8SWzJqd01oCPyVspV/XCfBu/+Adux38hRSJTO3CRqDsRbc6qOf+j83iyQ+Jus0cQ/ocaQRQngvroz/jS247JfY3i+Q1lddLdby7Kf4o612mizY8Mfrti2FR51vvdRhIBl2qc/NGv23RqZO39811jox/cZGGRhsq86TX/p3ikrYJ+9daV+nTntKiBN1FhibI7k6/+dJ3auCTDVdXUCKzOZ8ATOf/KlC+0z4i2RatcIUS1MWAx1bsCphIrnsY7O1uWiqxyr3x5N1e/cttZ03hmNl9AgMBAAECggEAFeuLjAuPIe23h+Rq0SK2gQrkwJZW8KjbvvcLSOlMBECVotct9ZSEsKdh/+kOHdGqdTjMsGXVagQf/HLcw9AmwwiZVdgq19XAkNzCn/hAQXEy1utXnwrmHa2Uey3NwvBAsMW0Ib2C/7vbeLkpDqBJ7fF8uSfFeKlmO00zJGBjXuAaa7UEl1M662pAO/lFwXa+rRBJ7tOyKxaZWz0OyP+KFRz+JSoUFYQcHsliJe1RDP+cxijSzNRm1fnPOmMG2360UeiqKtvZ0xBNYFaolhQgg83F3Rq6c0iKXczKzZNGZzl3yENZ81VITljO3gDoy/Q2dz5ELx5c9/IoN93UQjwGAQKBgQDGGKXm1xTUbatD6c9I6/tYJvCi+/Q9M2r5KJcs7MVPaqOdhqiRVFJXwUedsDpz+JU2hjxtJpqvMGOiSQ6KO/OMdVWbNd2zsDpqAbbHU9Yed62VchHq82Xb/riUabwWURTH+SxqKweJUw2Ro7eqg0duEJlXrTBfnMXP26okEOjJgQKBgQDDB6xzSCPtO+BXpW+LyHbqMes0IagRKJCCd8D1kgB50PoUGu7pZvZ0u48Xqpmw1Yt6guZK57gsTwAbASPN95IRNS5JEop+l02oVclPaXtCkp54hzdx3ppHNiQ0NZxIZKXZf+IUWYvLjBhXMFANguMfuGHnbYqrXKNkKGU+rtI1/QKBgC3DW/AsF9/LRhl2VjCec3414LmGQzIwQrI0slfqitqB5DJr/V01gaBMQmMnZxmLf80MzcDZfjqijyHahsVgfC8inBgzJXhxac07gCIkN/7p3uv7YjBI8msUOUfv9Ueii452/c+5CRCsYyIWFEQL9+BxbTtYAyjTas9OC8wxK9YBAoGBAK+JuZrtZIDDctIQckzDOtzh30xFusru0trLRJ8DMXBzOpNcBouHg+5fXpSzla+HFQpIxVTIKYmf9X1Y0kwxN4RLdISKrqLwNhJ8XDwgubOa2g2rzqir7fqIbZ+cXTT/ehNAulEMo/mp98KKFpOCqkDDqUMu4jAIqnucrDv2B/hVAoGBALk1VE5MFFsz2XAkF+UJR4t1SCNrkEc69lLK8JGFIbFduda3BjqU0mKQzSeTtb6QQ6TXnkzVtpg9HUjLcWmCRh166RPWb8jbXIXgva9N5Ay87XVNcdPyTCIgCy/30BaVaJa/p/Y5wJ0rSJ0vWJ21JA/FWDg9ep5AxP67dyGGwlS0";
    private static String lxlPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAluq2fSOBGjC3dR7CKN2X6k69J84SoyGMFXt5o3KsG1cNMHUP9+QXe5P1i9kc05fElsyandNaAj8lbKVf1wnwbv/gHbsd/IUUiUztwkag7EW3Oqjn/o/N4skPibrNHEP6HGkEUJ4L66M/40tuOyX2N4vkNZXXS3W8uyn+KOtdpos2PDH67YthUedb73UYSAZdqnPzRr9t0amTt/fNdY6Mf3GRhkYbKvOk1/6d4pK2CfvXWlfp057SogTdRYYmyO5Ov/nSd2rgkw1XV1AiszmfAEzn/ypQvtM+ItkWrXCFEtTFgMdW7AqYSK57GOztbloqscq98eTdXv3LbWdN4ZjZfQIDAQAB";

    public static void main(String[] args) {
//        jdkRSA();
        ssss();
    }

    private static void ssss() {
        File file = new File("F:/交通银行对接/交通银行接口-新版本SDK/在职证明.pdf");
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int n = 0;
            while (-1 != (n = inputStream.read(buffer))) {
                output.write(buffer, 0, n);
            }
            byte[] bytes = output.toByteArray();
            byte[] bytes1 = output.toByteArray();
            byte[] decode = Base64.getDecoder().decode(lxlPrdKey);
            byte[] sign = SignatureUtils.sign(bytes, decode);

            //数字签名
            String s = Hex.encodeHexString(sign);
            System.out.println("sign:"+s);
            //校验
            byte[] decode1 = Base64.getDecoder().decode(lxlPubKey);

            byte[] sign11 = Hex.decodeHex(s.toCharArray());
            boolean verify = SignatureUtils.verify(bytes1, decode1, sign11);
            System.out.println("checkSign:"+verify);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
//4ae1273d0b953c71564efde87301d88e7f985d5e12b458510cda293964364700b9dfc2e867352538b50210c4f64b1acf562aef1836dde3ff6f7ef0d7d37821fb5c50c0de2ffc799c51ce6f5cb30bfd381e459bf58349745fd11cc4c09b6e578183229481110d9d6c909f8e00c67672a079dda69aa5cdb3e8bc1e5ae4f9aff5e71845baac0c665374b80f1451b85359a01d600f11011074aed354923f04e25f42e6a2405185e7874fa412002ad2b087073c15585c6b865f4408de941274f1d30118e8ccaf393ea4e8e9865573dc6ee979bbda21de2961370f6dff58ab955d052e87f2edd89924b9fe6e37bb74c1c9a8e622f86b7d0a8ed9d1fd40096c965055a5


    public static void jdkRSA() {
        try {
            // 1 初始化密钥
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(512);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

            // 2 私钥加密、公钥解密——加密
            // 用私钥进行签名
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            // 执行key的转化
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

            // 声明一个签名对象 使用JDK实现
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initSign(privateKey);
            signature.update(src.getBytes());
            byte[] sign = signature.sign();
            System.out.println("私钥加密、公钥解密——加密:" + Hex.encodeHexString(sign));

            // 3私钥加密、公钥解密——解密
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            // 创建签名对象
            signature = Signature.getInstance("MD5withRSA");
            signature.initVerify(publicKey);
            signature.update(src.getBytes());
            boolean verify = signature.verify(sign);
            System.out.println("私钥加密、公钥解密——解密:" + verify);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
