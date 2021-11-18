package com.gre.lxl.service;

import java.util.List;

/**
 * @author lxl
 * @date 2021/11/18 11:07
 */
public interface IDealSaveService<T> extends IDealReflectService<T> {

    int saveDeal(List<T> obgList);

}
