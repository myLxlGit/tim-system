package com.gre.lxl.testCMB.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 模式列表查询body
 *
 * @author lxl
 * @date 2022/5/30 11:41
 */
@Data
public class LcBusModQueryBodyReq implements Serializable {

    private List<Onb2dcmdx1> onb2dcmdx1;
}
