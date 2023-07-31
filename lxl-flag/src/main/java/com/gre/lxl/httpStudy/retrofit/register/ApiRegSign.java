package com.gre.lxl.httpStudy.retrofit.register;

import com.github.lianjiatech.retrofit.spring.boot.interceptor.InterceptMark;

import java.lang.annotation.*;

/**
 * @author lxl
 * @date 2023/6/2 10:13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@InterceptMark
public @interface ApiRegSign {

    String appId();

    String secretId();


}
