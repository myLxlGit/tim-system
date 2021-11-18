package com.gre.lxl.system.dao;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * @author lxl
 * @date 2021/7/7 18:05
 */
public class LxlFlagTest {

    //java 编写 千位符转化
    public static void main(String[] args) {
        long n = 100;
        DecimalFormat df = new DecimalFormat("#,###.00"); //转为千分符字符串
        String str = df.format(n);
        System.out.println(str);

        try {
            Double aaa = 100.00;
            int b = NumberFormat.getIntegerInstance(Locale.getDefault()).parse(String.valueOf(aaa)).intValue(); //转为数字

            Double aDouble = Double.valueOf(String.valueOf(b));
            System.out.println("" + b);
            System.out.println("-------------------------------------------------------------------");
            System.out.println(aDouble);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
