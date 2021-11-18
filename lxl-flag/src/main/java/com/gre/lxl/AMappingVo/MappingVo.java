package com.gre.lxl.AMappingVo;

import com.gre.lxl.service.impl.CsPrjEvaluationExServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lxl
 * @date 2021/11/18 10:21
 */
public class MappingVo {

    public final static Map<String,Class> SAVE_VO = new HashMap<>();

    static {
        SAVE_VO.put("csPrjEvaluationExList", CsPrjEvaluationExServiceImpl.class);
//        SAVE_VO.put("csEvalSupList", CsPrjEvaluationExServiceImpl.class);

    }

}
