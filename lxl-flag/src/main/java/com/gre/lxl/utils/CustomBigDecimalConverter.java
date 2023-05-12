package com.gre.lxl.utils;

import com.thoughtworks.xstream.converters.basic.BigDecimalConverter;
import org.springframework.util.StringUtils;

/**
 * @author lxl
 * @date 2022/7/13 10:03
 */
public class CustomBigDecimalConverter extends BigDecimalConverter {

    @Override
    public boolean canConvert(Class type) {
        return super.canConvert(type);
    }

    @Override
    public Object fromString(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return super.fromString(str);
    }
}
