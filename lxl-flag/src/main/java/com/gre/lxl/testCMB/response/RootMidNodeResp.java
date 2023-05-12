package com.gre.lxl.testCMB.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;

/**
 * 响应子节点
 *
 * @author lxl
 * @date 2022/5/27 14:51
 */
@Data
@JsonPropertyOrder(alphabetic = true)
public class RootMidNodeResp implements Serializable {

    private Object body;

    private HeadResp head;
}
