package com.gre.lxl.service.impl;

import com.gre.lxl.common.util.StringUtils;
import com.gre.lxl.common.util.uuid.IdUtils;
import com.gre.lxl.domain.CsPrjEvaluationEx;
import com.gre.lxl.mapper.CsPrjEvaluationExMapper;
import com.gre.lxl.service.IDealReflectService;
import com.gre.lxl.service.IDealSaveService;
import com.gre.lxl.utils.GeneratePrjNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author lxl
 * @date 2021/11/17 10:05
 */
@Service
public class CsPrjEvaluationExServiceImpl implements IDealSaveService<CsPrjEvaluationEx> {

    @Autowired
    private CsPrjEvaluationExMapper exMapper;
    @Autowired
    private GeneratePrjNo generatePrjNo;

    @Override
    public int saveDeal(List<CsPrjEvaluationEx> obgList) {
        int index = 0;
        if (!obgList.isEmpty()) {
            for (CsPrjEvaluationEx ex : obgList) {
                if (StringUtils.isEmpty(ex.getId())) {
                    ex.setId(IdUtils.simpleUUID());
                    ex.setPrjNo(generatePrjNo.getPrjNo());
                    ex.setEvalState("0");
                    ex.setCreateTime(new Date());
                    index += exMapper.insert(ex);
                } else {
                    index += exMapper.updateById(ex);
                }
            }
        }
        return index;
    }
}
