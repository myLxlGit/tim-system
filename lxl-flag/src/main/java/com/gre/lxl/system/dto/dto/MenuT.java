package com.gre.lxl.system.dto.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lxl
 * @date 2021/5/31 15:34
 */
@Data
@NoArgsConstructor
public class MenuT {

    private String id;
    private String parentId;
    private String text;
    private String url;
    private String yxbz;
    private List<MenuT> children;
    private List<UserT> userTList;

    public MenuT(String id, String parentId, String text, String url, String yxbz, List<UserT> userDTOList) {
        this.id = id;
        this.parentId = parentId;
        this.text = text;
        this.url = url;
        this.yxbz = yxbz;
        this.userTList = userDTOList;
    }
}
