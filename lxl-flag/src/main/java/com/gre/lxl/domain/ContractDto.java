package com.gre.lxl.domain;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dk
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractDto {

    @ApiModelProperty("合同号")
    private String contractNo;

    @ApiModelProperty("合同的路径")
    private String fileUrl;

    @ApiModelProperty("合同标题")
    private String docTitle;

//    @ApiModelProperty("参与签署的人")
//    private List<SignDto> signList;

    @ApiModelProperty("创建人CUSTOMERID")
    private String createCustomerId;

    @ApiModelProperty("创建人公司认证CUSTOMERID")
    private String companyCustomerId;

}
