package com.gre.lxl.common.constant;

/**
 * 返回状态码
 *
 * @author sumec
 */
public class HttpStatus {
    /**
     * 操作成功
     */
    public static final int SUCCESS = 200;

    /**
     * 对象创建成功
     */
    public static final int CREATED = 201;

    /**
     * 请求已经被接受
     */
    public static final int ACCEPTED = 202;

    /**
     * 操作已经执行成功，但是没有返回数据
     */
    public static final int NO_CONTENT = 204;

    /**
     * 资源已被移除
     */
    public static final int MOVED_PERM = 301;

    /**
     * 重定向
     */
    public static final int SEE_OTHER = 303;

    /**
     * 资源没有被修改
     */
    public static final int NOT_MODIFIED = 304;

    /**
     * 参数列表错误（缺少，格式不匹配）
     */
    public static final int BAD_REQUEST = 400;

    /**
     * 未授权
     */
    public static final int UNAUTHORIZED = 401;

    /**
     * 访问受限，授权过期
     */
    public static final int FORBIDDEN = 403;

    /**
     * 资源，服务未找到
     */
    public static final int NOT_FOUND = 404;

    /**
     * 不允许的http方法
     */
    public static final int BAD_METHOD = 405;

    /**
     * 资源冲突，或者资源被锁
     */
    public static final int CONFLICT = 409;

    /**
     * 不支持的数据，媒体类型
     */
    public static final int UNSUPPORTED_TYPE = 415;

    /**
     * 系统内部错误
     */
    public static final int ERROR = 500;

    /**
     * 接口未实现
     */
    public static final int NOT_IMPLEMENTED = 501;

    /**
     * 线索公司共享冲突
     */
    public static final int SHARE_CONFLICT = 131381;

    /**
     * 线索个人专享冲突
     */
    public static final int PERSONAL_CONFLICT = 131382;

    /**
     * 线索首次提交状态
     */
    public static final int FIRST_CODE = 131383;

    /**
     * 线索不能申请自己的线索
     */
    public static final int REPEAT_CODE = 131384;

    /**
     * 线索冲突，不能修改
     */
    public static final int NO_UPDATE = 131385;
    /**
     * 共享人多个，不能删除
     */
    public static final int NO_DELETE = 131386;

    /**
     * 更新成功
     */
    public static final int UPDATE_SUCCESS = 131387;

    /**
     * 需进行客商评审
     */
    public static final int NEED_REVIEW = 131388;

    /**
     * 新增线索已被关联
     */
    public static final int LINKED = 131389;

    /**
     * 线索已存在，可直接关联
     */
    public static final int EXHIBITION_CLUE = 131390;

    /**
     * 参数为必填
     */
    public static final int PARAM_EMPTY = 131391;

    /**
     * 评审失败，存在冲突评审或已存在评审未过期
     */
    public static final int EVALUATION_CONFLICT = 131392;

    /**
     * 查无此公司
     */
    public static final int NO_COMPANY = 131393;

    /**
     * 数据同步的数据不能删除
     */
    public static final int BAN_DELETE = 131394;
}
