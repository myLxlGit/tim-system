package com.gre.lxl.utils;

import org.apache.commons.codec.binary.Base64;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author dk
 * @date 2022/04/07 16:36
 **/
public class Base64DecodeMultipartFile implements MultipartFile {

    private final byte[] imgContent;
    private final String header;

    public Base64DecodeMultipartFile(byte[] imgContent, String header) {
        this.imgContent = imgContent;
        this.header = header.split(";")[0];
    }

    @NotNull
    @Override
    public String getName() {
        return System.currentTimeMillis() + Math.random() + "." + header.split("/")[1];
    }

    @Override
    public String getOriginalFilename() {
        return System.currentTimeMillis() + (int) (Math.random() * 10000) + "." + header.split("/")[1];
    }

    @Override
    public String getContentType() {
        return header.split(":")[1];
    }

    @Override
    public boolean isEmpty() {
        return imgContent == null || imgContent.length == 0;
    }

    @Override
    public long getSize() {
        return imgContent.length;
    }

    @NotNull
    @Override
    public byte[] getBytes() throws IOException {
        return imgContent;
    }

    @NotNull
    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(imgContent);
    }

    @Override
    public void transferTo(@NotNull File dest) throws IOException, IllegalStateException {
        new FileOutputStream(dest).write(imgContent);
    }

    public static MultipartFile base64ToMultipartFile(String base64) {
        String[] baseStrs = base64.split(",");
        byte[] b = Base64.decodeBase64(baseStrs[1]);
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {
                b[i] += 256;
            }
        }
        return new Base64DecodeMultipartFile(b, baseStrs[0]);
    }

}
