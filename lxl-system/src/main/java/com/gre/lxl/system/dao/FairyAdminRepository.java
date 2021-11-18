package com.gre.lxl.system.dao;

import com.gre.lxl.system.dao.ctm.IFairyAdminRepository;
import com.gre.lxl.system.dto.FairyAdminDTO;
import com.gre.lxl.system.entity.FairyAdmin;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lxl
 * @date 2021/8/24 16:28
 */
public interface FairyAdminRepository extends JpaRepository<FairyAdmin,String>, IFairyAdminRepository {

    @Query("select new com.gre.lxl.system.dto.FairyAdminDTO(fa.adminId,fa.adminPassword,fa.adminNickname,fa.adminNicpic,fa.adminUsername,fc.catId,fc.catName,fc.tabId) " +
            "from FairyAdmin fa " +
            "left join FairyCat fc on fa.adminId = fc.tabId")
    List<FairyAdminDTO> queryFairyAdminAll();

    @Query("update FairyAdmin set adminUsername = :username where adminId = :adminId")
    @Modifying
    @Transactional
    int updateFairyAdminById(@Param("adminId") String adminId, @Param("username") String username);

}
