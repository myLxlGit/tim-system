package com.gre.lxl.Atest.domain;

import com.gre.lxl.Atest.service.impl.DtrServiceImpl;
import com.gre.lxl.Atest.service.impl.GtrServiceImpl;
import com.gre.lxl.Atest.service.impl.HtrServiceImpl;

/**
 * @author lxl
 * @date 2022/03/14 14:53
 **/
public enum TrType {

    DTR("dtr", DtrServiceImpl.class),

    GTR("gtr", GtrServiceImpl.class),

    HTR("htr", HtrServiceImpl.class);

    public final String value;

    public final Class<?> clazz;

    TrType(String value, Class<?> clazz) {
        this.value = value;
        this.clazz = clazz;
    }

    public String getValue() {
        return value;
    }

    public static String getValue(String value){
        for (TrType trType : TrType.values()) {
            if(trType.getValue().equals(value)){
                return value;
            }
        }
        return null;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public static Class<?> getClazz(String value){
        for (TrType trType : TrType.values()) {
            if(trType.getValue().equals(value)){
                return trType.getClazz();
            }
        }
        return null;
    }
}
