package com.gre.lxl.framework.system.log.service.impl;

import com.gre.lxl.common.util.ServletUtils;
import com.gre.lxl.common.util.ip.IpUtils;
import com.gre.lxl.common.util.uuid.IdUtils;
import com.gre.lxl.framework.system.log.domain.SysLoginInfo;
import com.gre.lxl.framework.system.log.mapper.SysLoginInfoMapper;
import com.gre.lxl.framework.system.log.service.ISysLoginInfoService;
import com.gre.lxl.framework.system.text.EventType;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author lxl
 * @date 2021/11/15 10:49
 */
@Service
@Slf4j
public class SysLoginInfoServiceImpl implements ISysLoginInfoService {

    @Autowired
    private SysLoginInfoMapper loginInfoMapper;

    @Override
    public int saveLog(String userId, EventType eventType) {
        SysLoginInfo sysLoginInfo = new SysLoginInfo();
        sysLoginInfo.setLoginId(IdUtils.simpleUUID());
        sysLoginInfo.setLoginUser(userId);
        String ipAddr = IpUtils.getIpAddr(ServletUtils.getRequest());
        sysLoginInfo.setLoginIp(ipAddr);

        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        // 获取客户端操作系统
        String os = userAgent.getOperatingSystem().getName();
        // 获取客户端浏览器
        String browser = userAgent.getBrowser().getName();
        sysLoginInfo.setLoginBrowser(browser);
        sysLoginInfo.setLoginOs(os);
        sysLoginInfo.setLoginTime(new Date());
        sysLoginInfo.setLoginStatus("0");
        sysLoginInfo.setUserId(userId);
        sysLoginInfo.setLoginMsg(eventType.getText());
        return loginInfoMapper.insert(sysLoginInfo);
    }
}
