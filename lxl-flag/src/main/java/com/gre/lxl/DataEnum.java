package com.gre.lxl;

import com.gre.lxl.common.exception.CustomException;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author lxl
 * @date 2022/3/15 15:06
 */
public enum DataEnum {

    WORKDAY("工作日","1"),

    WEEKEND("周末","2"),

    HOLIDAYS("节假日","3");

    private final String text;
    private final String name;

    DataEnum(String text, String name) {
        this.text = text;
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public static DataEnum of(String text){
        for (DataEnum dataEnum : DataEnum.values()) {
            if(text.equals(dataEnum.getText())){
                return dataEnum;
            }
        }
        return null;
    }

    public static DataEnum ofName(String name) {
        for (DataEnum dataEnum : DataEnum.values()) {
            if(name.equals(dataEnum.getName())){
                return dataEnum;
            }
        }
        return null;
    }

    public static void ofExist(String name) {
        Optional<DataEnum> first = Arrays.stream(DataEnum.values()).filter(item -> name.equals(item.getName())).findFirst();
        if (!first.isPresent()) {
            throw new CustomException("无此类型:" + name);
        }
    }


    public String getName() {
        return name;
    }

}
