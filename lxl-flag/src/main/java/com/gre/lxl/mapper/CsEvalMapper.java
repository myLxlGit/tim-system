package com.gre.lxl.mapper;

import com.gre.lxl.system.dto.CsEval;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LXL
 * @since 2021-06-11
 */
@Repository
public interface CsEvalMapper extends BaseMapper<CsEval> {

    /**
     *
     * 查询所有的数据
     * @return list
     */
    List<CsEval> selectAll();
}
