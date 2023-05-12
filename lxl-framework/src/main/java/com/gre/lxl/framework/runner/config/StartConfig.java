package com.gre.lxl.framework.runner.config;

import com.gre.lxl.framework.system.app.domain.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 项目启动后 可以实现application执行其他事件
 *
 * @author lxl
 * @date 2021/11/8 9:44
 */
@Configuration
@Slf4j
@Order(1)
public class StartConfig implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("测试ApplicationRunner打印{}","你一定猜不到吧");
        log.info("测试appId:{}", AppConfig.appId);
        log.info("测试appSecret:{}", AppConfig.appSecret);
    }
}
