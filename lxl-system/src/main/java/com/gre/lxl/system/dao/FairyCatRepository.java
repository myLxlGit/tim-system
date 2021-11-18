package com.gre.lxl.system.dao;

import com.gre.lxl.system.dao.ctm.IFairyCatRepository;
import com.gre.lxl.system.entity.FairyCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lxl
 * @date 2021/8/24 16:31
 */
@Repository
public interface FairyCatRepository extends JpaRepository<FairyCat,String> , IFairyCatRepository {
}
