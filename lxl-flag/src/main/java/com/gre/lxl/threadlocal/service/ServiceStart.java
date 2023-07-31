package com.gre.lxl.threadlocal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lxl
 * @date 2023/7/31 11:21
 */
@Service
public class ServiceStart {

    @Autowired
    private ServiceEnd serviceEnd;

    public static final ThreadLocal<String> threadLocal = new ThreadLocal<>();


    public void start() {
        threadLocal.remove();


        threadLocal.set("张十三你能接收到吗？");

        serviceEnd.end();

    }


}
