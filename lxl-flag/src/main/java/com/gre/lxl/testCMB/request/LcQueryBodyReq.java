package com.gre.lxl.testCMB.request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 信用证查询body
 *
 * @author lxl
 * @date 2022/5/26 9:22
 */
@Data
@JsonPropertyOrder(alphabetic = true)
public class LcQueryBodyReq implements Serializable {

    private List<Onb2dcqlx1> onb2dcqlx1;

}
