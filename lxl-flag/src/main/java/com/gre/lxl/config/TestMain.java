package com.gre.lxl.config;


/**
 * @author lxl
 * @date 2021/10/10 15:26
 */
public class TestMain {

    public static void main(String[] args) {
        String s1 = new String();
        System.out.println(s1);

        byte[] arr1 = {97,98,99};
        //解码，将计算机读的懂的转换成我们读得懂
        String s2 = new String(arr1);
        System.out.println(s2);

        byte[] arr2 = {97,98,99,100,101,102};
        //将arr2字节数组从2索引开始转换3个
        String s3 = new String(arr2,2,3);
        System.out.println(s3);

        byte[] arr3 = {'a','b','c','d','e','f'};
        //将字符数组转换成字符串
        String s4 = new String(arr3);
        System.out.println(s4);

        //将arr3字节数组从1索引开始转换3个
        String s5 = new String(arr3, 1, 3);
        System.out.println(s5);

        String s6 = new String("newString");
        System.out.println(s6);
    }
}
