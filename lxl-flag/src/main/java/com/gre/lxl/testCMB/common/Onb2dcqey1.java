package com.gre.lxl.testCMB.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;

/**
 * 授信列表查询参数
 * 用于无保证金模式下，根据客户号查询授信编号及风险额度编号信息。
 * 授信编号及风险额度编号，可用于填写信用证经办时的这两项要素信息。
 *
 * @author lxl
 * @date 2022/5/30 9:44
 */
@Data
@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Onb2dcqey1 implements Serializable {

    /**
     * 客户号
     */
    private String cltnbr;

    /**
     * 续传字段
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String ctnkey;

    /**
     * 业务模式
     */
    private String busmod;

    /**
     * 业务子模式
     */
    private String patsub;

    /**
     * 业务类型
     */
    private String bustyp;

}
