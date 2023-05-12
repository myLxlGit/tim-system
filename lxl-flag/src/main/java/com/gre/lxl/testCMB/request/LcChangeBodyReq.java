package com.gre.lxl.testCMB.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 改证经办body
 *
 * @author lxl
 * @date 2022/5/30 13:55
 */
@Data
@JsonPropertyOrder(alphabetic = true)
public class LcChangeBodyReq implements Serializable {

    /**
     * 包含信用证改证的主体要素，用于填写改证基本信息。
     */
    private List<Onb2dcmox1> onb2dcmox1;

    /**
     * 用于填写改证所需的附件信息。
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Onbdcecsx1> onbdcecsx1;

}
