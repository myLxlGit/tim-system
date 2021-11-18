package com.gre.lxl.system.dao.ctm;

import com.gre.lxl.common.core.page.TableDataInfo;
import com.gre.lxl.system.dto.FairyAdminDTO;

import java.util.List;

/**
 * @author lxl
 * @date 2021/8/24 18:12
 */
public interface IFairyAdminRepository {

    int updateFairyAdminAllId(FairyAdminDTO fairyAdminDTO);

    TableDataInfo pageQueryFairyAdmin();
}
