package com.gre.lxl.framework.system.log.service;

import com.gre.lxl.framework.system.text.EventType;

/**
 * @author lxl
 * @date 2021/11/15 10:49
 */
public interface ISysLoginInfoService {

    int saveLog(String userId, EventType eventType);

}
