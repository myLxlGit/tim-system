package com.gre.lxl.framework.system.login.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author lxl
 * @date 2021/11/16 14:27
 */
@Repository
public interface TestLoginMapper {

    int queryCount(@Param("id") Integer id);
}
