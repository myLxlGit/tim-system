package com.gre.lxl.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author lxl
 * @date 2021/6/15 14:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Student {

    @Value("${student.age}")
    private String age;

    @Value("${student.name}")
    private String name;
}
