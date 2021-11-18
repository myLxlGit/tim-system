package com.gre.lxl.workflow.listeners;

import com.alibaba.fastjson.JSON;
import com.gre.lxl.common.util.StringUtils;
import com.gre.lxl.common.util.spring.SpringUtils;
import com.gre.lxl.workflow.dto.FlowReceiver;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.el.FixedValue;
import org.assertj.core.util.Lists;

import java.util.Map;

/**
 * 任务监听器
 *
 * @author lxl
 * @date 2021/10/11 14:20
 */
public class FlowTaskListener implements TaskListener {

    private FixedValue roleKey;

    @Override
    public void notify(DelegateTask delegateTask) {
        TaskService bean = SpringUtils.getBean(TaskService.class);
        //多角色未处理
        String starter = (String) bean.getVariables(delegateTask.getId()).get("starter");
        String starterName = (String) bean.getVariables(delegateTask.getId()).get("starterName");
        Map<String, Object> variables = (Map<String,Object>)bean.getVariables(delegateTask.getId()).get("routerParams");
        String fullStarter = variables.get("starter").toString();
        String fullStarterName = variables.get("starterName").toString();
        String roleKeyText = roleKey.getExpressionText();

        if ("self".equals(roleKeyText)) {
            if (StringUtils.isEmpty(starter)) {
                starter = fullStarter;
                starterName = fullStarterName;
            }
            delegateTask.addCandidateUsers(Lists.list(starter));
            bean.setVariableLocal(delegateTask.getId(),"wither",starter);
            bean.setVariableLocal(delegateTask.getId(),"witherName",starterName);
        } else {
            Object o = variables.get("receiverUser");//获取指定接收人
            FlowReceiver flowReceiver = JSON.parseObject(JSON.toJSONString(o), FlowReceiver.class);

            //应该查询查出来 现在模拟
            String userId = flowReceiver.getReceiver();
            String userName = flowReceiver.getReceiverName();
            delegateTask.addCandidateUsers(Lists.list(userId));
            bean.setVariableLocal(delegateTask.getId(),"wither",userId);
            bean.setVariableLocal(delegateTask.getId(),"witherName",userName);
        }
        bean.getVariables(delegateTask.getId());
    }
}
