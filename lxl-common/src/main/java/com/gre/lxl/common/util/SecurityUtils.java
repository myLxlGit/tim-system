package com.gre.lxl.common.util;

import com.gre.lxl.common.constant.HttpStatus;
import com.gre.lxl.common.exception.CustomException;
import com.gre.lxl.common.model.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 安全服务工具类
 *
 * @author sumec
 */
public class SecurityUtils {

    /**
     * 获取用户Id
     * @return
     */
    public static String getUserId() {
        try {
            return getLoginUser().getUser().getUserId();
        } catch (Exception e) {
            throw new CustomException("获取用户姓名异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取用户姓名
     * @return
     */
    public static String getFullName() {
        try {
            return getLoginUser().getUser().getFullName();
        } catch (Exception e) {
            throw new CustomException("获取用户ID异常", HttpStatus.UNAUTHORIZED);
        }
    }
    /**
     * 获取用户账户
     **/
    public static String getUsername() {
        try {
            return getLoginUser().getUsername();
        } catch (Exception e) {
            throw new CustomException("获取用户账户异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser() {
        try {
            return (LoginUser) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new CustomException("获取用户信息异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 判断是否可以正常获取用户Id
     *
     * @return true|false 是|否
     */
    public static boolean logon() {
        try {
            return StringUtils.isNotEmpty(getUserId());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取当前登录用户的组织Id
     *
     * @return 组织Id，若无组织返回null
     */
//    public static String getOrganId() {
//        if (!Objects.isNull(getLoginUser().getUser().getOrgan())) {
//            return getLoginUser().getUser().getOrgan().getOrganId();
//        }
//        return null;
//    }

    /**
     * 获取当前登录用户的组织名称
     *
     * @return 组织名称，若无组织返回null
     */
//    public static String getOrganName() {
//        if (!Objects.isNull(getLoginUser().getUser().getOrgan())) {
//            return getLoginUser().getUser().getOrgan().getOrganName();
//        }
//        return null;
//    }

    /**
     * 获取当前登录用户的工号
     *
     * @return 工号，若无工号返回null
     */
    public static String getJobNo() {
        if (!Objects.isNull(getLoginUser().getUser().getJobNo())) {
            return getLoginUser().getUser().getJobNo();
        }
        return null;
    }
    public static Long getErpId() {
        if (!Objects.isNull(getLoginUser().getErpId())) {
            return getLoginUser().getErpId();
        }
        return null;
    }

//    public static boolean isAdminUser() {
//        List<RoleVO> roles = getLoginUser().getUser().getRoles();
//        if (CollectionUtils.isEmpty(roles)) {
//            return false;
//        }
//        List<String> collect = roles.stream().map(RoleVO::getRoleKey).collect(Collectors.toList());
//        return collect.contains("sys:manager");
//    }

}
