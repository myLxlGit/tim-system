package com.gre.lxl.controller;

import com.gre.lxl.common.core.controller.BaseController;
import com.gre.lxl.common.core.page.TableDataInfo;
import com.gre.lxl.domain.CsPrjEvaluationEx;
import com.gre.lxl.domain.dto.CsPrjEvaluationExDTO;
import com.gre.lxl.service.ICsPrjEvaluationExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author lxl
 * @date 2021/11/17 10:08
 */
@RestController
@RequestMapping("/prjEvaluation")
public class CsPrjEvaluationExController extends BaseController {

//    @Autowired
//    private ICsPrjEvaluationExService evaluationExService;

    /**
     * 查询列表
     *
     * @param timeInterval 时间间隔 1m 近一个月，3m近三个月，6m近六个月
     * @return
     */
//    @GetMapping("/queryList")
//    public TableDataInfo queryExList(String timeInterval) throws ParseException {
//        Map<String,Integer> map = new HashMap<>();
//        map.put("1m",-1);
//        map.put("3m",-3);
//        map.put("6m",-6);
//        Integer integer = map.get(timeInterval);
//        if (integer == null) {
//            return TableDataInfo.defaultProps();
//        }
//        startPage();
//        Calendar instance = Calendar.getInstance();
//        instance.add(Calendar.MONTH,integer);
//        Date time = instance.getTime();
//        System.out.println("0:"+time);
////        Instant instant = time.toInstant();
////        ZoneId zoneId = ZoneId.systemDefault();
//
////        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
//        LocalDateTime localDateTime = time.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//        System.out.println("1:"+localDateTime);
//        String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        System.out.println("2:"+format);
//
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String format1 = dateFormat.format(time);
//        System.out.println("3:"+format1);
//        Date parse = dateFormat.parse(format1);
//        System.out.println("4:"+parse);
//
//        String timeString = "2021-10-17 15:25:01";
//        LocalDateTime parse1 = LocalDateTime.parse(timeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        System.out.println("5:"+parse1);
//        Date from = Date.from(parse1.atZone(ZoneId.systemDefault()).toInstant());
//        System.out.println("6:"+from);
//
//        List<CsPrjEvaluationEx> prjEvaluationExes = evaluationExService.queryList(format);
//        return getDataTable(prjEvaluationExes, CsPrjEvaluationExDTO::new);
//    }
}
