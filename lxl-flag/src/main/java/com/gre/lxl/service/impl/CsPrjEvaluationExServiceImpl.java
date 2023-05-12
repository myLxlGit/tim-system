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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
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

    //创建list 目的是为了价格趋势变化
    private final static List<Date> dateList = new ArrayList<>();

    @Override
    public int saveDeal(List<CsPrjEvaluationEx> obgList) {
        int index = 0;
        if (!obgList.isEmpty()) {
            for (CsPrjEvaluationEx ex : obgList) {
                if (StringUtils.isEmpty(ex.getId())) {
                    ex.setId(IdUtils.simpleUUID());
                    ex.setPrjNo(generatePrjNo.getPrjNo());
                    ex.setEvalState("0");
                    index += exMapper.insert(ex);
                } else {
                    index += exMapper.updateById(ex);
                }
            }
        }
        return index;
    }

    /**
     * dateList中放置两个日期之间的日期
     *
     * @param start 开始日期
     * @param end 结束日期
     */
    private void getBetweenDates(Date start, Date end) {
        dateList.clear();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
        tempStart.add(Calendar.DAY_OF_YEAR, 1);
        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        //包含结束
        tempEnd.add(Calendar.DATE, 1);
        while (tempStart.before(tempEnd)) {
            Date date = tempStart.getTime();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Date format = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            dateList.add(format);
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
    }
}
