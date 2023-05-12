package com.gre.lxl.testCMB.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;

/**
 * 货物/服务描述及条款请求，用于填写货物/服务描述、单据条款、特殊条款。
 *
 * @author lxl
 * @date 2022/5/23 9:43
 */
@Data
@JsonPropertyOrder(alphabetic = true)
public class Onbdcgodx1 implements Serializable {

    /**
     * 条款类型 G（货物描述），D（单据条款），S（特殊条款）
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String temtyp;

    /**
     * 条款内容
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String temtxt;

}
