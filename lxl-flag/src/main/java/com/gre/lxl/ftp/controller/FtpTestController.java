package com.gre.lxl.ftp.controller;

import com.gre.lxl.common.core.domain.model.AjaxResult;
import com.gre.lxl.ftp.service.IFtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author lxl
 * @date 2022/7/21 9:22
 */
@RequestMapping("/ftp")
@RestController
public class FtpTestController {

    @Autowired
    private IFtpService ftpService;

    /**
     * 下载文件
     * @param fileName
     * @param httpServletResponse
     */
    @GetMapping({"downloadFileByFtp/{path}/{fileName}","downloadFileByFtp/{fileName}.pdf"})
    public void downloadFileByFtp(@PathVariable("path") String path, @PathVariable("fileName") String fileName, HttpServletResponse httpServletResponse) {
        ftpService.downloadFileByFtp(path,fileName,httpServletResponse);
    }

    @PostMapping(value="/test",produces = {"application/xml;charset=UTF-8"})
    public void test() {

    }

}
