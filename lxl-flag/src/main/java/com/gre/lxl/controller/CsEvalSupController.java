package com.gre.lxl.controller;


import com.gre.lxl.common.core.domain.model.AjaxResult;
import com.gre.lxl.system.dao.ICsEvalSupService;
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
 * @since 2021-06-14
 */
@RestController
@RequestMapping("/cs-eval-sup")
public class CsEvalSupController {

    @Autowired
    private ICsEvalSupService supService;

    @GetMapping("select")
    public AjaxResult select() {
        return AjaxResult.success("查询成功",supService.selectSup());
    }


}
