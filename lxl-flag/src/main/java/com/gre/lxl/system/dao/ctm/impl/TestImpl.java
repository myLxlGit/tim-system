package com.gre.lxl.system.dao.ctm.impl;

import com.gre.lxl.system.dto.dto.MenuT;
import com.gre.lxl.system.dto.dto.UserT;
import com.gre.lxl.framework.utils.frameUtil;
import com.gre.lxl.system.dao.ITest;
import com.gre.lxl.workflow.utils.FlowService;
import com.gre.lxl.workflow.utils.FlowUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lxl
 * @date 2021/6/11 10:11
 */
@Service
public class TestImpl implements ITest {

    @Autowired
    private FlowService flowService;

    @Override
    public void test() {
        System.out.println(FlowUtils.name);
        System.out.println(frameUtil.name);
        flowService.testFlow();
    }

    @Override
    public List<MenuT> treeT() {
        List<MenuT> menuList = new ArrayList<>();
        UserT user = new UserT("10","小红");
        UserT user1 = new UserT("11","小4");
        List<UserT> userDTOS = new ArrayList<>();
        List<UserT> userDTOS1 = new ArrayList<>();
        List<UserT> userDTOS2 = new ArrayList<>();
        userDTOS.add(user);
        userDTOS1.add(user1);
        /*插入一些数据*/
        menuList.add(new MenuT("GN001D000", "0", "系统管理", "/admin", "Y",userDTOS2));
        menuList.add(new MenuT("GN001D100", "GN001D000", "权限管理", "/admin", "Y",userDTOS));
        menuList.add(new MenuT("GN001D110", "GN001D100", "密码修改", "/admin", "Y",userDTOS1));
        menuList.add(new MenuT("GN001D120", "GN001D100", "新加用户", "/admin", "Y",userDTOS2));
        menuList.add(new MenuT("GN001D200", "GN001D000", "系统监控", "/admin", "Y",userDTOS2));
        menuList.add(new MenuT("GN001D210", "GN001D200", "在线用户", "/admin", "Y",userDTOS2));
        menuList.add(new MenuT("GN002D000", "0", "订阅区", "/admin", "Y",userDTOS2));
        menuList.add(new MenuT("GN003D000", "0", "未知领域", "/admin", "Y",userDTOS2));

        return buildMenuT(menuList);
    }

    private List<MenuT> buildMenuT(List<MenuT> menuList) {
        List<MenuT> tag = menuList.stream().filter(o -> "0".equals(o.getParentId())).collect(Collectors.toList());
        menuList = menuList.stream().filter(o -> !"0".equals(o.getParentId())).collect(Collectors.toList());
        buildMenuTree(tag,menuList);
        return tag;
    }

    //递归
    private void buildMenuTree(List<MenuT> tag, List<MenuT> menuList) {
        if (tag.isEmpty()) {
            return;
        }
        tag.forEach( o -> o.setChildren(menuList.stream().filter(item -> o.getId().equals(item.getParentId())).collect(Collectors.toList())));
        tag.forEach(o -> buildMenuTree(o.getChildren(),menuList));
    }
}
