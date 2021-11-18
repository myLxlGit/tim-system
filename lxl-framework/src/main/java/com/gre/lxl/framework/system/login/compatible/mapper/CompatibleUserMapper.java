package com.gre.lxl.framework.system.login.compatible.mapper;

import com.gre.lxl.common.model.UserCachedVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author qixlin
 * @date 2021/06/23 15:41
 */
@Repository
public interface CompatibleUserMapper {

    /**
     * 加载人员数据
     *
     * @param mode    登录方式
     * @param loginId 用户标识
     * @return UserCachedVO
     */
    UserCachedVO loadUserByUserName(@Param("mode") String mode, @Param("loginId") Integer loginId);

    /**
     * 查询用户所在的部门，仅用于登录获取部门，不用于其他查询
     *
     * @param userId 用户id
     * @return OrganVO
     */
//    OrganVO selectOrganByUser(@Param("userId") String userId);

    /**
     * 查询指定用户的所有角色信息
     *
     * @param userId 用户id
     * @return List<RoleVO>
     */
//    List<RoleVO> selectRoles(@Param("userId") String userId);

    /**
     * 根据给定的角色Id获取部门清单
     *
     * @param organIds 部门Id列表
     * @return List<OrganVO>
     */
//    List<OrganVO> selectOrganById(@Param("organIds") List<String> organIds);

    /**
     * 查询用户的所有菜单
     *
     * @param userId 用户Id，为空时默认为管理员
     * @return List<CompatibleMenuDTO>
     */
//    List<CompatibleMenuDTO> selectMenu(@Param("userId") String userId);

    /**
     * 查询所有的菜单目录，仅目录，用于生成用户菜单
     *
     * @return List<CompatibleMenuDTO>
     */
//    List<CompatibleMenuDTO> selectAllMenuDir();
}
