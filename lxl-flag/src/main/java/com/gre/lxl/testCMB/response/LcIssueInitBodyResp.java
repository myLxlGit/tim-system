package com.gre.lxl.testCMB.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 信用证经办响应body
 *
 * @author lxl
 * @date 2022/5/30 14:47
 */
@Data
public class LcIssueInitBodyResp implements Serializable {

    private List<Onb2dcopz1> onb2dcopz1;

}
