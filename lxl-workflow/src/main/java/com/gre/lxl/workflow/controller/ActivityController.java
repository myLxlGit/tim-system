package com.gre.lxl.workflow.controller;

import com.github.pagehelper.PageInfo;
import com.gre.lxl.common.core.domain.model.AjaxResult;
import com.gre.lxl.common.core.page.TableDataInfo;
import com.gre.lxl.common.util.StringUtils;
import com.gre.lxl.workflow.dto.FlowProcessLog;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author lxl
 * @date 2021/6/10 16:52
 */
@Controller
public class ActivityController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;

    /**
     * 查询当前人代办
     *
     * @param dataSize 分页参数
     * @param pageIndex 分页参数
     * @param dataType 类型
     * @param userId 用户id
     * @return table
     */
    @RequestMapping(value = "process/todo", method = RequestMethod.GET)
    @ResponseBody
    public TableDataInfo processTodo(Integer dataSize, Integer pageIndex, String dataType, String userId) {
        TableDataInfo tableDataInfo = new TableDataInfo();
        int firstResult = dataSize * (pageIndex - 1);
        int maxResults = pageIndex * dataSize;
        int initialValue = dataSize * (pageIndex - 1) + 1;
        Map<Object, Boolean> mapTemp = new HashMap<>();

        //dataType 1 代办
        if ("1".equals(dataType)) {
            TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned(userId);
            tableDataInfo.setTotal(taskQuery.count());

            List<Task> taskList = taskQuery.orderByTaskCreateTime().desc().listPage(firstResult, dataSize);

            AtomicInteger no = new AtomicInteger(initialValue);
            List<Map<String, Object>> collect = taskList.stream().map(ins -> {

                Map<String, Object> map = commonVariables(ins);
                map.put("no", no.getAndIncrement());
                return map;
            }).collect(Collectors.toList());
            tableDataInfo.setMsg("查询成功");
            tableDataInfo.setCode(200);
            tableDataInfo.setRows(collect);
        }
        //dataType 2 已办
//        if ("2".equals(dataType)) {
//            //需要传pageSize pageNum
//            startPage();
//            List<HistoryTask> taskList = flowMapper.queryHistoricTasks(SecurityUtils.getUserId());
//
////            HistoricTaskInstanceQuery historicTaskInstanceQuery = historyService.createHistoricTaskInstanceQuery()
////                    .taskCandidateUser(SecurityUtils.getUserId()).finished();
////            List<HistoricTaskInstance> taskList = historicTaskInstanceQuery.orderByTaskCreateTime().desc().listPage(firstResult,maxResults);
//
////            List<HistoricTaskInstance> taskList = historyService.createHistoricTaskInstanceQuery()
////                    .taskCandidateUser(SecurityUtils.getUserId()).finished().orderByTaskCreateTime().desc().list();
//
//            AtomicInteger no = new AtomicInteger();
//            //封装历史参数流程变量
//            List<Map<String, Object>> collect = taskList.stream().map(this::commonHistoryVariables).collect(Collectors.toList());
//
//            PageInfo<HistoryTask> pageInfo = new PageInfo<>(taskList);
//
//            tableDataInfo.setTotal(pageInfo.getTotal());
//            tableDataInfo.setMsg("查询成功");
//            tableDataInfo.setCode(200);
//            //添加序列号
//            tableDataInfo.setRows(collect.stream().peek(item -> item.put("no",pageInfo.getStartRow() + no.getAndIncrement())).collect(Collectors.toList()));
//            //根据描述过滤出唯一的描述（考虑到之前同一人完成两个节点任务）
////            List<Map<String, Object>> rows = collect.stream().filter(item -> mapTemp.putIfAbsent(item.get("description"),Boolean.TRUE) == null).collect(Collectors.toList());
//            //自定义分页
////            pageList(dataSize, pageIndex, tableDataInfo, firstResult, maxResults, no, collect);
//        }
        //dataType 3 全办
//        if ("3".equals(dataType)) {
//            HistoricTaskInstanceQuery historicTaskInstanceQuery = historyService.createHistoricTaskInstanceQuery()
//                    .taskCandidateUser(SecurityUtils.getUserId());
//            tableDataInfo.setTotal(historicTaskInstanceQuery.count());
//            List<HistoricTaskInstance> taskList = historicTaskInstanceQuery.listPage(firstResult, maxResults);
//
//            AtomicInteger no = new AtomicInteger(initialValue);
//
//            List<Map<String, Object>> maps = new ArrayList<>();
//            for (HistoricTaskInstance list : taskList) {
//                Map<String, Object> map = new HashMap<>();
//                List<HistoricVariableInstance> variableInstances = historyService.createHistoricVariableInstanceQuery()
//                        .excludeTaskVariables().list();
//                for (HistoricVariableInstance history : variableInstances) {
//                    if (history.getProcessInstanceId().equals(list.getProcessInstanceId())) {
//                        if ("description".equals(history.getVariableName())) {
//                            map.put("description", history.getValue());
//                        } else if ("routerParams".equals(history.getVariableName())) {
//                            String processInstanceId = history.getProcessInstanceId();
//                            Map<String, Object> routerParamsMap = (Map<String, Object>) history.getValue();
//                            routerParamsMap.put("instanceId", processInstanceId);
//                            map.put("routerParams", routerParamsMap);
//                        } else if ("starterName".equals(history.getVariableName())) {
//                            map.put("starterName", history.getValue());
//                        } else if ("startTime".equals(history.getVariableName())) {
//                            map.put("startTime", history.getValue());
//                        } else if ("operation".equals(history.getVariableName())) {
//                            map.put("operation", history.getValue());
//                        }
//                    }
//                }
//                map.put("no", no.getAndIncrement());
//                maps.add(map);
//            }
//            tableDataInfo.setMsg("查询成功");
//            tableDataInfo.setCode(200);
//            tableDataInfo.setRows(maps);
//        }
        return tableDataInfo;
    }

    /**
     * 审核记录
     */
    @RequestMapping(value = "process/log/{instanceId}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult processLog(@PathVariable("instanceId") String instanceId) {
        List<FlowProcessLog> logs = getLogs(instanceId);
//        for (FlowProcessLog log : logs) {
//            if (StringUtils.isNotEmpty(log.getActWither())) {
//                if (log.getActWither().contains(",")) {
//                    log.setActWither("1");
//                } else {
                    //前台获取头像，需要用户性别和用户头像文件id
//                    SysUser actUser = userMapper.selUserByUserId(log.getActWither());
//                    log.setActUser(actUser);
//                }
//            }
//        }
        return AjaxResult.success(logs);
    }

    //获取审核记录
    public List<FlowProcessLog> getLogs(String instanceId) {
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().processInstanceId(instanceId).orderByTaskCreateTime().asc().list();
        AtomicInteger i = new AtomicInteger();
        List<FlowProcessLog> collect = list.stream().map(task -> {
            FlowProcessLog log = new FlowProcessLog();
            log.setProcessNo(i.incrementAndGet());
            log.setNodeName(task.getName());
            log.setStartTime(task.getCreateTime());
            log.setEndTime(task.getEndTime());

            return getFlowProcessLog(task, log);
        }).collect(Collectors.toList());

        return collect.stream().filter(item -> item.getWither() != null && !"reject".equals(item.getComments())).collect(Collectors.toList());
    }

    private FlowProcessLog getFlowProcessLog(HistoricTaskInstance task, FlowProcessLog log) {
        List<HistoricVariableInstance> variableInstances = historyService.createHistoricVariableInstanceQuery().taskId(task.getId()).list();

        for (HistoricVariableInstance history :
                variableInstances) {
            if ("actWither".equals(history.getVariableName())) {
                log.setActWither((String) history.getValue());
            } else if ("starterName".equals(history.getVariableName())) {
                log.setStarter((String) history.getValue());
            } else if ("witherName".equals(history.getVariableName())) {
                log.setWither((String) history.getValue());
            } else if ("operation".equals(history.getVariableName())) {
                log.setOperation(history.getValue());
            } else if ("opinionsType".equals(history.getVariableName())) {
                String value = (String) history.getValue();
                String opinionsType = null;
                if ("0".equals(value)) {
                    opinionsType = "正常推进";
                } else if ("1".equals(value)) {
                    opinionsType = "有条件推进";
                } else if ("2".equals(value)) {
                    opinionsType = "放弃业务";
                } else if ("退回".equals(value)) {
                    opinionsType = "退回";
                } else if ("撤回".equals(value)) {
                    opinionsType = "撤回";
                } else if ("4".equals(value)) {
                    opinionsType = "审核通过";
                } else if ("提交".equals(value)) {
                    opinionsType = "提交";
                } else if ("结束".equals(value)) {
                    opinionsType = "结束";
                } else if ("终止".equals(value)) {
                    opinionsType = "终止";
                }
                log.setOpinionsType(opinionsType);
            } else if ("actWitherName".equals(history.getVariableName())) {
                log.setActWitherName((String) history.getValue());
            } else if ("fileLogDTOS".equals(history.getVariableName())) {
                log.setFileLogDTOS(history.getValue());
            }
        }

        log.setComments(taskService.getTaskComments(task.getId()).stream().map(Comment::getFullMessage).collect(Collectors.joining(",")));
        return log;
    }

    /**
     * 查询通用的代办流程变量
     *
     * @param ins 正在执行中的任务
     * @return map
     */
    private Map<String, Object> commonVariables(Task ins) {
        Map<String, Object> variables = taskService.getVariables(ins.getId());

        Map<String, Object> map = new HashMap<>();
        map.put("taskId", ins.getId());
        map.put("taskName", ins.getName());
        map.put("instanceId", ins.getProcessInstanceId());
        map.put("description", variables.get("description"));
        map.put("starterName", variables.get("starterName"));
        map.put("startTime", variables.get("startTime"));
        map.put("operation", variables.get("operation"));
        Map<String, Object> routerParamsMap = (Map<String, Object>) variables.get("routerParams");
        routerParamsVar(map, routerParamsMap, ins.getProcessInstanceId());
        return map;
    }

    private void routerParamsVar(Map<String, Object> map, Map<String, Object> routerParamsMap, String processInstanceId) {
        String router = routerParamsMap.get("router").toString();
        routerParamsMap.put("instanceId", processInstanceId);
        routerParamsMap.put("approveFlag", true);
        routerParamsMap.put("flag", true);
//        if ("ConstructionDetails".equals(router)) {
//            String type = (String) routerParamsMap.get("type");
//            String id = (String) routerParamsMap.get("id");
//            String inId = userMapper.queryExInId(id);
//            String inCreId = userMapper.queryExInCreId(id);
//            if (type != null) {
//                if ("firstProcess".equals(type)) {
//                    routerParamsMap.put("instanceId", processInstanceId);
//                    routerParamsMap.put("instanceCreditId",inCreId);
//                } else if ("secondProcess".equals(type)) {
//                    routerParamsMap.put("instanceCreditId",inCreId);
//                    routerParamsMap.put("instanceId",inId);
//                }
//            }
//        }
        map.put("routerParams", routerParamsMap);
    }

    /**
     * 分页列表
     */
    private void pageList(Integer dataSize, Integer pageIndex, TableDataInfo tableDataInfo, int firstResult, int maxResults, AtomicInteger no, List<Map<String, Object>> rows) {
        int total = rows.size();
        if (total / dataSize >= pageIndex) {
            rows = rows.subList(firstResult,maxResults);
        } else {
            rows = rows.subList(firstResult,total);
        }
        rows.forEach(item -> item.put("no",no.getAndIncrement()));
        tableDataInfo.setTotal(total);
        tableDataInfo.setMsg("查询成功");
        tableDataInfo.setCode(200);
        tableDataInfo.setRows(rows);
    }
}
