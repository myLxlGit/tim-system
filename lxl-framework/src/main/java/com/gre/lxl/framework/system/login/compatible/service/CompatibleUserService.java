package com.gre.lxl.framework.system.login.compatible.service;

import com.gre.lxl.common.model.UserCachedVO;
import com.gre.lxl.common.util.StringUtils;
import com.gre.lxl.framework.system.login.compatible.mapper.CompatibleUserMapper;
import com.gre.lxl.framework.system.login.vo.LoginMode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author lxl
 * @date 2021/06/23 16:02
 */
@Service
@Slf4j
public class CompatibleUserService {

    @Autowired
    private CompatibleUserMapper cuMapper;

    public UserCachedVO loadUserByUserName(LoginMode mode, String loginId) {
        if (mode == null || StringUtils.isEmpty(loginId)) {
            log.error("{}查无此人, {}", loginId, mode);
            throw new UsernameNotFoundException("用户名密码不匹配");
        }
        // 获取用户基本信息
        UserCachedVO userCachedVO = cuMapper.loadUserByUserName(mode.name(), Integer.valueOf(loginId));
        if (userCachedVO == null) {
            log.error("{}查无此人, {}", loginId, mode);
            throw new UsernameNotFoundException("用户名密码不匹配");
        }
        // 获取组织信息
//        OrganVO organVO = cuMapper.selectOrganByUser(userCachedVO.getUserId());
//        if (Objects.isNull(organVO)) {
//            log.error("{}没有找到部门， {}", userCachedVO.getUserName(), mode);
//            throw new CustomException("没有找到部门");
//        }
//        userCachedVO.setOrgan(organVO);
        //todo 获取用户爱好信息

        // 获取角色信息
//        List<RoleVO> roleList = cuMapper.selectRoles(userCachedVO.getUserId());
//        if (roleList.isEmpty()) {
//            log.error("{}没有找到角色， {}", userCachedVO.getUserName(), mode);
//            throw new UsernameNotFoundException("用户信息缺失，如有疑问请联系管理员");
//        } else {
//            List<String> organIds = roleList.stream().map(i ->
//                    Arrays.stream(StringUtils.nvl(i.getOrganIds(), "").split(","))
//                            .distinct().collect(Collectors.toList())
//            ).flatMap(Collection::stream).distinct().collect(Collectors.toList());
//            List<OrganVO> organList = cuMapper.selectOrganById(organIds);
//            roleList.forEach(i -> {
//                String organIds1 = i.getOrganIds();
//                if (StringUtils.isEmpty(organIds1)) {
//                    i.setOrgans(Collections.emptyList());
//                } else {
//                    i.setOrgans(Lists.newArrayList());
//                    List<String> strings = Arrays.asList(organIds1.split(","));
//                    if (!strings.isEmpty()) {
//                        if (strings.contains(TOP_LEVEL_PARENT)) {
//                            OrganVO orgSharp = new OrganVO();
//                            orgSharp.setOrganId(TOP_LEVEL_PARENT);
//                            i.getOrgans().add(orgSharp);
//                        }
//                        List<String> collect = strings.stream().filter(string -> !TOP_LEVEL_PARENT.equals(string)).collect(Collectors.toList());
//                        if (!collect.isEmpty()) {
//                            organList.forEach(org -> {
//                                if (collect.contains(org.getOrganId())) {
//                                    i.getOrgans().add(org);
//                                }
//                            });
//                        }
//                    }
//                }
//            });
//            userCachedVO.setRoles(roleList);
//        }
//        if (userCachedVO.getRoles() == null) {
//            userCachedVO.setRoles(Collections.emptyList());
//        }
        return userCachedVO;
    }

//    public List<CompatibleMenuDTO> selectMenu(String userId) {
//        return cuMapper.selectMenu(userId);
//    }

//    public List<CompatibleMenuDTO> selectAllMenuDir() {
//        return cuMapper.selectAllMenuDir();
//    }
}
