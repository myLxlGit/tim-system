package com.gre.lxl.framework.system.login.filter;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.gre.lxl.common.constant.Constants;
import com.gre.lxl.common.util.StringUtils;
import com.gre.lxl.framework.system.login.vo.LoginBody;
import com.gre.lxl.framework.system.login.vo.LoginMode;
import com.gre.lxl.framework.system.login.vo.TimAuthToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;

/**
 * @author lxl
 * @date 2021/11/16 10:01
 */
@Slf4j
public class TimAuthFilter extends AbstractAuthenticationProcessingFilter {

    public TimAuthFilter(RequestMatcher matcher) {
        super(matcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        if (!HttpMethod.POST.name().equalsIgnoreCase(req.getMethod())) {
            throw new AuthenticationServiceException("不支持的请求方式");
        }

        LoginBody loginBody = parseLoginBody(req);
        if (Objects.isNull(loginBody)) {
            throw new BadCredentialsException("登录信息读取失败");
        }
        String method = Optional.ofNullable(loginBody.getMode()).map(Enum::name).orElse("");
        String pwd = StringUtils.nvl(loginBody.getCredential(), "");
        String userName = StringUtils.nvl(loginBody.getLoginId(), "");
        String requestId = StringUtils.nvl(loginBody.getRequestId(), "");

        if (StringUtils.existsEmpty(userName,method)) {
            throw new BadCredentialsException("参数不完整");
        }

//        if ((!WC.equals(loginBody.getMode()) && !SASL.equals(loginBody.getMode())) && StringUtils.isEmpty(pwd)) {
//            throw new BadCredentialsException("参数不完整");
//        }

//        boolean emailLogin = method.equals(LoginMode.EM.name());
//        boolean smsLogin = method.equals(LoginMode.PH.name());

//        boolean emailLoginNotRequestId = (emailLogin || smsLogin) && StringUtils.isEmpty(requestId);
//        if (emailLoginNotRequestId) {
//            throw new BadCredentialsException("参数不完整");
//        }
//
//        if (emailLogin && !userName.contains(Constants.EMAIL_ADDRESS_SEPARATOR)) {
//            userName += "@tim.com.cn";
//        }

        LoginMode loginMode = LoginMode.valueOf(method);
        TimAuthToken authToken = new TimAuthToken(userName, pwd);
        authToken.setMode(loginMode);
        authToken.setRequestId(requestId);
        authToken.setDetails(authenticationDetailsSource.buildDetails(req));

        return super.getAuthenticationManager().authenticate(authToken);
    }

    private LoginBody parseLoginBody(HttpServletRequest request) {
        try {
            String bodyContent = IoUtil.read(request.getInputStream(), StandardCharsets.UTF_8);
            return JSONObject.parseObject(bodyContent, LoginBody.class);
        } catch (IOException e) {
            log.error("登录信息读取失败:{}",e.getMessage());
            return null;
        }
    }
}
