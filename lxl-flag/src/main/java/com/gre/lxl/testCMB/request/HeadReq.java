package com.gre.lxl.testCMB.request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lxl
 * @date 2022/5/26 9:53
 */
@Data
@JsonPropertyOrder(alphabetic = true)
public class HeadReq implements Serializable {

    private String funcode;
    private String userid;
    private String reqid;
}
