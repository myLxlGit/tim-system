package com.gre.lxl.utils;


import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * @author lxl
 * @date 2022/4/11 18:30
 */
public class Sha256Utils {

    private static final Logger logger = LoggerFactory.getLogger(Sha256Utils.class);

    /**
     * 获取图片SHA256
     *
     * @param file
     * @return
     */
    private static String getSHA256(File file) {
        String sha256Hex = null;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            sha256Hex = DigestUtils.sha256Hex(inputStream);
            return sha256Hex;
        } catch (IOException e) {
            logger.error("文件获取SHA256失败", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }



    public static String checkStrSHA256(String str,String SHA256){
        return DigestUtils.sha256Hex(str);
    }
    /**
     * 检查图片的SHA256 是否正确
     *
     * @param file   文件
     * @param SHA256 SHA256结果值
     * @return
     */
    private static boolean checkSHA256(File file, String SHA256) {
        String sha256Hex = null;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            sha256Hex = DigestUtils.sha256Hex(inputStream);
            if (sha256Hex.equals(SHA256)) {
                return true;
            }
        } catch (IOException e) {
            logger.error("SHA256检查文件完整性失败", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     *
     * @param str   字符串
     * @return 返回加密后的字符串
     */
    public static String getSHA256(String str){
        return DigestUtils.sha256Hex(str);
    }

    private static boolean checkSHA256(String targetStr, String SHA256) {
        String sha256 = getSHA256(targetStr);
        return SHA256.equals(sha256)?true:false;
    }

    //调用样例
    public static void main(String[] args) throws IOException {
//        File file = new File("D:\\bocom2\\测试文档.docx");
        File file = new File("F:/交通银行对接/交通银行接口-新版本SDK/在职证明.pdf");
        //文件摘要码
        String result = getSHA256(file);

        System.out.println(result);
        //校验文件是否被篡改
//        Boolean  ss=checkSHA256(file,"27dd54736aed8a3bbec9c39712f2a6e864e5c29b252bca88e01e1222bb268419");
        Boolean  ss=checkSHA256(file,result);
        System.out.println(ss);

//        String targetStr="1308302812048012821047";
        String targetStr="1997";
        String res =getSHA256(targetStr);
        System.out.println(res);
        Boolean  aa=checkSHA256(targetStr,"9eff139ce1ac9baa5f1586cfbbf05cded99d49dbc54e13176426c1de2ec540f4");
        System.out.println(aa);

    }

}
