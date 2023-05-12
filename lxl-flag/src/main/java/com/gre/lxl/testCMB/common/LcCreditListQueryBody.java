package com.gre.lxl.testCMB.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 授信列表查询body
 *
 * @author lxl
 * @date 2022/5/30 9:50
 */
@Data
@JsonPropertyOrder(alphabetic = true)
public class LcCreditListQueryBody implements Serializable {


    /**
     * 输入接口授信列表查询
     */
    private List<Onb2dcqey1> onb2dcqey1;

    /**
     * 输出接口授信列表查询  包含授信编号及风险额度编号
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Onb2dcqez1> onb2dcqez1;
}
