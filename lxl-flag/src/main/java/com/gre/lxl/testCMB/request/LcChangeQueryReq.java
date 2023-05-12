package com.gre.lxl.testCMB.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 改证列表查询body
 *
 * @author lxl
 * @date 2022/5/30 14:22
 */
@Data
public class LcChangeQueryReq implements Serializable {

    private List<Onb2dcmlx1> onb2dcmlx1;

}
