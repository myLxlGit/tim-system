package com.gre.lxl;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 晨实-发票响应实体类
 *
 * @author lxl
 * @date 2022/8/24 11:11
 */
@Data
public class BillResp {

    /**
     * 主键
     */
    private String id;

    /**
     * 发票编号
     */
    private String billNo;

    /**
     * 应税劳务及服务名称
     */
    private String serverName;

    /**
     * 发票代码
     */
    private String billCode;

    /**
     * 号码
     */
    private String billNum;

    /**
     * 单位
     */
    private String kindName;

    /**
     * 规格型号
     */
    private String specification;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 数量
     */
    private BigDecimal amunt;

    /**
     * 税
     */
    private BigDecimal tax;

    /**
     * 税率
     */
    private BigDecimal taxRate;

    /**
     * 价税合计
     */
    private BigDecimal totalTax;

    /**
     * 金额/价款
     */
    private BigDecimal money;

    /**
     * 发票类型
     */
    private String type;

    /**
     * 合同业务编号，从新增购销合同接口返回值中
     */
    private String contractId;

    private String attribute1;

    private String attribute2;

    private String createTime;

    private String updateTime;

    private String userId;

    private String status;

    private String billStatus;

}
