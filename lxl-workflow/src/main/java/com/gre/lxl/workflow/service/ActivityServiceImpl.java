package com.gre.lxl.workflow.service;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.springframework.stereotype.Service;

/**
 * @author lxl
 * @date 2021/6/10 16:59
 */
@Service
public class ActivityServiceImpl implements IActivityService {

    @Override
    public void deploy(String processId) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        DeploymentBuilder builder = repositoryService.createDeployment();
        builder.addClasspathResource("processes/" + processId + ".bpmn20.xml");
        builder.deploy();
    }
}
