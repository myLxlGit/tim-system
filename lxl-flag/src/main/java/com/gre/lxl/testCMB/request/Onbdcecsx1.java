package com.gre.lxl.testCMB.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;

/**
 * 信用证附件请求，用户填写贸易背景资料的附件信息。
 *
 * @author lxl
 * @date 2022/5/23 11:28
 */
@Data
@JsonPropertyOrder(alphabetic = true)
public class Onbdcecsx1 implements Serializable {

    /**
     * 文件 ID
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String filnbr;

    /**
     * 文件名
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String filnam;

    /**
     * 文件类型 支持的类型
     * "jpg", "gif", "png", "jpeg", "bmp", "doc", "docx", "xls", "xlsx", "xlsm", "ppt", "pptx", "pdf", "rar", "zip"
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String filtyp;

    /**
     * 文件长度 (字节长度)
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int fillen;

}
