package com.gre.lxl.common.core.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Entity基类
 *
 * @author lxl
 */
@Data
@ApiIgnore
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 搜索值
     */
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private transient String searchValue;

    /**
     * 创建者
     */
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    @TableField(value = "CREATE_USER", fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 创建时间
     */
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新者
     */
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    @TableField(value = "UPDATE_USER", fill = FieldFill.UPDATE)
    private String updateUser;

    /**
     * 更新时间
     */
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    @TableField(value = "UPDATE_TIME", fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 备注
     */
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @ApiModelProperty(hidden = true)
    private transient String remark;

    /**
     * 开始时间
     */
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private transient String beginTime;

    /**
     * 结束时间
     */
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private transient String endTime;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @ApiModelProperty(hidden = true)
    private transient Integer no;

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
     * 获取Id
     *
     * @return id
     */
    public abstract String getId();

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    @SuppressWarnings("unchecked")
    public <T extends BaseEntity, R extends BaseDTO> R to(Function<T, R> fun) {
        return fun.apply((T) this);
    }
}
