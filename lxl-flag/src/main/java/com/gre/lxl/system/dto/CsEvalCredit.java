package com.gre.lxl.system.dto;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author LXL
 * @since 2021-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CsEvalCredit implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String evalId;

    private String creditAmount;


}
