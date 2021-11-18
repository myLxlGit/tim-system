package com.gre.lxl.framework.system.login.vo;

import lombok.Data;

/**
 * @author qixlin
 * @date 2021/06/23 15:10
 */
@Data
public class CaptchaQuery {

    private String loginId;

    private LoginMode mode;

    private String captcha;
}
