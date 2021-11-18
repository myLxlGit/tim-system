package com.gre.lxl.system.dao.ctm.impl;

import com.gre.lxl.system.dto.CsEval;
import com.gre.lxl.mapper.CsEvalMapper;
import com.gre.lxl.system.dao.ICsEvalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LXL
 * @since 2021-06-11
 */
@Service
public class CsEvalServiceImpl implements ICsEvalService {

    @Autowired
    private CsEvalMapper evalMapper;

    @Override
    public List<CsEval> selectAll() {
        return evalMapper.selectAll();
//        return evalMapper.selectList(null);
    }
}
