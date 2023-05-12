package com.gre.lxl.service.impl;

import com.gre.lxl.AMappingVo.MappingVo;
import com.gre.lxl.common.core.domain.BaseEntity;
import com.gre.lxl.common.exception.FlowException;
import com.gre.lxl.common.util.spring.SpringUtils;
import com.gre.lxl.domain.DealParams;
import com.gre.lxl.service.IDealSaveService;
import com.gre.lxl.service.ISaveService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lxl
 * @date 2021/11/18 10:42
 */
@Service
public class SaveServiceImpl implements ISaveService {


    @Override
    public int save(DealParams dealParams) {
        int index = 0;
        Map<String,Class> map =  MappingVo.SAVE_VO;
        Field[] declaredFields = dealParams.getClass().getDeclaredFields();
        List<String> collect = Arrays.stream(declaredFields).map(Field::getName).collect(Collectors.toList());
        for (String c : collect) {
            //拼接get方法名
            String methodName = "get" + c.substring(0, 1).toUpperCase() + c.substring(1);
            try {
                Method method = DealParams.class.getDeclaredMethod(methodName);
                List<BaseEntity> invoke = (List<BaseEntity>)method.invoke(dealParams);
                if (invoke != null) {
                    IDealSaveService bean = (IDealSaveService) SpringUtils.getBean(map.get(c));
                    index = bean.saveDeal(invoke);
                }
            } catch (Exception e) {
                throw new FlowException("保存失败"+ e);
            }


        }
        return index;
    }
}
