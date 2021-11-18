package com.gre.lxl.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author lxl
 * @date 2021/9/25 17:46
 */
@Component
@Data
@ConfigurationProperties(prefix = "student")
public class Person {

    private String name;

    private String age;

}
