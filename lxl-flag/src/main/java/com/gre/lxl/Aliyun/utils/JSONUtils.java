package com.gre.lxl.Aliyun.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author qixlin
 * @date 2020/09/15 15:53
 */
public class JSONUtils {

    public static boolean isNotEmpty(JSONObject json) {
        return null != json && !json.isEmpty();
    }

    public static boolean isNotEmptyArray(JSONArray json) {
        return null != json && !json.isEmpty();
    }
}
