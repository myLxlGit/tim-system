package com.gre.lxl.framework.system.login.service;

import com.gre.lxl.common.core.redis.RedisCache;
import com.gre.lxl.common.exception.CustomException;
import com.gre.lxl.common.exception.FlowException;
import com.gre.lxl.common.util.StringUtils;
import com.gre.lxl.common.util.spring.SpringUtils;
import com.gre.lxl.framework.system.login.vo.CaptchaQuery;
import com.gre.lxl.framework.system.login.vo.LoginMode;
import com.gre.lxl.framework.system.login.vo.TimAuthToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static com.gre.lxl.common.constant.Constants.CAPTCHA_PREFIX;

/**
 * @author qixlin
 * @date 2021/06/23 14:17
 */
public interface IAuthService extends UserDetailsService {


    /**
     * 前置检查，用于检查验证码是否可以通过，只用于手机号，邮箱的登录方式
     *
     * @param authToken token实体im
     */
    default void preCheck(TimAuthToken authToken) {
        LoginMode mode = authToken.getMode();
        if (mode.isNeedCode()) {
            RedisCache redisCache = SpringUtils.getBean(RedisCache.class);
            // fixme
            String requestId = authToken.getRequestId();
            CaptchaQuery captchaQuery = redisCache.getCacheObject(CAPTCHA_PREFIX + requestId, CaptchaQuery.class);
            if (captchaQuery == null) {
                throw new BadCredentialsException("验证码不存在或已失效");
            }
            if (!captchaQuery.getCaptcha().equals(authToken.getCredentials())) {
                throw new BadCredentialsException("验证码不正确");
            }
        } else {
            throw new CustomException(StringUtils.format("{} 禁止调用preCheck", mode.name()));
        }
    }


//    default void checkUserStatus(UserCachedVO cachedVO, Logger log, String username) {
//        if (StringUtils.isNull(cachedVO)) {
//            log.info("登录用户：{} 不存在.", username);
//            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
//        } else if (UserStatus.DELETED.getCode().equals(cachedVO.getDelFlag())) {
//            log.info("登录用户：{} 已被删除.", username);
//            throw new AccountExpiredException("对不起，您的账号：" + username + " 已被删除");
//        } else if (UserStatus.DISABLE.getCode().equals(cachedVO.getUserStatus())) {
//            log.info("登录用户：{} 已被停用.", username);
//            throw new LockedException("对不起，您的账号：" + username + " 已停用");
//        }
//    }

    default void startCodeToUser(String captcha, String account) {
        throw new FlowException("禁止调用");
    }


    default void afterCall(UserDetails userDetails, Authentication authentication) {}
}
