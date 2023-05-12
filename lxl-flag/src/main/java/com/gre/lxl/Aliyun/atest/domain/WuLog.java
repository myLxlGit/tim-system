package com.gre.lxl.Aliyun.atest.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 物流信息
 *
 * @author lxl
 * @date 2021/11/24 15:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WuLog implements Serializable {

    private String expName;
    private String takeTime;
    private String expSite;
    private String expPhone;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    private String type;

    private List<WuChild>  list;

    private String issign;
    private String number;
    private String deliverystatus;
    private String courier;
    private String logo;
    private String courierPhone;
}
