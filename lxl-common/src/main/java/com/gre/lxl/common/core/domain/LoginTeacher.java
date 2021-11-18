package com.gre.lxl.common.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lxl
 * @date 2021/11/7 17:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginTeacher implements Serializable {

    private int teacherId;

    private int teacherDepartmentId;

    private String teacherNumber;

    private String teacherName;

    private String teacherPassword;
}
