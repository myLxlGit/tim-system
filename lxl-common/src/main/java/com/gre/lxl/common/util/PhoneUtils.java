package com.gre.lxl.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lxl
 * @date 2021/9/26 13:56
 */
public class PhoneUtils {

    /**
     * 正则表达式验证手机号
     *
     * @param str 手机号
     * @return true | false
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        String s2="^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$";// 验证手机号
        if(StringUtils.isNotBlank(str)){
            p = Pattern.compile(s2);
            m = p.matcher(str);
            b = m.matches();
        }
        return b;
    }

}
