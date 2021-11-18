package com.gre.lxl.framework.system.login.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gre.lxl.common.core.domain.ResultT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

/**
 * @author lxl
 * @date 2021/11/15 18:17
 */
@Slf4j
@Configuration
public class TimAuthSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    public static final String LOGIN_PATTERN_URL = "/system/auth/login";

//    @Autowired
//    private TokenResolver resolver;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        super.configure(builder);
        //路径匹配
        AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher(LOGIN_PATTERN_URL, HttpMethod.POST.name());
        TimAuthFilter timAuthFilter = new TimAuthFilter(antPathRequestMatcher);
        timAuthFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        timAuthFilter.setAuthenticationSuccessHandler((req, resp, authentication) -> {

            log.info("登录成功");
            resp.setContentType("application/json;charset=UTF-8");
//            CrmAuthToken sourceToken = ((CrmAuthToken) authentication).getSourceToken();
//            SpringUtils.getBean(RedisCache.class).deleteObject(CAPTCHA_PREFIX + sourceToken.getRequestId());
//            // 生成令牌
//            String token = resolver.createToken((LoginUser) authentication.getPrincipal());
//            LogFactory.loginSuccess(sourceToken.getMode(), (String) sourceToken.getPrincipal());
//            String respText = objectMapper.writeValueAsString(ResultT.success("登录成功", token));
            String respText = objectMapper.writeValueAsString(ResultT.success("登录成功", "666666666"));
            resp.getWriter().write(respText);
        });

        timAuthFilter.setAuthenticationFailureHandler((req, resp, e) -> {
            log.info("登陆失败");
            resp.setContentType("application/json;charset=UTF-8");
            resp.getWriter().write(objectMapper.writeValueAsString(ResultT.error(e.getMessage())));
        });
        TimAuthProvider provider = new TimAuthProvider();
        builder.authenticationProvider(provider)
                .addFilterAfter(timAuthFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
