package com.gre.lxl.controller;


import com.gre.lxl.common.core.domain.model.AjaxResult;
import com.gre.lxl.system.dao.ICsEvalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LXL
 * @since 2021-06-11
 */
@RestController
@RequestMapping("/cs-eval")
public class CsEvalController {

    @Autowired
    private ICsEvalService evalService;

    @GetMapping("selectAll")
    public AjaxResult select(){
        return AjaxResult.success("查询成功",evalService.selectAll());

    }

}
