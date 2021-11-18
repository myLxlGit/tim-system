package com.gre.lxl.common.util.uuid;

/**
 * ID生成器工具类
 *
 * @author lxl
 * @date 2020/09/01 00:00
 */
public class IdUtils {
    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     */
    public static String randomUUID() {
        return java.util.UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUUID() {
        return randomUUID().replaceAll("-", "");
    }
}
