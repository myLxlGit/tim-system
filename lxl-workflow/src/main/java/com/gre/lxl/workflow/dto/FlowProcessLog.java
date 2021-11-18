package com.gre.lxl.workflow.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author lxl
 * @date 2020/12/09 15:08
 */
@Data
public class FlowProcessLog {

    private Integer processNo;

    private String nodeName;

    private String starter;

    private String wither;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date endTime;

    private String comments;

    private Object operation;

    private String opinionsType;

    private String actWitherName;

    private Object fileLogDTOS;

    /**
     * 处理人id
     */
    private String actWither;

    /**
     * 处理人所有信息
     */
//    private SysUser actUser;

}
