package com.gre.lxl.system;

import com.gre.lxl.framework.system.app.domain.AppConfig;
import com.gre.lxl.testCMB.config.CmbConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author lxl
 * @date 2022/5/31 10:07
 */
@Configuration
@Slf4j
@Order(0)
public class Start2Config implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("###########################################");
        log.info("第二次测试ApplicationRunner打印{}","你一定猜不到吧");
        log.info("测试uid:{}", CmbConfig.uid);
        log.info("url:{}", CmbConfig.url);
    }
}
