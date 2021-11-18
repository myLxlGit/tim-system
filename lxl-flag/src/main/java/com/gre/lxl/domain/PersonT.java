package com.gre.lxl.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lxl
 * @date 2021/9/25 18:17
 */
@Component
public class PersonT {

    public static String name;

    public static String age;

    public static String getName() {
        return name;
    }

    @Value("${student.name}")
    public void setName(String name) {
        PersonT.name = name;
    }

    public static String getAge() {
        return age;
    }

    @Value("${student.age}")
    public void setAge(String age) {
        PersonT.age = age;
    }
}
