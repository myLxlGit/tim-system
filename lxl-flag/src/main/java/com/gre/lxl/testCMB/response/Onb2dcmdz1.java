package com.gre.lxl.testCMB.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * 模式列表查询返回实体信息
 *
 * 包含业务模式编号、业务模式子编号，可用于填写信用证经办时的这两项要素信息。
 * 如零审批标记为Y，则代表不需要在网银客户端进行下一级审批，直接提交至银行。
 * @author lxl
 * @date 2022/5/30 11:38
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Onb2dcmdz1 implements Serializable {

    /**
     * 业务模式编号
     */
    private String busmod;

    /**
     * 业务模式子编号
     */
    private String patsub;

    /**
     * 模式中文名称（支持中文
     */
    private String modnam;

    /**
     * 零审批标记 Y为是，N为否
     */
    private String zerflg;

}
