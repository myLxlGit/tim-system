package com.gre.lxl.httpStudy.retrofit.controller;

import com.gre.lxl.httpStudy.retrofit.remote.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lxl
 * @date 2023/2/3 13:40
 */
@RestController
@RequestMapping("/test/teddd")
public class RetrofitController {

    @Resource
    private RemoteService remoteService;

    @GetMapping("/test")
    public void test() {
        remoteService.getTest();
    }

}
