package com.gre.lxl.framework.system.log.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gre.lxl.framework.system.text.EventType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统登陆信息
 *
 * @author qixlin
 */
@Data
@TableName("SYS_LOGIN_INFO")
public class SysLoginInfo implements Serializable {
    /**
     * 访问主键
     */
    @TableId("LOGIN_ID")
    private String loginId;

    /**
     * 登录账号
     */
    @TableField("LOGIN_USER")
    private String loginUser;

    /**
     * 登录IP地址
     */
    @TableField("LOGIN_IP")
    private String loginIp;

    /**
     * 登录地点
     */
    @TableField("LOGIN_LOCAL")
    private String loginLocal;

    /**
     * 浏览器类型
     */
    @TableField("LOGIN_BROWSER")
    private String loginBrowser;

    /**
     * 操作系统
     */
    @TableField("LOGIN_OS")
    private String loginOs;

    /**
     * 登录状态（0成功 1失败）
     */
    @TableField("LOGIN_STATUS")
    private String loginStatus;

    /**
     * 提示消息
     */
    @TableField("LOGIN_MSG")
    private String loginMsg;

    /**
     * 访问时间
     */
    @TableField("LOGIN_TIME")
    private Date loginTime;

    /**
     * 用户id
     */
    @TableField("USER_ID")
    private String userId;

    @TableField(exist = false)
    private EventType eventType;

//    private LoginMethod method;

    private static final long serialVersionUID = 1L;

}