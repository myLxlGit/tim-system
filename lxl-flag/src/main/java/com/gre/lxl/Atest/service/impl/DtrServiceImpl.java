package com.gre.lxl.Atest.service.impl;

import com.gre.lxl.Atest.domain.Lor;
import com.gre.lxl.Atest.domain.Nor;
import com.gre.lxl.Atest.service.IFtrService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lxl
 * @date 2022/8/25 17:24
 */
@Service
public class DtrServiceImpl implements IFtrService<Nor> {

    @Override
    public List<Lor> testList() {
        List<Lor> list = new ArrayList<>();
        Lor lor = new Lor();
        lor.setSt("1");
        lor.setTh("1");
        lor.setSay("1");
        lor.setHand("1");
        list.add(lor);
        print(lor);
        return list;
    }

}
