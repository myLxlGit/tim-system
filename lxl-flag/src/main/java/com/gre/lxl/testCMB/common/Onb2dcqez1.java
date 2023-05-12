package com.gre.lxl.testCMB.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lxl
 * @date 2022/5/30 10:12
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Onb2dcqez1 implements Serializable {

    /**
     * 授信编号
     */
    private String cdtnbr;

    /**
     * 风险额度编号
     */
    private String rsknbr;

}
