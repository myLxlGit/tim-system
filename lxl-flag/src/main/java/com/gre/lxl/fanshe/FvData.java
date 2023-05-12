package com.gre.lxl.fanshe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lxl
 * @date 2022/7/1 14:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FvData {

    private String fvKey;

    private String fvValue;

    private void fvOf() {
        System.out.println("fv HelloFv!!");
    }

}
