package com.gre.lxl.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gre.lxl.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * @author lxl
 * @date 2021/10/11 15:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("CS_PRJ_EVALUATION_EX")
public class CsPrjEvaluationEx extends BaseEntity implements Serializable {

    @TableId("ID")
    private String id;

    @TableField("PRJ_NAME")
    private String prjName;

    @TableField("INSTANCE_ID")
    private String instanceId;

    /**
     * 0 暂存 1审核中 2审核通过 3退回 4撤回 5 删除
     */
    @TableField("EVAL_STATE")
    private String evalState;

    @TableField("PRJ_NO")
    private String prjNo;

    @TableField("CREATE_USER")
    private String createUser;

    @TableField("CREATE_TIME")
    @JsonFormat(pattern = "HH:mm:ss")
    private Time createTime;

    @TableField("UPDATE_USER")
    private String updateUser;

    @TableField("UPDATE_TIME")
    private Date updateTime;


    @TableField(exist = false)
    @JsonFormat(pattern = "HH:mm:ss")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Time currentTime;
}
