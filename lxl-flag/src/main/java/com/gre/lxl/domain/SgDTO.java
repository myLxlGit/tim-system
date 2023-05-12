package com.gre.lxl.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author lxl
 * @date 2023/4/27 17:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SgDTO {

    private String id;

    private String no;

    private BigDecimal amt;

    private BigDecimal qty;

}
