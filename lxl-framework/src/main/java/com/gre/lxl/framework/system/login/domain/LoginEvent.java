package com.gre.lxl.framework.system.login.domain;

import org.springframework.context.ApplicationEvent;

/**
 * @author lxl
 * @date 2021/11/7 16:24
 */
public class LoginEvent extends ApplicationEvent {

    private final int loginId;

    private final String loginName;

    public LoginEvent(Object source, int loginId, String loginName) {
        //source 可以随意定义
        super(source);
        this.loginId = loginId;
        this.loginName = loginName;
    }

    public int getLoginId() {
        return loginId;
    }

    public String getLoginName() {
        return loginName;
    }
}
