package com.gre.lxl.testCMB.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gre.lxl.testCMB.common.SignatureBody;
import lombok.Data;

import java.io.Serializable;

/**
 * 响应根节点
 *
 * @author lxl
 * @date 2022/5/27 14:45
 */
@Data
@JsonPropertyOrder(alphabetic = true)
public class RootResp implements Serializable {

    private RootMidNodeResp response;

    private SignatureBody signature;

}
