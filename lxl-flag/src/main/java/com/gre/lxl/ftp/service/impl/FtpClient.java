package com.gre.lxl.ftp.service.impl;

import com.gre.lxl.common.util.CreateFileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;
import java.util.Optional;

/**
 * FTP服务器
 *
 * @author lxl
 * @date 2022/7/14 11:30
 */
public class FtpClient {

    private final static Logger logger = LoggerFactory.getLogger(FtpClient.class);

    //初始化FTP
    private static FTPClient ftpClient = null;
    //本地字符编码
    static String LOCAL_CHARSET = "GBK";
    // FTP协议里面，规定文件名编码为iso-8859-1
    static String SERVER_CHARSET = "iso-8859-1";

    /**
     * 初始化FTP服务器
     */
    public static boolean initFtpClient() {
        boolean initFlag = false;
        ftpClient = new FTPClient();
        //测试环境
//        String ftpIp = "61.132.59.215";
//        String port = "21";
//        String userName = "citic";
//        String pwd = "Citic_fu123";
//
        //本机环境
        String ftpIp = "10.4.27.142";
        String port = "21";
        String userName = "ftp1";
        String pwd = "ftp1";

        try {
            //连接ftp服务器
            ftpClient.connect(ftpIp, Integer.parseInt(port));
            //登录ftp服务器
            ftpClient.login(userName, pwd);
            //是否成功登录服务器
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                logger.info("connect failed...ftp服务器:" + ftpIp + ":" + port + ",账号或密码错误！");
            } else {
                logger.info("connect successfu...ftp服务器:" + ftpIp + ":" + port);
                initFlag = true;
            }
        } catch (Exception e) {
            logger.error("初始化FTP失败：" + e.getMessage());
            e.printStackTrace();
        }
        return initFlag;
    }

    /**
     * 上传文件
     *
     * @param pathName    ftp服务保存地址
     * @param fileName    上传到ftp的文件名
     * @param inputStream 输入文件流
     * @return
     */
    public static boolean uploadFile(String pathName, String fileName, InputStream inputStream) {
        boolean flag = false;
        try {
            //登录服务器
            boolean loginState = initFtpClient();
            if (loginState) {
                logger.info("开始上传文件");
                //创建目录 并且进入目录
                createDirectory(pathName);
                //设置文件类型(二进制)
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                //设置被动模式
                ftpClient.enterLocalPassiveMode();
                //防止中文乱码问题
                if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF8", "ON"))) {
                    LOCAL_CHARSET = "UTF-8";
                }
                ftpClient.setControlEncoding(LOCAL_CHARSET);
                //上传文件
                flag = ftpClient.storeFile(new String(fileName.getBytes(LOCAL_CHARSET),
                        SERVER_CHARSET), inputStream);
                logger.info("上传状态：" + flag);
                inputStream.close();
                //退出
                ftpClient.logout();
            }
        } catch (Exception e) {
            logger.error("上传文件失败");
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**下载文件
     *
     * @param pathName FTP服务器文件目录
     * @param filename 文件名称
     * @param localPath 下载后的文件路径
     * @return */
    public static boolean downloadFile(String pathName, String filename, String localPath){
        boolean flag = false;
        OutputStream os = null;
        try {
            boolean loginState = initFtpClient();
            if (loginState) {
                logger.info("开始下载文件");
                // 下载文件
                ftpClient.setBufferSize(1024);
                //类型
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                //切换FTP目录
                boolean change = ftpClient.changeWorkingDirectory(pathName);
                if (!change) {
                    logger.error("切换FTP目录不存在！请核实路径是否正确");
                    return false;
                }
                //防止中文乱码问题
                ftpClient.setControlEncoding(LOCAL_CHARSET);
                //一定要加上
                ftpClient.enterLocalPassiveMode();
                FTPFile[] ftpFiles = ftpClient.listFiles();
                Optional<FTPFile> first = Arrays.stream(ftpFiles).filter(file -> filename.equalsIgnoreCase(file.getName())).findFirst();
                if (first.isPresent()) {
                    FTPFile ftpFile = first.get();
                    //拿到文件流 （文件名称 转化输出的编码）
                    InputStream inputStream = ftpClient.retrieveFileStream(new String(ftpFile.getName().getBytes(LOCAL_CHARSET),SERVER_CHARSET));
                    //创建本地文件 file
                    boolean cre = CreateFileUtils.createFile(localPath + "/" + ftpFile.getName());
                    if (cre) {
                        int bytesRead = 0;
                        File file1 = new File(localPath + "/" + ftpFile.getName());
                        os = new FileOutputStream(file1);
                        byte[] buffer = new byte[8192];
                        while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                            os.write(buffer, 0, bytesRead);
                        }
                        os.close();
                        inputStream.close();
                    }
                    flag = true;
                    logger.info("下载文件成功");
                } else {
                    logger.error("文件不存在");
                }
                //退出
                ftpClient.logout();
            }
        } catch (Exception e) {
            System.out.println("下载文件失败");
            e.printStackTrace();
        } finally{
            if(ftpClient.isConnected()){
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(null != os){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**下载文件
     *
     * @param pathName FTP服务器文件目录
     * @param filename 文件名称
     * @return */
    public static InputStream downloadFile(String pathName, String filename){
        InputStream is = null;
        try {
            boolean loginState = initFtpClient();
            if (loginState) {
                logger.info("开始下载文件");
                // 下载文件
                ftpClient.setBufferSize(1024);
                //类型
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                //切换FTP目录
                boolean change = ftpClient.changeWorkingDirectory(pathName);
                if (!change) {
                    logger.error("切换FTP目录不存在！请核实路径是否正确");
                    return null;
                }
                //防止中文乱码问题
                ftpClient.setControlEncoding(LOCAL_CHARSET);
                //一定要加上
                ftpClient.enterLocalPassiveMode();
                FTPFile[] ftpFiles = ftpClient.listFiles();
                Optional<FTPFile> first = Arrays.stream(ftpFiles).filter(file -> filename.equalsIgnoreCase(file.getName())).findFirst();
                if (first.isPresent()) {
                    FTPFile ftpFile = first.get();
                    //拿到文件流 （文件名称 转化输出的编码）
                    is = ftpClient.retrieveFileStream(new String(ftpFile.getName().getBytes(LOCAL_CHARSET),SERVER_CHARSET));
                }
                //退出
                ftpClient.logout();
            }
        } catch (Exception e) {
            System.out.println("下载文件失败");
            e.printStackTrace();
        }
        return is;
    }


    /**
     * 创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
     *
     * @param remote 路径
     * @throws IOException
     */
    public static void createDirectory(String remote) throws IOException {
        String directory = remote + "/";
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(directory)) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            }
            end = directory.indexOf("/", start);
            String path = "";
            do {
                String subDirectory = new String(remote.substring(start, end).getBytes(LOCAL_CHARSET), SERVER_CHARSET);
                path = path + "/" + subDirectory;
                if (!existFile(path)) {
                    if (!makeDirectory(subDirectory)) {
                        logger.error("创建目录[" + subDirectory + "]失败");
                    }
                }
                changeWorkingDirectory(subDirectory);
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
            } while (end > start);
        }
    }

    /**
     * 改变目录路径
     *
     * @param directory 目录路径
     * @return true | false
     */
    public static boolean changeWorkingDirectory(String directory) {
        boolean flag = true;
        try {
            flag = ftpClient.changeWorkingDirectory(directory);
            if (flag) {
                logger.info("进入文件夹" + directory + " 成功！");
            } else {
                logger.error("进入文件夹" + directory + " 失败！开始创建文件夹");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return flag;
    }

    /**
     * 判断ftp服务器文件是否存在
     *
     * @param path 路径
     * @return true | false
     * @throws IOException
     */
    public static boolean existFile(String path) throws IOException {
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 创建目录
     *
     * @param dir 目录
     * @return true | false
     */
    public static boolean makeDirectory(String dir) {
        boolean flag = true;
        try {
            flag = ftpClient.makeDirectory(dir);
            if (flag) {
                logger.info("创建文件夹" + dir + " 成功！");
            } else {
                logger.error("创建文件夹" + dir + " 失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

}
