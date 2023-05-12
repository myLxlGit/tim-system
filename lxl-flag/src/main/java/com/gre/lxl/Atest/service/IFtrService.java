package com.gre.lxl.Atest.service;

import com.gre.lxl.Atest.domain.Lor;
import com.gre.lxl.Atest.domain.Nor;

import java.util.List;

/**
 * @author lxl
 * @date 2022/8/25 17:23
 */
public interface IFtrService<T> {

    List<Lor> testList();

    default void print(T t) {
        System.out.println("打印了");
    }

}
