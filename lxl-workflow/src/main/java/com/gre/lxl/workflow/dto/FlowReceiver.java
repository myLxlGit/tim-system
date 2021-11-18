package com.gre.lxl.workflow.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lxl
 * @date 2021/11/5 15:20
 */
@Data
public class FlowReceiver implements Serializable {

    private  static  final  long  serialVersionUID = 8656128222714547171L;

    /**
     * 接收人
     */
    private String receiver;

    /**
     *接收人名称
     */
    private String receiverName;

}
