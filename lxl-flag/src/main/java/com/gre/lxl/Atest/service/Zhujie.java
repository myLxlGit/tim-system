package com.gre.lxl.Atest.service;

/**
 * @author lxl
 * @date 2023/1/30 16:45
 */
public @interface Zhujie {

    int personId(); //元素1

    String company() default "[unassigned]"; //元素2

}
