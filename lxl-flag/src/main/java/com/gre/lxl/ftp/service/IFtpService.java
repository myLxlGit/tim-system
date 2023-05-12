package com.gre.lxl.ftp.service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author lxl
 * @date 2022/7/21 9:24
 */
public interface IFtpService {

    /**
     * 下载文件‘
     * ’
     *
     * @param path 路径
     * @param fileName 文件名
     * @param httpServletResponse 响应
     */
    void downloadFileByFtp(String path, String fileName, HttpServletResponse httpServletResponse);

}
