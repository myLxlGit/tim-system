package com.gre.lxl.domain.dto;

import com.gre.lxl.workflow.dto.FlowReceiver;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lxl
 * @date 2021/11/4 16:09
 */
@Data
public class ProcessDTO implements Serializable {

    private  static  final  long  serialVersionUID = 8656128222714547171L;

    /**
     * 主键
     */
    private String id;

    /**
     * 评审意见
     */
    private String comments;

    /**
     * 处理人
     */
    private String userId;

    /**
     * 处理人名称
     */
    private String userName;

    /**
     * 接收人信息
     */
    private FlowReceiver flowReceiver;

}
