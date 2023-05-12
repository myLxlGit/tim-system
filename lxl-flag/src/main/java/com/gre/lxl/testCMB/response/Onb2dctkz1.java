package com.gre.lxl.testCMB.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 补档记录。
 *
 * @author lxl
 * @date 2022/5/29 20:35
 */
@Data
@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Onb2dctkz1 implements Serializable {

    /**
     * 流程实例号
     */
    private String reqnbr;

    /**
     * 网上编号
     */
    private String yurref;

    /**
     * 状态 S(完成),W(待补档),R(客户拒绝),C(银行撤销)
     */
    private String tsksts;

    /**
     * 银行意见
     */
    private String bnkmsg;

    /**
     * 发起人
     */
    private String crtusr;

    /**
     * 发起部门
     */
    private String crtbrn;

    /**
     * 机构名字
     */
    private String brnnam;

    /**
     * 任务id
     */
    private String tskidx;

    /**
     * 创建日期
     */
    @DateTimeFormat(pattern = "yyyyMMdd")
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    private Date crtdat;

}
