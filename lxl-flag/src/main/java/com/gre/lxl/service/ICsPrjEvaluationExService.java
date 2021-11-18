package com.gre.lxl.service;

import com.gre.lxl.domain.CsPrjEvaluationEx;

import java.util.List;

/**
 * @author lxl
 * @date 2021/11/17 10:04
 */
public interface ICsPrjEvaluationExService {

    List<CsPrjEvaluationEx> queryList(String format);

}
