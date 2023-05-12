package com.gre.lxl.testCMB.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 补档记录响应body
 *
 * @author lxl
 * @date 2022/5/29 20:40
 */
@Data
public class LcAddQueryResp implements Serializable {

    private List<Onb2dctkz1> onb2dctkz1;
}
