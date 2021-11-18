package com.gre.lxl.system.dao;

import com.gre.lxl.system.dto.dto.MenuT;

import java.util.List;

/**
 * @author lxl
 * @date 2021/6/11 10:10
 */
public interface ITest {
    void test();

    /**
     *
     * 递归查询组织数
     * @return
     */
    List<MenuT> treeT();
}
