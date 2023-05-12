package com.gre.lxl.utils;


import com.thoughtworks.xstream.converters.basic.DateConverter;
import org.apache.commons.lang3.StringUtils;

import java.util.TimeZone;

/**
 * @author lxl
 * @date 2022/7/13 10:06
 */
public class CustomDateConverter extends DateConverter {


    public CustomDateConverter() {};

    /**
     * 构造方法
     *
     * @param string
     * @param strings
     * @param timeZone
     */
    public CustomDateConverter(String string, String[] strings, TimeZone timeZone) {
        super(string, strings, timeZone);
    }

    /**
     * 时间日期转换
     */
    @Override
    public Object fromString(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        return super.fromString(str);
    }

}
