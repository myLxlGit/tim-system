package com.gre.lxl.common.util;

import java.util.Objects;

/**
 * @author lxl
 * @date 2020/12/13 19:20
 */
public class ObjectsUtil {

    public static <T> T requireNonNullElse(T obj, T defaultObj) {
        return obj != null ? obj : requireNonNull(defaultObj, "defaultObj");
    }

    public static <T> T requireNonNull(T defaultObj, String message) {
        if (Objects.isNull(defaultObj)) {
            throw new NullPointerException(message);
        }
        return defaultObj;
    }
}
