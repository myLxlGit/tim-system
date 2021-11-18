package com.gre.lxl.common.core.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 表格分页数据对象
 *
 * @author sumec
 */
@Data
@ApiModel
public class TableDataInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    @ApiModelProperty("总记录数")
    private long total;

    /**
     * 列表数据
     */
    @ApiModelProperty("列表数据")
    private List<?> rows;

    /**
     * 消息状态码
     */
    @ApiModelProperty("状态码")
    private int code;

    /**
     * 消息内容
     */
    @ApiModelProperty("消息内容")
    private String msg;

    /**
     * 表格数据对象
     */
    public TableDataInfo() {
    }

    /**
     * 分页
     *
     * @param list  列表数据
     * @param total 总记录数
     */
    public TableDataInfo(List<?> list, int total) {
        this.rows = list;
        this.total = total;
    }

    public static TableDataInfo defaultProps() {
        TableDataInfo dataInfo = new TableDataInfo();
        dataInfo.total = 0;
        dataInfo.rows = Collections.emptyList();
        dataInfo.msg = "查询成功";
        dataInfo.code = 200;
        return dataInfo;
    }

    public static TableDataInfo errorProps(String msg) {
        TableDataInfo dataInfo = new TableDataInfo();
        dataInfo.total = 0;
        dataInfo.rows = Collections.emptyList();
        dataInfo.msg = msg;
        dataInfo.code = 500;
        return dataInfo;
    }
}
