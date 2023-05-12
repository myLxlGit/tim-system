package com.gre.lxl.testCMB.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 信用证查询响应body
 *
 * @author lxl
 * @date 2022/5/27 17:34
 */
@Data
public class LcQueryBodyResp implements Serializable {

    private List<Onb2dcqlz1> onb2dcqlz1;

}
