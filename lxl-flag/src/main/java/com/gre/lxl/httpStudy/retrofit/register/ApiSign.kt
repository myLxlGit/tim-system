package com.gre.lxl.httpStudy.retrofit.register

import com.github.lianjiatech.retrofit.spring.boot.interceptor.BasePathMatchInterceptor
import com.github.lianjiatech.retrofit.spring.boot.interceptor.InterceptMark
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@MustBeDocumented
@InterceptMark
annotation class ApiSign(
    /**
     * 密钥key
     * 支持占位符形式配置。
     */
    val appId: String,
    /**
     * 密钥
     * 支持占位符形式配置。
     */
    val secretId: String,
    /**
     * 拦截器匹配路径
     */
    val include: Array<String> = ["/**"],
    /**
     * 拦截器排除匹配，排除指定路径拦截
     */
    val exclude: Array<String> = [],
    /**
     * 处理该注解的拦截器类
     * 优先从spring容器获取对应的Bean，如果获取不到，则使用反射创建一个！
     */
    val handler: KClass<out BasePathMatchInterceptor> = SignInterceptor::class
)