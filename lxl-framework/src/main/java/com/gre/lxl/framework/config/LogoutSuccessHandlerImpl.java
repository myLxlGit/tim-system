//package com.gre.lxl.framework.config;
//
//
//import com.alibaba.fastjson.JSON;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 推出成功处理程序
// *
// * @author
// * @date 2020/10/27 16:59
// */
//@Configuration
//public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
//
//    @Autowired
//    private TokenResolver tokenResolver;
//
//    /**
//     * 退出处理
//     */
//    @Override
//    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//        LoginUser loginUser = tokenResolver.getLoginUser(request);
//        if (StringUtils.isNotNull(loginUser)) {
//            String userName = loginUser.getUsername();
//            // 记录用户退出日志
//            LogFactory.logOutSuccess(loginUser.getUser().getFullName(), userName, "退出成功", true);
//            // 删除用户缓存记录
//            tokenResolver.delLoginUser(loginUser.getToken());
//        }
//        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(HttpStatus.SUCCESS, "退出成功")));
//    }
//}