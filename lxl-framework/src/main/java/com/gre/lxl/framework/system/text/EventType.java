package com.gre.lxl.framework.system.text;

/**
 * @author lxl
 * @date 2021/11/12 17:58
 */
public enum EventType {

    LOGIN("登录"),

    LOGIN_OUT("退出");

    private final String text;

    EventType(String tips) {
        this.text = tips;
    }

    public String getText() {
        return text;
    }
}
