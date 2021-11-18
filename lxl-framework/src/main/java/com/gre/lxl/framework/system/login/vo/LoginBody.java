package com.gre.lxl.framework.system.login.vo;

import lombok.Data;

@Data
public class LoginBody {

    /**
     * 登录标识
     */
    private String loginId;

    /**
     * 登录凭证
     */
    private String credential;

    /**
     * 登录方式
     */
    private LoginMode mode;

    /**
     * 请求Id
     */
    private String requestId;
}
