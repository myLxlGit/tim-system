package com.gre.lxl.system.dao;

import com.gre.lxl.system.dto.CsEval;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LXL
 * @since 2021-06-11
 */
public interface ICsEvalService{

    /**
     * 查询所有数据
     *
     * @return list
     */
    List<CsEval> selectAll();
}
