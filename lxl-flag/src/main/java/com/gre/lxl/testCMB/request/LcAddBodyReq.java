package com.gre.lxl.testCMB.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 信用证补档请求体
 *
 * @author lxl
 * @date 2022/5/29 20:08
 */
@Data
@JsonPropertyOrder(alphabetic = true)
public class LcAddBodyReq implements Serializable {

    /**
     * 补档的基本信息
     */
    private List<Onb2dcadx1> onb2dcadx1;

    /**
     * 文件信息
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Onbdcecsx1> onbdcecsx1;


}
