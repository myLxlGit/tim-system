package com.gre.lxl.testCMB.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 改证查询返回实体
 *
 * @author lxl
 * @date 2022/5/30 14:24
 */
@Data
public class Onb2dcmlz1 implements Serializable {

    /**
     * 信用证id
     */
    private String ilcidx;

    /**
     * 信用证状态 W(待审批),N(审批拒绝),B(银行处理中),A(待补档),O(开证中),R(银行拒绝),C(取消),E(过期),F( 完成）
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
    private BigDecimal ilcamt;

    /**
     * 信用证号
     */
    private String ilcnum;

    /**
     * 修改次数
     */
    private String mdfnum;

}
