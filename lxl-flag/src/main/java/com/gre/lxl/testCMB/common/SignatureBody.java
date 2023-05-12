package com.gre.lxl.testCMB.common;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 签名信息
 *
 * @author lxl
 * @date 2022/5/26 10:07
 */
@Data
@Builder(toBuilder = true)
@JsonPropertyOrder(alphabetic = true)
@AllArgsConstructor
@NoArgsConstructor
public class SignatureBody {

    private String sigtim;
    private String sigdat;

}
