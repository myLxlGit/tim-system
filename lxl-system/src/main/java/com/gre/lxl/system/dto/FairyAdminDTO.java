package com.gre.lxl.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lxl
 * @date 2021/8/24 18:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FairyAdminDTO {
    private String adminId;

    private String adminPassword;

    private String adminNickname;

    private String adminNicpic;

    private String adminUsername;

    private FairyCatDTO cats;

    public FairyAdminDTO(String adminId,String adminPassword,String adminNickname,String adminNicpic,String adminUsername,String catId,String catName,String tabId) {
        this.adminId = adminId;
        this.adminPassword = adminPassword;
        this.adminNickname = adminNickname;
        this.adminNicpic = adminNicpic;
        this.adminUsername = adminUsername;
        this.cats = new FairyCatDTO();
        this.cats.setCatId(catId);
        this.cats.setCatName(catName);
        this.cats.setTabId(tabId);
    }
}
