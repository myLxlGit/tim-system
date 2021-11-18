package com.gre.lxl.framework.system.login.service.impl;

import com.gre.lxl.common.model.LoginUser;
import com.gre.lxl.common.model.UserCachedVO;
import com.gre.lxl.common.util.SecurityUtils;
import com.gre.lxl.framework.system.login.compatible.service.CompatibleUserService;
import com.gre.lxl.framework.system.login.service.IAuthService;
import com.gre.lxl.framework.system.login.vo.LoginMode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author qixlin
 * @date 2021/06/23 14:17
 */
@Slf4j
@Service
public class AcAuthServiceImpl implements IAuthService {

    @Autowired
    private CompatibleUserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCachedVO userCachedVO = service.loadUserByUserName(LoginMode.AC, username);
//        checkUserStatus(userCachedVO, log, username);
        return new LoginUser(userCachedVO);
    }

    @Override
    public void afterCall(UserDetails userDetails, Authentication authentication) {
        String dbPwd = userDetails.getPassword();
        if (!SecurityUtils.matchesPassword((String) authentication.getCredentials(), dbPwd)) {
            throw new BadCredentialsException("用户名/密码不正确");
        }
    }
}
