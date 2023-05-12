package com.gre.lxl;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 文件通知报文
 *
 * @author lxl
 * @date 2022/7/18 15:26
 */
@Getter
@Setter
@XStreamAlias("ROOT")
@Builder
public class FileNoticeReq {

    /**
     * 交易码 SMDFileToGj
     */
//    @XStreamAlias("TRANCODE")
    @XStreamAlias("TRX_CODE")
    private String tranCode;

    /**
     * 路径
     */
    @XStreamAlias("srcpath")
    private String srcPath;

    /**
     * 文件名称
     */
    @XStreamAlias("FILE_NAME")
    private String fileName;


}
