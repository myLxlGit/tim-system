package com.gre.lxl.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lxl
 * @date 2021/11/18 10:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealParams {

    private List<CsPrjEvaluationEx> csPrjEvaluationExList;

}
