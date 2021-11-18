package com.gre.lxl.system.dao.ctm.impl;

import com.gre.lxl.system.dto.CsEvalSup;
import com.gre.lxl.system.dto.Student;
import com.gre.lxl.mapper.CsEvalSupMapper;
import com.gre.lxl.system.dao.ICsEvalSupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LXL
 * @since 2021-06-14
 */
@Service
public class CsEvalSupServiceImpl implements ICsEvalSupService {

    @Autowired
    private CsEvalSupMapper supMapper;

    @Autowired
    private Student student;

    @Override
    public List<CsEvalSup> selectSup() {
        CsEvalSup sup = new CsEvalSup();
        sup.setEvalId(1);

        System.out.println(student);
        return supMapper.selectSup(sup);
    }
}
