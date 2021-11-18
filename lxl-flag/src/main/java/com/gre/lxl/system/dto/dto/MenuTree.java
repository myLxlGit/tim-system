package com.gre.lxl.system.dto.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lxl
 * @date 2021/5/31 17:23
 */
@Data
@NoArgsConstructor
public class MenuTree {

    private String id;

    private String label;

    //m 菜单 u 用户
    private String type;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<MenuTree> children;

    public MenuTree(MenuT menu) {
        this.id = menu.getId();
        this.label = menu.getText();
        this.type = "m";
        this.children = menu.getChildren().stream().map(MenuTree::new).collect(Collectors.toList());
        this.children.addAll(menu.getUserTList().stream().map(MenuTree::new).collect(Collectors.toList()));
    }

    public MenuTree(UserT userDTO) {
        this.id = userDTO.getUserId();
        this.label = userDTO.getUserName();
        this.type = "u";
    }
}
