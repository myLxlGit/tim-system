package com.gre.lxl.testCMB.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 待补档列表查询body
 *
 * @author lxl
 * @date 2022/5/29 20:27
 */
@Data
public class LcAddQueryBodyReq implements Serializable {

    private List<Onb2dctkx1> onb2dctkx1;
}
