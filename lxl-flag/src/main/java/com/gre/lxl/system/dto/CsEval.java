package com.gre.lxl.system.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author LXL
 * @since 2021-06-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CsEval implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Integer id;

    @TableField("NAME")
    private String name;

    @TableField(exist = false)
    private List<CsEvalSup> csEvalSups;

    @TableField(exist = false)
    private CsEvalCredit csEvalCredit;
}
