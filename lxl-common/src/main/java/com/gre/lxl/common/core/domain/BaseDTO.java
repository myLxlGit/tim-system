package com.gre.lxl.common.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lxl
 * @date 2020/09/08 17:25
 */
public abstract class BaseDTO {
    /**
     * 请求参数
     */
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private transient Map<String, Object> params;

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }
    /**
     * 设置请求参数
     */
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
