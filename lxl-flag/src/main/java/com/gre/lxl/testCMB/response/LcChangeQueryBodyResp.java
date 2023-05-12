package com.gre.lxl.testCMB.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 改证查询body
 *
 * @author lxl
 * @date 2022/5/30 14:23
 */
@Data
public class LcChangeQueryBodyResp implements Serializable {

    private List<Onb2dcmlz1> onb2dcmlz1;
}
