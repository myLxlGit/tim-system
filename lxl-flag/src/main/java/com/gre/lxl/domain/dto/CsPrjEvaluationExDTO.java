package com.gre.lxl.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gre.lxl.common.core.domain.BaseDTO;
import com.gre.lxl.domain.CsPrjEvaluationEx;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author lxl
 * @date 2021/11/17 13:53
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CsPrjEvaluationExDTO extends BaseDTO {

    private String id;

    private String prjName;

    private String prjNo;

    private String instanceId;

    private String evalState;

    private String createUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @JsonIgnore
    private String updateUser;

    @JsonIgnore
    private Date updateTime;

    public CsPrjEvaluationExDTO (CsPrjEvaluationEx ex) {
        this.id = ex.getId();
        this.prjName = ex.getPrjName();
        this.prjNo = ex.getPrjNo();
        this.instanceId = ex.getInstanceId();
        this.evalState = ex.getEvalState();
        this.createUser = ex.getCreateUser();
        this.createTime = ex.getCreateTime();
        this.updateTime = ex.getUpdateTime();
        this.updateUser = ex.getUpdateUser();
    }

}
