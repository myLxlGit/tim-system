package com.gre.lxl.testDelay.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lxl
 * @date 2022/6/16 16:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DelayUser implements Serializable {

    private String userId;

    private String  userName;

}
