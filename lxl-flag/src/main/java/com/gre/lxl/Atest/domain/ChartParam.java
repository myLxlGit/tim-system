package com.gre.lxl.Atest.domain;

import com.google.common.collect.Lists;
import com.gre.lxl.common.exception.CustomException;
import lombok.Data;

/**
 * @author lxl
 * @date 2022/8/26 16:42
 */
@Data
public class ChartParam {

    /**
     * 图表类型
     */
    private String chartType;

    /**
     * 合同类型 PO采购 OM销售
     */
    private String contractType;

    /**
     * 客商编码
     */
    private String merchantCode;

    /**
     * 时间范围 周week、月month、季quarter、年year
     */
    private String timeInterval;

    /**
     * 时间格式按照timeInterval来
     * iw 周
     * yyyy-MM 月
     * q 季度
     * yyyy 年
     */
    private String dateFormat;

    public String getDateFormat() {
        if (!Lists.newArrayList("week","month","quarter","year").contains(timeInterval)) {
            throw new CustomException("时间范围错误！");
        }
        if ("week".equals(timeInterval)) {
            dateFormat = "iw";
        } else if ("month".equals(timeInterval)) {
            dateFormat = "yyyy-MM";
        } else if ("quarter".equals(timeInterval)) {
            dateFormat = "q";
        } else if ("year".equals(timeInterval)) {
            dateFormat = "yyyy";
        }
        return dateFormat;
    }
}
