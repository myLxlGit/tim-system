package com.gre.lxl.workflow.service;

/**
 * @author lxl
 * @date 2021/6/10 16:58
 */
public interface IActivityService {

    /**
     * 部署流程
     *
     * @param processId 流程Id
     */
    void deploy(String processId);
}
