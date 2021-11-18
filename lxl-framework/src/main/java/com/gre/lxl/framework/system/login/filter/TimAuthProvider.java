package com.gre.lxl.framework.system.login.filter;

import com.gre.lxl.common.util.spring.SpringUtils;
import com.gre.lxl.framework.system.login.service.IAuthService;
import com.gre.lxl.framework.system.login.vo.LoginMode;
import com.gre.lxl.framework.system.login.vo.TimAuthToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Objects;

/**
 * @author qixlin
 * @date 2021/06/23 12:55
 */
@Slf4j
public class TimAuthProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TimAuthToken authToken = (TimAuthToken) authentication;
        LoginMode mode = authToken.getMode();
        try {
            if (Objects.isNull(mode)) {
                throw new BadCredentialsException("不支持的登录方式");
            }
            IAuthService authService = SpringUtils.getBean(mode.getServiceClass());

            if (mode.isNeedCode()) {
                authService.preCheck(authToken);
            }

            UserDetails userDetails = authService.loadUserByUsername((String) authToken.getPrincipal());
            authService.afterCall(userDetails, authToken);

            TimAuthToken newAuthToken = new TimAuthToken(userDetails, userDetails.getAuthorities());
            newAuthToken.setDetails(authToken.getDetails());
            newAuthToken.setSourceToken(authToken);
            return newAuthToken;

        } catch (Exception e) {
            //日志
//            LogFactory.loginFailure(mode, (String) authentication.getPrincipal(), e.getMessage());
            log.error("登录失败，原因：{}", e.getMessage());
            throw new InternalAuthenticationServiceException(e.getMessage());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TimAuthToken.class.isAssignableFrom(authentication);
    }
}
