package com.gre.lxl.testCMB.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;

/**
 * 补档 信用证申请提交到银行后，银行审查相关材料，审核不通过，会给客户发起补档请求，要求客户补充相关资料。可使用此接口上传相关文件资料。
 *
 * @author lxl
 * @date 2022/5/29 20:02
 */
@Data
@JsonPropertyOrder(alphabetic = true)
public class Onb2dcadx1 implements Serializable {

    /**
     * 任务id
     */
    private String tskidx;

    /**
     * 用户意见（支持中文）
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String usrmsg;

    /**
     * 操作类型 S（补档）、R（拒绝补档）
     */
    private String tskflg;

}
