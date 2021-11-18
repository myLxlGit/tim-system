package com.gre.lxl.workflow.utils;

import com.gre.lxl.common.exception.FlowException;
import com.gre.lxl.common.util.StringUtils;
import com.gre.lxl.workflow.dto.FlowParams;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lxl
 * @date 2021/6/11 10:08
 */
@Service
public class FlowService {

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    /**
     * 开启
     * @return 流程id
     */
    public String startFlowProcess(FlowParams flowParams) {
        String starter = flowParams.getRouteParams().get("starter").toString();
        String starterName = flowParams.getRouteParams().get("starterName").toString();
        Map<String, Object> params = getStarterParams(starter,starterName);
        params.putAll(getActWitherParams(starter,starterName));
        if (StringUtils.isEmpty(flowParams.getInstanceId())) {
            Map<String, Object> outerParams = new HashMap<>();
            outerParams.put("routerParams", flowParams.getRouteParams());
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(flowParams.getFlowKey(), flowParams.getBusinessId(), outerParams);
            flowParams.setInstanceId(processInstance.getId());
        }

        Task taskConApply = taskService.createTaskQuery().processInstanceId(flowParams.getInstanceId()).singleResult();
        params.put("operation", "提交");

        taskService.setVariablesLocal(taskConApply.getId(), params);
        taskService.addComment(taskConApply.getId(), taskConApply.getProcessInstanceId(), starterName + "提交了流程");
        taskService.complete(taskConApply.getId(), params);

        List<Task> taskConList = taskService.createTaskQuery().processInstanceId(flowParams.getInstanceId()).orderByTaskCreateTime().desc().list();
        taskConList.forEach(item -> {
            Map<String, Object> starterParams = getStarterParams(starter,starterName);
            if (StringUtils.isNotEmpty(flowParams.getDescription())) {
                starterParams.put("description", flowParams.getDescription());
            }
            taskService.setVariablesLocal(item.getId(), starterParams);
        });
        return flowParams.getInstanceId();

    }

    /**
     * 完成任务
     * @param flowParams bean
     */
    public void completeTask(FlowParams flowParams) {
        String instanceId = flowParams.getInstanceId();
        if (StringUtils.isEmpty(instanceId)) {
            throw new FlowException("未找到审批流程");
        }
        String actWither = flowParams.getRouteParams().get("actWither").toString();
        String actWitherName = flowParams.getRouteParams().get("actWitherName").toString();
        List<Task> taskList = taskService.createTaskQuery().taskCandidateOrAssigned(actWither).orderByTaskCreateTime().desc().list();
        if (taskList.isEmpty()) {
            throw new FlowException("当前暂无审批流程");
        }
        String comments = flowParams.getComments();
        if (StringUtils.isEmpty(comments)) {
            comments = actWitherName + "提交了流程";
        }
        Map<String, Object> actWitherParams = getActWitherParams(actWither, actWitherName);
        actWitherParams.put("operation","审核通过");
        actWitherParams.put("comments",comments);
        taskService.setVariablesLocal(taskList.get(0).getId(),actWitherParams);
        taskService.addComment(taskList.get(0).getId(),instanceId,comments);
        taskService.complete(taskList.get(0).getId(),actWitherParams);
        List<Task> list = taskService.createTaskQuery().processInstanceId(instanceId).list();
        list.forEach(item -> {
            Map<String, Object> starterParams = getStarterParams(actWither, actWitherName);
            starterParams.put("description",flowParams.getDescription());
            taskService.setVariablesLocal(item.getId(),starterParams);
        });
    }



    //发起人信息
    public Map<String, Object> getStarterParams(String starter, String starterName) {
        Map<String, Object> params = new HashMap<>();
        params.put("starter", starter);
        params.put("starterName", starterName);
        params.put("startTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//        params.put("starterOrgan", SecurityUtils.getOrganId());
//        params.put("starterOrganName", SecurityUtils.getOrganName());
        return params;
    }

    //提交人信息
    public Map<String, Object> getActWitherParams(String actWither, String actWitherName) {
        Map<String, Object> params = new HashMap<>();
        params.put("actWither", actWither);
        params.put("actWitherName", actWitherName);
        params.put("withTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//        params.put("actWitherOrgan", SecurityUtils.getOrganId());
//        params.put("actWitherOrganName", SecurityUtils.getOrganName());
        return params;
    }



    public void testFlow(){
        System.out.println("12");
    }

}
