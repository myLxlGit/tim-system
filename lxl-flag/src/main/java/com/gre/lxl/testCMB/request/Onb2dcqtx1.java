package com.gre.lxl.testCMB.request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;

/**
 * 协议编号列表查询
 * 使用全额保证金模式(FULL)的客户需线下与网点签署《国际信用证开证合作协议》，
 * 此接口提供协议编号查询，以供经办接口使用。
 *
 * @author lxl
 * @date 2022/5/29 20:49
 */
@Data
@JsonPropertyOrder(alphabetic = true)
public class Onb2dcqtx1 implements Serializable {

    /**
     * 机构号
     */
    private String brnnbr;

    /**
     * 客户号
     */
    private String cltnbr;

    /**
     * 业务模式
     */
    private String busmod;

    /**
     * 业务子模式
     */
    private String patsub;

    /**
     * 业务类型 O(开证)，M(改证)
     */
    private String bustyp;
}
