package com.gre.lxl.common.core;

/**
 * @author lxl
 * @date 2021/11/9 14:21
 */
public enum LoginEnum {

    AA("哈哈"),
    BB("呵呵"),
    CC("嘿嘿"),
    DD("拜拜");
    private final String text;

    LoginEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
