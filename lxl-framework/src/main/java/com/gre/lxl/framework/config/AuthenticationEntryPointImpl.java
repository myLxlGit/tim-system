//package com.gre.lxl.framework.config;
//
//import com.alibaba.fastjson.JSON;
//import com.gre.lxl.common.constant.HttpStatus;
//import com.gre.lxl.common.core.domain.model.AjaxResult;
//import com.gre.lxl.common.util.ServletUtils;
//import com.gre.lxl.common.util.StringUtils;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.Serializable;
//
///**
// * 认证异常时的处理程序
// *
// * @author lxl
// */
//@Component
//public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
//
//    private static final long serialVersionUID = -8970718410437077606L;
//
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
//        String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
//        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(HttpStatus.UNAUTHORIZED, msg)));
//    }
//}