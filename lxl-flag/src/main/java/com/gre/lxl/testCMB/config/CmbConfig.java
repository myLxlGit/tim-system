package com.gre.lxl.testCMB.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 招商银行配置文件
 *
 * @author lxl
 * @date 2022/5/31 9:05
 */
@Component
@ConfigurationProperties(prefix = "lc-cmb")
public class CmbConfig {

    /**
     * 国密算法
     */
    public static String sm = "SM";

    /**
     * 银行服务地址
     */
    public static String url;

    public static String getSm() {
        return sm;
    }

    public static String getUrl() {
        return url;
    }

    public static String getBankPubKey() {
        return bankPubKey;
    }

    public static String getPrivateKey() {
        return privateKey;
    }

    public static String getSm4key() {
        return sm4key;
    }

    public static String getUid() {
        return uid;
    }

    /**
     * 银行公钥
     */
    public static String bankPubKey;

    /**
     * 用户私钥
     */
    public static String privateKey;

    /**
     * 用户的对称密钥(sm4)
     */
    public static String sm4key;

    /**
     * 用户编号
     */
    public static String uid;

    @Value("lc-cmb.url")
    public void setUrl(String url) {
        CmbConfig.url = url;
    }

    @Value("lc-cmb.bankPubKey")
    public void setBankPubKey(String bankPubKey) {
        CmbConfig.bankPubKey = bankPubKey;
    }

    @Value("lc-cmb.privateKey")
    public void setPrivateKey(String privateKey) {
        CmbConfig.privateKey = privateKey;
    }

    @Value("lc-cmb.sm4key")
    public void setSm4key(String sm4key) {
        CmbConfig.sm4key = sm4key;
    }

    @Value("lc-cmb.uid")
    public void setUid(String uid) {
        CmbConfig.uid = uid;
    }
}
