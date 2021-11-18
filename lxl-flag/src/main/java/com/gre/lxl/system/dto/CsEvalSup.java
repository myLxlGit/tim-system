package com.gre.lxl.system.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2021-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CsEvalSup implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Integer id;

    @TableField("EVAL_ID")
    private Integer evalId;

    @TableField("SUP_NAME")
    private String supName;


}
