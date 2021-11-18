package com.gre.lxl.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lxl
 * @date 2021/11/7 16:59
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("RC_TEACHER")
public class RcTeacher implements Serializable {

    @TableId("TEACHER_ID")
    private int teacherId;

    @TableField("TEACHER_DEPARTMENT_ID")
    private int teacherDepartmentId;

    @TableField("TEACHER_NUMBER")
    private String teacherNumber;

    @TableField("TEACHER_NAME")
    private String teacherName;

    @TableField("TEACHER_PASSWORD")
    private String teacherPassword;

}
