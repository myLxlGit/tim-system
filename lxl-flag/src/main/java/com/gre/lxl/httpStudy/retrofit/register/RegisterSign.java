package com.gre.lxl.httpStudy.retrofit.register;

import com.github.lianjiatech.retrofit.spring.boot.interceptor.BasePathMatchInterceptor;
import com.github.lianjiatech.retrofit.spring.boot.interceptor.InterceptMark;
import java.lang.annotation.*;
/**
 * @author lxl
 * @date 2023/2/3 9:14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@InterceptMark
public @interface RegisterSign {
    //@Documented 如果一个注解@B，被@Documented标注，那么被@B修饰的类，
    //生成文档时，会显示@B。如果@B没有被@Documented标准，最终生成的文档中就不会显示@B。
    //@Target用来表示注解作用范围，超过这个作用范围，编译的时候就会报错。
//    @Target:注解的作用目标
//    @Target(ElementType.TYPE)——接口、类、枚举、注解
//    @Target(ElementType.FIELD)——字段、枚举的常量
//    @Target(ElementType.METHOD)——方法
//    @Target(ElementType.PARAMETER)——方法参数
//    @Target(ElementType.CONSTRUCTOR) ——构造函数
//    @Target(ElementType.LOCAL_VARIABLE)——局部变量
//    @Target(ElementType.ANNOTATION_TYPE)——注解
//    @Target(ElementType.PACKAGE)——包，用于记录java文件的package信息

    String appId();

    String secretId();

    /**
     * 拦截器匹配路径
     *
     * @return
     */
    String[] include() default {"/**"};

    /**
     * 拦截器排除匹配，排除指定路径拦截
     *
     * @return
     */
    String[] exclude() default {};

    /**
     * 处理该注解的拦截器类
     * 优先从spring容器获取对应的Bean，如果获取不到，则使用反射创建一个！
     *
     * @return
     */
    Class<? extends BasePathMatchInterceptor> handler() default EyeSkySignInterceptor.class;

}
