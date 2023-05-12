package com.gre.lxl;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 多数据源配置 需要排除   DataSourceAutoConfiguration.class
 */

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@MapperScan("com.gre.lxl.**.mapper")
@EnableConfigurationProperties
@EnableScheduling
@EnableAsync

@EntityScan(basePackages = {"com.gre.lxl.**.entity", "com.gre.lxl.**.domain","com.gre.lxl.**.dto"}) // jpa支持
public class LxlFlagApplication {

    public static void main(String[] args) {
        SpringApplication.run(LxlFlagApplication.class, args);
        System.out.println("------------------------------------------------------------启动成功-----------------------------------------------------------");
    }
}
