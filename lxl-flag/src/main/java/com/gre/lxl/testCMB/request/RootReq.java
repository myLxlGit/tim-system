package com.gre.lxl.testCMB.request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gre.lxl.testCMB.common.SignatureBody;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lxl
 * @date 2022/5/27 11:55
 */
@Data
@JsonPropertyOrder(alphabetic = true)
public class RootReq implements Serializable {

    private RootMidNodeReq request;

    private SignatureBody signature;

}
