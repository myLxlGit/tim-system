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
 * 信用证要素主请求，包含贸易背景资料、开证基本信息的大部分要素。
 *
 * @author lxl
 * @date 2022/5/20 15:10
 */
@Data
@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Onbdcmstx1 implements Serializable {

    /**
     * 操作类型
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String oprtyp;

    /**
     * 业务模式
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String busmod;

    /**
     * 业务子模式
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String patsub;

    /**
     * 外部系统开证业务id
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String extidx;

    /**
     * 客户号
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cltnbr;

    /**
     * 扣费账号
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String dctacc;

    /**
     * 远期利率
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String librat;

    /**
     * 联系人
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String ctanam;

    /**
     * 联系人电话
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String ctatel;

    /**
     * 业务机构
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String trsbrn;

    /**
     * 申请说明
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String appdes;

    /**
     * 保证金模式 FULL,PART,NONE 范围内
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String detmod;

    /**
     * 协议编号
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String texnbr;

    /**
     * 授信编号
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cdtnbr;

    /**
     * 风险额度编号
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String rsknbr;

    /**
     * 交易编码
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String trscod;

    /**
     * 信用证类型，是否可转让 Y N
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fomtrs;

    /**
     * 保兑指示 N（不要求）,Y（要求）,M（可以加具）
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cfmins;

    /**
     * 保兑承担方 APL（申请人）,BEN（受益人）,OTH（详见费用条款）
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cfmprt;

    /**
     * 是否允许电索 Y（是）,N（否）
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cfmrim;

    /**
     * 信用证到期日 (yyyyMMdd格式)
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    @DateTimeFormat(pattern = "yyyyMMdd")
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    private Date expdat;

    /**
     * 交单地点
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String plaprt;

    /**
     * 申请人地址
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String appadd;

    /**
     * 受益人名称
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String bennam;

    /**
     * 受益人地址
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String benadd;

    /**
     * 受益人国家
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String bencnr;

    /**
     * 受益人账号
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String benacc;

    /**
     * 通知行SWIFT
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String advbnk;

    /**
     * 通知行名址
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String advadd;

    /**
     * 第二通知行SWIFT
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String advbk2;

    /**
     * 第二通知行名址
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String advad2;

    /**
     * 开证币种
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String opncur;

    /**
     * 开证金额
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal opnamt;

    /**
     * 增幅
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String icspct;

    /**
     * 减幅
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String dcspct;

    /**
     * 指定银行
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String spebnk;

    /**
     * 兑付方式 NG（议付）,AC（承兑）,PY（即期付款）,DP（延期付款）
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String paytyp;

    /**
     * 付款期限 ST（即期）,US（远期）,OT（付款期限详见单据/特殊条款）
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String paydrt;

    /**
     * 远期天数
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String drtday;

    /**
     * 期限类型
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String drttyp;

    /**
     * 是否提交汇票 Y（是）,N（否）
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String draact;

    /**
     * 受票行
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String drabnk;

    /**
     * 汇票占比 5F2(支持3位整数，2位小数)
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String drapct;

    /**
     * 汇票金额
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String draamt;

    /**
     * 分批装运条款 A（ALLOWED）,N（NOT ALLOWED, C（CONDITIONAL）
     *
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String ptlshp;

    /**
     * 分批装运描述
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String ptldtl;

    /**
     * 转运条款
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String trstem;

    /**
     * 转运描述
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String trsdtl;

    /**
     * 起运地，发货地
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String frmadd;

    /**
     * 装货港，起飞机场
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lodpot;

    /**
     * 卸货港，目的地机场
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String despot;

    /**
     * 最终目的地
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String finadd;

    /**
     * 最迟装运日期 yyyyMMdd格式
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String shpdat;

    /**
     * 装运期
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String shpped;

    /**
     * 交单期类型 PDA（交单在起算日后提交），PWV（交单在信用证有效期内），OTH（交单期详见单据条款/特殊条款）
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String reptyp;

    /**
     * 交单天数
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String prsday;

    /**
     * 交单期起算日类型
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String prsunt;

    /**
     * 费用条款
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String feetem;

}
