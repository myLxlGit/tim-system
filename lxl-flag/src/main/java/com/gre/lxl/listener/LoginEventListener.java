package com.gre.lxl.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gre.lxl.common.core.domain.LoginTeacher;
import com.gre.lxl.common.core.redis.RedisCache;
import com.gre.lxl.domain.RcTeacher;
import com.gre.lxl.framework.system.login.domain.LoginEvent;
import com.gre.lxl.mapper.RcTeacherMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author lxl
 * @date 2021/11/7 16:50
 */
@Component
@Slf4j
public class LoginEventListener implements ApplicationListener<LoginEvent> {

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private RcTeacherMapper teacherMapper;

    //接收事件
    @Override
    public void onApplicationEvent(@NonNull LoginEvent e) {
        //对事件做出处理
        try {
            RcTeacher rcTeacher = teacherMapper.selectById(e.getLoginId());
            LoginTeacher loginTeacher = JSONObject.parseObject(JSON.toJSONString(rcTeacher), LoginTeacher.class);
            redisCache.setCacheObject("loginTeacher",loginTeacher,1, TimeUnit.MINUTES);
        } catch (Exception ex) {
            log.error("根据登录id{}获取老师用户的teacherName失败", e.getLoginId());
        }
    }
}
