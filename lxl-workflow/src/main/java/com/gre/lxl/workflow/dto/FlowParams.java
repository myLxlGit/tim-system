package com.gre.lxl.workflow.dto;

import com.gre.lxl.common.util.StringUtils;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qixlin
 * @date 2020/12/10 14:57
 */
@Data
public class FlowParams {

    private String businessId;

    private String router;

    private String description;

    private String instanceId;

    private Map<String, Object> routeParams;

    private String comments;

    private String msgTitle;

    public Map<String, Object> getRouteParams() {
        if (routeParams == null){
            routeParams = new HashMap<>();
        }
        if (StringUtils.isEmpty(router)){
            return routeParams;
        }
        routeParams.put("router",router);
        return routeParams;
    }

    public String flowKey;

}
