package com.gre.lxl.service.impl;

import com.gre.lxl.common.exception.FlowException;
import com.gre.lxl.common.util.StringUtils;
import com.gre.lxl.domain.CsPrjEvaluationEx;
import com.gre.lxl.domain.ReceiverUser;
import com.gre.lxl.domain.dto.ProcessDTO;
import com.gre.lxl.mapper.CsPrjEvaluationExMapper;
import com.gre.lxl.service.IFlowService;
import com.gre.lxl.workflow.dto.FlowParams;
import com.gre.lxl.workflow.utils.FlowService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lxl
 * @date 2021/10/11 14:32
 */
@Service
public class FlowServiceImpl implements IFlowService {

    @Autowired
    private FlowService flowService;
    @Autowired
    private CsPrjEvaluationExMapper evaluationMapper;
    @Autowired
    private TaskService taskService;

    @Override
    public int startConFlow(ReceiverUser receiverUser) {
        String flowKey = receiverUser.getFlowKey();
        String id = receiverUser.getId();
        if (StringUtils.isEmpty(receiverUser.getReceiver()) || StringUtils.isEmpty(receiverUser.getReceiverName())) {
            throw new FlowException("接收人不能为空");
        }
        CsPrjEvaluationEx csPrjEvaluationEx = evaluationMapper.selectById(id);
        String instanceId = csPrjEvaluationEx.getInstanceId();
        FlowParams flowParams = new FlowParams();
        flowParams.setBusinessId(id);
        flowParams.setRouter("ConstructionDetails");
        flowParams.setDescription("【" +csPrjEvaluationEx.getPrjName() + "】项目" + "已提交给您审核，请前往审核");
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("starter","1");
        map.put("starterName","张飞");
        map.put("receiverUser",receiverUser);
        if (instanceId != null) {
            map.put("instanceId",instanceId);
        }
        flowParams.setFlowKey(flowKey);
        flowParams.setRouteParams(map);
        String newInstanceId = flowService.startFlowProcess(flowParams);
        csPrjEvaluationEx.setInstanceId(newInstanceId);
        //审核中
        csPrjEvaluationEx.setEvalState("1");
        return evaluationMapper.updateById(csPrjEvaluationEx);
    }

    @Override
    public int completeConFlow(ProcessDTO processDTO) {
        String id = processDTO.getId();
        String comments = processDTO.getComments();
        String actWither = processDTO.getUserId();
        String actWitherName = processDTO.getUserName();
        if (processDTO.getFlowReceiver() == null) {
            throw new FlowException("接收人不能为空");
        }

        CsPrjEvaluationEx csPrjEvaluationEx = evaluationMapper.selectById(id);
        String instanceId = csPrjEvaluationEx.getInstanceId();
        List<Task> list = taskService.createTaskQuery().taskCandidateOrAssigned(actWither).processInstanceId(instanceId).list();
        if (list.isEmpty()) {
            throw new FlowException("当前人暂无审批流程");
        }

        FlowParams flowParams = new FlowParams();
        flowParams.setBusinessId(id);
        flowParams.setComments(comments);
        flowParams.setInstanceId(instanceId);
        flowParams.setRouter("ConstructionDetails");
        flowParams.setDescription("【" + "内蒙古测试001" + "】项目" + "已提交给您审核，请前往审核");
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("actWither",actWither);
        map.put("actWitherName",actWitherName);
        map.put("instanceId",instanceId);
        flowParams.setRouteParams(map);
        flowService.completeTask(flowParams);
        csPrjEvaluationEx.setEvalState("2");
        return evaluationMapper.updateById(csPrjEvaluationEx);
    }
}
