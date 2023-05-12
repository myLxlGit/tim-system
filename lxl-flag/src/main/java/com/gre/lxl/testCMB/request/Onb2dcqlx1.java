package com.gre.lxl.testCMB.request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;

/**
 * 信用证查询
 *
 * @author lxl
 * @date 2022/5/26 9:24
 */
@Data
public class Onb2dcqlx1 implements Serializable {

    /**
     * 信用证id
     */
    private String ilcIdx;


}
