package com.gre.lxl.framework.system.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author lxl
 * @date 2021/6/22 14:25
 */
public interface IFileService {

    MultipartFile baseToFile(String base64) throws IOException;

    void base64ToFile(String base64);
}
