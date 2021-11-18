package com.gre.lxl.controller;

import com.gre.lxl.common.core.domain.model.AjaxResult;
import com.gre.lxl.domain.ReceiverUser;
import com.gre.lxl.domain.dto.ProcessDTO;
import com.gre.lxl.service.IFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lxl
 * @date 2021/10/11 14:29
 */
@RestController
@RequestMapping("/lxl/flow")
public class FlowController {

    @Autowired
    private IFlowService flowService;

    @PostMapping("startConFlow")
    public AjaxResult startConFlow(@RequestBody ReceiverUser receiverUser) {
        return flowService.startConFlow(receiverUser) > 0 ? AjaxResult.success("开启成功") : AjaxResult.error("开启失败");
    }

    /**
     * 审核
     */
    @PostMapping("completeConFlow")
    public AjaxResult completeConFlow(@RequestBody ProcessDTO processDTO) {
        return flowService.completeConFlow(processDTO) > 0 ? AjaxResult.success("完成任务") :AjaxResult.error("操作失败");
    }

    /**
     * 退回
     */

    /**
     * 撤回
     */
}
