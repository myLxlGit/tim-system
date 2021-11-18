package com.gre.lxl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gre.lxl.domain.RcTeacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lxl
 * @date 2021/11/7 17:05
 */
@Repository
public interface RcTeacherMapper extends BaseMapper<RcTeacher> {

    /**
     * 批量新增老师信息
     *
     * @param rcTeachers bean
     * @return 影响的条数
     */
    int saveTeachers(@Param("teachers") List<RcTeacher> rcTeachers);

}
