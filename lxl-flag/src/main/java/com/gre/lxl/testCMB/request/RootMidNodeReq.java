package com.gre.lxl.testCMB.request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gre.lxl.testCMB.request.HeadReq;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lxl
 * @date 2022/5/27 11:57
 */
@Data
@JsonPropertyOrder(alphabetic = true)
public class RootMidNodeReq implements Serializable {

    private Object body;

    private HeadReq head;

}
