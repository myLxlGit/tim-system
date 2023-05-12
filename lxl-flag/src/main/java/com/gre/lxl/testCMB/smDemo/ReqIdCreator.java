package com.gre.lxl.testCMB.smDemo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取主键:返回17位时间戳+3位递增数(同一时间递增)
 *
 * @author lxl
 * @date 2022/6/1 9:59
 */
public class ReqIdCreator {

    private static int addPart = 1;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static String lastDate = "";

    /**
     * 获取主键
     *
     * @param length 长度
     * @return 返回17位时间戳+3位递增数
     */
    public synchronized static String getReqId(int length) {
        //获取时间部分字符串
        Date now = new Date();
        StringBuilder nowStr = new StringBuilder(sdf.format(now));
        //获取数字后缀值部分
        if (ReqIdCreator.lastDate.equals(nowStr.toString())) {
            addPart += 1;
        } else {
            addPart = 1;
            lastDate = nowStr.toString();
        }

        if (length > 17) {
            length -= 17;
            for (int i = 0; i < length - ((addPart + "").length()); i++) {
                nowStr.append("0");
            }
            nowStr.append(addPart);
        }
        System.out.println("请求id = " + nowStr.toString() );
        //请求id = 202206011006046740001
        //请求id = 202206011007335540001
        //请求id = 202206011008421580001
        return nowStr.toString();

    }
}
