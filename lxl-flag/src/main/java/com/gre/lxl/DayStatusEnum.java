package com.gre.lxl;

/**
 * @author lxl
 * @date 2021/12/9 16:48
 */
public enum DayStatusEnum {

    WORKDAY("工作日"),
    WEEKEND("周末"),
    HOLIDAYS("节假日");
    private final String text;

    DayStatusEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
