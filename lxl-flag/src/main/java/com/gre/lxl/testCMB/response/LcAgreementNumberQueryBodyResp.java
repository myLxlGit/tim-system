package com.gre.lxl.testCMB.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 协议编号响应body
 *
 * @author lxl
 * @date 2022/5/29 21:26
 */
@Data
public class LcAgreementNumberQueryBodyResp implements Serializable {

    private List<Onb2dcqtz1> onb2dcqtz1;
}
