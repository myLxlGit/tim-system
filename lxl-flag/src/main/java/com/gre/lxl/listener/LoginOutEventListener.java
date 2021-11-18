package com.gre.lxl.listener;


import com.gre.lxl.common.core.redis.RedisCache;
import com.gre.lxl.framework.system.login.domain.LoginOutEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * @author lxl
 * @date 2021/11/12 17:39
 */
@Component
@Slf4j
public class LoginOutEventListener implements ApplicationListener<LoginOutEvent> {

    @Autowired
    private RedisCache redisCache;

    @Override
    public void onApplicationEvent(@NonNull LoginOutEvent event) {
        String eventContent = event.getEvent();
        log.info("登录退出事件监听器触发：{}",eventContent);
        redisCache.setCacheObject("loginOut",eventContent,1, TimeUnit.MINUTES);
    }

}
