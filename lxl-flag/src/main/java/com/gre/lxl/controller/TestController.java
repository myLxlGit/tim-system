package com.gre.lxl.controller;

import com.gre.lxl.common.core.domain.model.AjaxResult;
import com.gre.lxl.system.dto.dto.MenuTree;
import com.gre.lxl.system.dao.ITest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lxl
 * @date 2021/6/11 17:19
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private ITest iTest;

    @GetMapping("testL")
    public void test() {
        iTest.test();
    }

    @GetMapping("tree")
    public AjaxResult treeT() {
        return AjaxResult.success("查询成功",iTest.treeT().stream().map(MenuTree::new));
    }
}
