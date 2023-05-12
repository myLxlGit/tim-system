package com.gre.lxl.testCMB.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;

/**
 * head节点
 *
 * @author lxl
 * @date 2022/5/27 14:40
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class HeadResp implements Serializable {

    private String bizcode;
    private String funcode;
    private String reqid;
    private String resultcode;
    private String resultmsg;
    private String rspid;
    private String userid;

}
