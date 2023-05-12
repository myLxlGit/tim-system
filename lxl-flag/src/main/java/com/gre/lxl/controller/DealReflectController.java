package com.gre.lxl.controller;

import com.gre.lxl.common.core.controller.BaseController;
import com.gre.lxl.common.core.domain.model.AjaxResult;
import com.gre.lxl.domain.DealParams;
import com.gre.lxl.service.ISaveService;
import com.gre.lxl.utils.AsycService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lxl
 * @date 2021/11/18 10:28
 */
@RestController
@RequestMapping("/reflect")
public class DealReflectController extends BaseController {

    @Autowired
    private ISaveService saveService;

    @PostMapping("/save")
    public AjaxResult save(@RequestBody DealParams dealParams) {
        return saveService.save(dealParams) > 0 ? AjaxResult.success("保存成功",dealParams) : AjaxResult.error("保存失败");
    }

    @Autowired
    private AsycService asycService;
    @GetMapping("/test2")
    public AjaxResult test2() {
        System.out.println("主线程 =====> 开始 =====> " + System.currentTimeMillis());
        asycService.sendMsg();
        System.out.println("主线程 =====> 结束 =====> " + System.currentTimeMillis());


        return AjaxResult.success("保存成功");
    }

}
