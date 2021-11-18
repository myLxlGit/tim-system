package com.gre.lxl.framework.system.app.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lxl
 * @date 2021/11/12 17:24
 */
@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    public static String appId;

    public static String appSecret;

    public static String getAppId() {
        return appId;
    }

    @Value("${app.appId}")
    public  void setAppId(String appId) {
        AppConfig.appId = appId;
    }

    public static String getAppSecret() {
        return appSecret;
    }

    @Value("${app.appSecret}")
    public void setAppSecret(String appSecret) {
        AppConfig.appSecret = appSecret;
    }

}
