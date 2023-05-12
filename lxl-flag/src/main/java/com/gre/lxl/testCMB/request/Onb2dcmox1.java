package com.gre.lxl.testCMB.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 改证经办信息实体
 *
 * @author lxl
 * @date 2022/5/30 13:43
 */
@Data
@JsonPropertyOrder(alphabetic = true)
public class Onb2dcmox1 implements Serializable {

    /**
     * 业务模式 需传入用于改证的业务模式和业务子模式
     */
    private String busmod;

    /**
     * 业务子模式 需传入用于改证的业务模式和业务子模式
     */
    private String patsub;

    /**
     * 外部系统改证业务id 用于标志该笔业务，在原系统的唯一性
     */
    private String extidx;

    /**
     * 原始信用证号
     */
    private String ilcnum;

    /**
     * 联系人（支持中文）
     */
    private String ctanam;

    /**
     * 联系电话
     */
    private String ctatel;

    /**
     * 授信编号
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cdtnbr;

    /**
     * 风险额度编号
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String rsknbr;

    /**
     * 申请说明（支持中文）
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String appdes;

    /**
     * 开证金额
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal opnamt;

    /**
     * 最迟装运日
     */
    @DateTimeFormat(pattern = "yyyyMMdd")
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date shpdat;

    /**
     * 信用证到期日
     */
    @DateTimeFormat(pattern = "yyyyMMdd")
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date expdat;

    /**
     * 其他修改（支持中文
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String othmdf;

    /**
     * 费用承担类型 BEN（由受益人承担），BAK（由银行承担）
     */
    private String feetyp;

    /**
     * 扣费账号 当费用承担类型为BAK，则此选项必填
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String dctacc;

}
