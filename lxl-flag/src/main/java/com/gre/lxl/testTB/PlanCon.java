package com.gre.lxl.testTB;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lxl
 * @date 2022/6/23 19:10
 */
@Data
@Builder
public class PlanCon implements Serializable {

    /**
     * 方案名称
     */
    private String planName;

    /**
     * 方案结束时间
     */
    @DateTimeFormat(pattern = "yyyyMMdd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JSONField(format = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 方案开始时间
     */
    @DateTimeFormat(pattern = "yyyyMMdd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JSONField(format = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 方案业务Id
     */
    private String sname;

    /**
     * 期货盈亏上限,  输入0.1 界面显示10%
     */
    private String futureProfitLossTop;

    /**
     * 现货亏损上限,  输入0.3 界面显示30%
     */
    private String spotProfitLossTop;

    /**
     * 期现亏损上限 输入0.2 界面显示20%
     */
    private String profitLossTop;

}
