package com.gre.lxl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gre.lxl.domain.CsPrjEvaluationEx;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lxl
 * @date 2021/10/11 15:41
 */
@Repository
public interface CsPrjEvaluationExMapper extends BaseMapper<CsPrjEvaluationEx> {


    List<CsPrjEvaluationEx> queryList(@Param("format") String format);

}
