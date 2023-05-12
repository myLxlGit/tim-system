package com.gre.lxl.testCMB.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 开证成功后的信用证号等信用证基本信息。
 *
 * @author lxl
 * @date 2022/5/27 17:43
 */
@Data
@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Onb2dcqlz1 implements Serializable {

    /**
     * 信用证id
     */
    private String ilcidx;

    /**
     * 信用证状态 W(待审批),N(审批拒绝),B(银行处理中),A(待补档),O(开证中),R(银行拒绝),C(取消),E(过期),F(完成)
     */
    private String ilcsts;

    /**
     * 经办日期
     */
    @DateTimeFormat(pattern = "yyyyMMdd")
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    private Date oprdat;

    /**
     * 开证币种
     */
    private String ilcccy;

    /**
     * 开证金额
     */
    private String ilcamt;

    /**
     * 受益人名称
     */
    private String bennam;

    /**
     * 信用证号
     */
    private String ilcnum;

}
