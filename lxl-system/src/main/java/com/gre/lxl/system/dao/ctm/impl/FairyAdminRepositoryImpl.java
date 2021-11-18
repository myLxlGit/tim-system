package com.gre.lxl.system.dao.ctm.impl;

import com.gre.lxl.common.core.page.TableDataInfo;
import com.gre.lxl.system.dao.ctm.IFairyAdminRepository;
import com.gre.lxl.system.dto.FairyAdminDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lxl
 * @date 2021/8/24 18:44
 */
@Service
public class FairyAdminRepositoryImpl implements IFairyAdminRepository {

    @Autowired
    private JPAQueryFactory factory;

    @Override
    public int updateFairyAdminAllId(FairyAdminDTO fairyAdminDTO) {
        return 1;
    }

    @Override
    public TableDataInfo pageQueryFairyAdmin() {


        return null;
    }
}
