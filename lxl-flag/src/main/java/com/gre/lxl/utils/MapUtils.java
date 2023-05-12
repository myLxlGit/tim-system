package com.gre.lxl.utils;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author lxl
 * @date 2023/5/6 11:25
 */
public class MapUtils {

    public static Map<String, Object> buildMap(Object... objects) {
        Map<String, Object> map = Maps.newHashMap();
        for (int i = 0; i < objects.length; i += 2) {
            map.put((String) objects[i], objects[i + 1]);
        }
        return map;
    }

}
