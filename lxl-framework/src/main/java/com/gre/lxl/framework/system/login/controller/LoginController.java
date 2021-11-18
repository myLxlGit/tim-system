package com.gre.lxl.framework.system.login.controller;

import com.gre.lxl.common.core.domain.LoginTeacher;
import com.gre.lxl.common.core.domain.model.AjaxResult;
import com.gre.lxl.common.core.redis.RedisCache;
import com.gre.lxl.framework.system.login.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lxl
 * @date 2021/6/21 16:43
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @Autowired
    private RedisCache redisCache;

    @GetMapping("/loginSuccess")
    public AjaxResult loginSuccess(int id) {
        return AjaxResult.success("查询成功",loginService.getApplicationListenerTest(id));
    }

    @GetMapping("/testRedisGetTeacher")
    public AjaxResult testRedisGetTeacher() {
        LoginTeacher loginTeacher = redisCache.getCacheObject("loginTeacher", LoginTeacher.class);
        String str = null;
        if (loginTeacher == null) {
            str = "暂时没有该老师";
        }else {
            str = "该老师姓名为：" + loginTeacher.getTeacherName();
        }
        return AjaxResult.success("查询成功",str);
    }

    @GetMapping("/loginOutSuccess")
    public AjaxResult loginOutSuccess(int id) {
        return AjaxResult.success("查询成功",loginService.loginOutSuccess(id));
    }
}
