package com.gre.lxl.mapper;

import com.gre.lxl.system.dto.CsEvalSup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LXL
 * @since 2021-06-14
 */
@Repository
public interface CsEvalSupMapper extends BaseMapper<CsEvalSup> {

    List<CsEvalSup> selectSup(@Param("sup") CsEvalSup sup);
}
