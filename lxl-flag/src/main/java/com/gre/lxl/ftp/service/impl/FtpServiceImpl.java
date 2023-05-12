package com.gre.lxl.ftp.service.impl;

import com.alibaba.fastjson.JSON;
import com.gre.lxl.common.util.CreateFileUtils;
import com.gre.lxl.common.util.ServletUtils;
import com.gre.lxl.ftp.service.IFtpService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * @author lxl
 * @date 2022/7/21 9:26
 */
@Service
public class FtpServiceImpl implements IFtpService {


    @Override
    public void downloadFileByFtp(String remotePath, String fileName, HttpServletResponse response) {

        String localPath = "lcFileFromFtp";
        try {
            boolean b = FtpClient.downloadFile(remotePath + "/20220719" , fileName, localPath);
            if (b) {
                File file = new File(localPath + "/" + fileName);
                FileInputStream fileInputStream = new FileInputStream(file);
                //支持 浏览器下载文件
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
                response.setContentType("application/octet-stream");
                response.addHeader("Attach-Status", "1");

                IOUtils.copy(fileInputStream, response.getOutputStream());
                response.getOutputStream().close();
                //释放资源 为了后边删除文件
                fileInputStream.close();
            }else {
                response.setContentType("application/json");
                ServletUtils.renderString(response, "下载失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //删除本地文件夹
        CreateFileUtils.deleteDir(new File(localPath));

    }
}
