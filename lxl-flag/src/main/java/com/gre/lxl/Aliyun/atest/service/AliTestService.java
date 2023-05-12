package com.gre.lxl.Aliyun.atest.service;

import com.gre.lxl.Aliyun.atest.domain.WuLog;

import java.util.Map;

/**
 * @author lxl
 * @date 2021/11/24 10:02
 */
public interface AliTestService {


    Map<String, Object> queryTest(String no, String type);


    WuLog queryTest2(String no, String type);

}
