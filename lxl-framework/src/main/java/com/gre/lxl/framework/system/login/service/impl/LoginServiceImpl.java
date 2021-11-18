package com.gre.lxl.framework.system.login.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gre.lxl.common.core.domain.LoginTeacher;
import com.gre.lxl.common.core.redis.RedisCache;
import com.gre.lxl.common.util.StringUtils;
import com.gre.lxl.common.util.spring.SpringUtils;
import com.gre.lxl.framework.system.log.service.ISysLoginInfoService;
import com.gre.lxl.framework.system.login.domain.LoginEvent;
import com.gre.lxl.framework.system.login.domain.LoginOutEvent;
import com.gre.lxl.framework.system.login.service.ILoginService;
import com.gre.lxl.framework.system.text.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @author lxl
 * @date 2021/11/7 16:27
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public String getApplicationListenerTest(int id) {
        //发布事件
        eventPublisher.publishEvent(new LoginEvent("haha",id,"测试"));
        //获取存储在
        LoginTeacher rcTeacher = redisCache.getCacheObject("loginTeacher", LoginTeacher.class);
        ISysLoginInfoService bean = SpringUtils.getBean(ISysLoginInfoService.class);
        bean.saveLog(String.valueOf(id), EventType.LOGIN);
        return rcTeacher.getTeacherName();
    }

    @Override
    public String loginOutSuccess(int id) {
        //发布事件
        eventPublisher.publishEvent(new LoginOutEvent(this,"登录安全退出"));
        ISysLoginInfoService bean = SpringUtils.getBean(ISysLoginInfoService.class);
        bean.saveLog(String.valueOf(id), EventType.LOGIN);
        return redisCache.getCacheObject("loginOut");
    }
}
