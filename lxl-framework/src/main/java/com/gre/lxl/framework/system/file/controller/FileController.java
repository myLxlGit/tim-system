package com.gre.lxl.framework.system.file.controller;

import com.gre.lxl.common.core.controller.BaseController;
import com.gre.lxl.common.core.domain.model.AjaxResult;
import com.gre.lxl.framework.system.file.dto.FileDTO;
import com.gre.lxl.framework.system.file.service.IFileService;
import com.gre.lxl.framework.utils.ImageUtil;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 文件通用接口
 *
 * @author lxl
 * @date 2021/6/22 9:27
 */
@Controller
@RequestMapping("/attach")
public class FileController extends BaseController {

    @Autowired
    private IFileService fileService;

    /**
     * 将图片转换为jpeg格式，jpeg为有损压缩，可以大幅压缩图片尺寸
     * @param file 待转换的图片文件
     * @return 转换成功后的base64字符串
     */
    @PostMapping("convert")
    @ResponseBody
    public AjaxResult convertToJpeg(@ApiIgnore MultipartFile file) {

        try {
            OutputStream outputStream = ImageUtil.convertToJpeg(file.getInputStream());
            byte[] encode = Base64.getEncoder().encode(((ByteArrayOutputStream) outputStream).toByteArray());
            return AjaxResult.success("转换成功", new String(encode, StandardCharsets.UTF_8));
        } catch (IOException e) {
            logger.error("图片转换失败，{}", e.getMessage(), e);
            return AjaxResult.error("资源转换失败，请联系管理员");
        }
    }

    /**
     * base64 转文件  并且保存文件在本地
     *
     * @param fileDTO
     * @return
     * @throws IOException
     */
    @PostMapping("baseToFile")
    @ResponseBody
    public AjaxResult baseToFile(@RequestBody FileDTO fileDTO) throws IOException {
        MultipartFile file = fileService.baseToFile(fileDTO.getBase64());
        InputStream inputStream = file.getInputStream();
        System.out.println(inputStream);

        //文件转base64编码
        String[] suffixArra = file.getOriginalFilename().split("\\.");
        String preffix="data:image/jpg;base64,".replace("jpg", suffixArra[suffixArra.length - 1]);
        String base64EncoderImg=preffix + new BASE64Encoder().encode(file.getBytes()).replaceAll("[\\s*\t\n\r]", "");
        System.out.println(base64EncoderImg);

        return AjaxResult.success("转换成功");
    }


    /**
     * base64 转文件
     *
     * @param fileDTO
     * @return
     * @throws IOException
     */
    @PostMapping("base64ToFile")
    @ResponseBody
    public AjaxResult base64ToFile(@RequestBody FileDTO fileDTO) throws IOException {
        String base64 = fileDTO.getBase64();
        fileService.base64ToFile(base64);
        return AjaxResult.success("转换成功");
    }


}
