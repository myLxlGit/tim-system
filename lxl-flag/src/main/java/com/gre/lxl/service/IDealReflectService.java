package com.gre.lxl.service;

import java.util.List;

/**
 * @author lxl
 * @date 2021/11/18 10:18
 */
public interface IDealReflectService<T> {

    default int save(List<T> tList) {
        throw new RuntimeException("禁止调用");
    }

}
