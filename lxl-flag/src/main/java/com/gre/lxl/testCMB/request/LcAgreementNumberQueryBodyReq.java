package com.gre.lxl.testCMB.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 协议编号列表查询 body
 *
 * @author lxl
 * @date 2022/5/29 21:16
 */
@Data
public class LcAgreementNumberQueryBodyReq implements Serializable {

    private List<Onb2dcqtx1> onb2dcqtx1;
}
