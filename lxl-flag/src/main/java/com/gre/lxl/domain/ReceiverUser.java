package com.gre.lxl.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lxl
 * @date 2021/11/5 15:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiverUser implements Serializable {

    private  static  final  long  serialVersionUID = 8656128222714547171L;

    /**
     * 主键
     */
    private String id;

    /**
     * 接收人
     */
    private String receiver;

    /**
     *接收人名称
     */
    private String receiverName;

    /**
     * 流程key
     */
    private String flowKey;

}
