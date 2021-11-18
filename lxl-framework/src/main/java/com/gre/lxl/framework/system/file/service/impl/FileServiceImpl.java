package com.gre.lxl.framework.system.file.service.impl;

import com.gre.lxl.common.exception.FlowException;
import com.gre.lxl.framework.system.file.service.IFileService;
import org.springframework.core.env.Environment;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * @author lxl
 * @date 2021/6/22 14:26
 */
@Service
public class FileServiceImpl implements IFileService {

    @Override
    public MultipartFile baseToFile(String base64) throws IOException {
        File file = null;
        String fileName = "D:/9999.jpg";
        FileOutputStream out = null;
        FileInputStream fileIn = null;
        ByteArrayInputStream in = null;
        try {
            // 解码，然后将字节转换为文件
            file = new File(fileName);
            //base64编码转换成字节
            byte[] bytes = new BASE64Decoder().decodeBuffer(base64.trim());
            //转换字节数组输入流
            in = new ByteArrayInputStream(bytes);
            byte[] buffer = new byte[1024];
            out = new FileOutputStream(file);
            fileIn = new FileInputStream(file);
            int bytesum = 0;
            int byteread = 0;
            while ((byteread = in.read(buffer)) != -1) {
                bytesum += byteread;
                out.write(buffer, 0, byteread); // 文件写操作
            }
        } catch (IOException e) {
            throw new FlowException(e.getMessage());
        } finally {
            try {
                if (out!= null) {
                    out.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //将File格式转换为MultipartFile格式
        return new MockMultipartFile(file.getName(), fileIn);
    }

    @Override
    public void base64ToFile(String base64) {
        try{
            //base 64 解码转字节
            byte[] bytes = new BASE64Decoder().decodeBuffer(base64.trim());
            //转换后的字节写入 输入流中
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);




        } catch (IOException e) {
            throw new FlowException(e.getMessage());
        }

    }
}
