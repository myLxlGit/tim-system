package com.gre.lxl.testCMB.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 待补档列表查询
 *
 * @author lxl
 * @date 2022/5/29 20:21
 */
@Data
@JsonPropertyOrder(alphabetic = true)
public class Onb2dctkx1 implements Serializable {

    /**
     * 开始日期 yyyyMMdd格式
     */
    @DateTimeFormat(pattern = "yyyyMMdd")
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    private Date strdat;

    /**
     * 结束日期 yyyyMMdd格式
     */
    @DateTimeFormat(pattern = "yyyyMMdd")
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    private Date enddat;

    /**
     * S(完成),W(待补档),R(客户拒绝),C(银行撤销)
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String bussts;
}
