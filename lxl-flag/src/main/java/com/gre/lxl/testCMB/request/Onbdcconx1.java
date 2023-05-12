package com.gre.lxl.testCMB.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 信用证合同请求，用于填写贸易背景资料的合同信息。
 *
 * @author lxl
 * @date 2022/5/23 11:33
 */
@Data
@JsonPropertyOrder(alphabetic = true)
public class Onbdcconx1 implements Serializable {

    /**
     * 合同序号
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String connbr;

    /**
     * 合同币种
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String conccy;

    /**
     * 合同金额
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal conamt;

}
