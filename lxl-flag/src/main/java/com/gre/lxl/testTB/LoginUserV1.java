package com.gre.lxl.testTB;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lxl
 * @date 2022/6/23 18:46
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserV1 implements Serializable {

    private String name;

    private String password;

}
