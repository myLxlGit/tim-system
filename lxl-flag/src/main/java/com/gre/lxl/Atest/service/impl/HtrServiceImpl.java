package com.gre.lxl.Atest.service.impl;

import com.gre.lxl.Atest.domain.Lor;
import com.gre.lxl.Atest.domain.Nor;
import com.gre.lxl.Atest.service.IFtrService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lxl
 * @date 2022/8/25 17:25
 */
@Service
public class HtrServiceImpl implements IFtrService<Nor> {
    @Override
    public List<Lor> testList() {
        List<Lor> list = new ArrayList<>();
        Lor lor = new Lor();
        lor.setHand("3");
        lor.setTh("3");
        lor.setSay("3");
        lor.setHand("3");
        list.add(lor);
        return list;
    }
}
