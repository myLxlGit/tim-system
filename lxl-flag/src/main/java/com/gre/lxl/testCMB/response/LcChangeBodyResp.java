package com.gre.lxl.testCMB.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 改证经办响应body
 *
 * @author lxl
 * @date 2022/5/30 13:59
 */
@Data
public class LcChangeBodyResp implements Serializable {

    private List<Onb2dcmoz1> onb2dcmoz1;

}
