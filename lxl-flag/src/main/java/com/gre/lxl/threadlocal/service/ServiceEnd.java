package com.gre.lxl.threadlocal.service;

import org.springframework.stereotype.Service;

/**
 * @author lxl
 * @date 2023/7/31 11:21
 */
@Service
public class ServiceEnd {

    public void end()  {

        String s = ServiceStart.threadLocal.get();
        System.out.println(s);


    }

}
