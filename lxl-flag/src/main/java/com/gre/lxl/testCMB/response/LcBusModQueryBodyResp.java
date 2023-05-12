package com.gre.lxl.testCMB.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 模式列表查询响应body
 *
 * @author lxl
 * @date 2022/5/30 11:46
 */
@Data
public class LcBusModQueryBodyResp implements Serializable {

    private List<Onb2dcmdz1> onb2dcmdz1;
}
