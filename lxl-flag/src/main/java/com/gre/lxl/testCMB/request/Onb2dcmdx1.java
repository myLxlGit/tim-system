package com.gre.lxl.testCMB.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 模式列表查询信息实体
 *
 * @author lxl
 * @date 2022/5/30 11:32
 */
@Data
public class Onb2dcmdx1 implements Serializable {

    /**
     * 业务代码 N05020(开证)，N05030(改证)
     */
    private String buscod;

}
