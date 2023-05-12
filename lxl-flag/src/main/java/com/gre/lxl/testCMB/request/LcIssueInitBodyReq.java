package com.gre.lxl.testCMB.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 信用证经办body
 *
 * @author lxl
 * @date 2022/5/26 16:51
 */
@Data
@JsonPropertyOrder(alphabetic = true)
public class LcIssueInitBodyReq implements Serializable {

    /**
     * 信用证要素主请求，包含贸易背景资料、开证基本信息的大部分要素。 （必填）
     */
    private List<Onbdcmstx1> onbdcmstx1;

    /**
     * 货物/服务描述及条款请求，用于填写货物/服务描述、单据条款、特殊条款。 （必填）
     */
    private List<Onbdcgodx1> onbdcgodx1;

    /**
     * 信用证附件请求，用户填写贸易背景资料的附件信息。（不必填）
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Onbdcecsx1> onbdcecsx1;

    /**
     * 信用证合同请求，用于填写贸易背景资料的合同信息。 （必填）
     */
    private List<Onbdcconx1> onbdcconx1;

}
