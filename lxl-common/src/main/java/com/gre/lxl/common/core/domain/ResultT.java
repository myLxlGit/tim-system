package com.gre.lxl.common.core.domain;

import com.gre.lxl.common.constant.HttpStatus;
import com.gre.lxl.common.util.StringUtils;
import lombok.Getter;

/**
 * 全局统一返回实体
 *
 * @author qixlin
 * @date 2020/09/21 16:12
 */
@Getter
public class ResultT<T> {

    private final Integer code;

    private final String msg;

    private T data;

    public ResultT(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 初始化一个新创建的 ResultT 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public ResultT(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        if (StringUtils.isNotNull(data)) {
            this.data = data;
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static <T> ResultT<T> success() {
        return ResultT.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static <T> ResultT<T> success(T data) {
        return ResultT.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static <T> ResultT<T> success(String msg) {
        return ResultT.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static <T> ResultT<T> success(String msg, T data) {
        return new ResultT<>(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static <T> ResultT<T> error() {
        return ResultT.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static <T> ResultT<T> error(String msg) {
        return ResultT.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static <T> ResultT<T> error(String msg, T data) {
        return new ResultT<>(HttpStatus.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static <T> ResultT<T> error(int code, String msg) {
        return new ResultT<>(code, msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data  数据
     * @return 警告消息
     */
    public static <T> ResultT<T> error(int code, String msg, T data) {
        return new ResultT<>(code, msg, data);
    }

    public static <T> ResultT<T> count(int count) {
        return result(count > 0);
    }

    public static <T> ResultT<T> result(boolean result) {
        if (result) {
            return success("操作成功");
        } else {
            return error("操作失败");
        }
    }
}
