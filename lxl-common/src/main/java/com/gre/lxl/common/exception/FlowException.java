package com.gre.lxl.common.exception;

/**
 * 自定义运行时异常类
 *
 * @author lxl
 * @date 2021/6/22 14:31
 */
public class FlowException extends RuntimeException{

    public FlowException () {
    }

    public FlowException (String message) {
        super(message);
    }
}
