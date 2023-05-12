package com.gre.lxl.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * 创建文件
 *
 * @author lxl
 * @date 2022/6/15 9:46
 */
@Slf4j
public class CreateFileUtils {

    /**
     * 创建目录
     *
     * @param destDirName 目录地址
     * @return true/false
     */
    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            log.info("创建目录{}失败，目标目录已经存在", destDirName);
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        //创建目录
        if (dir.mkdirs()) {
            log.info("创建目录{}成功！", destDirName);
            return true;
        } else {
            log.info("创建目录{}失败！", destDirName);
            return false;
        }
    }

    /**
     * 创建单个文件
     *
     * @param destFileName 路径
     * @return true/false
     */
    public static boolean createFile(String destFileName) {
        File file = new File(destFileName);
        if (file.exists()) {
            log.info("创建单个文件{}失败。目标已存在！", destFileName);
            return false;
        }
        if (destFileName.endsWith(File.separator)) {
            log.info("创建单个文件{}失败。目标文件不能为目录！", destFileName);
            return false;
        }
        //判断目标文件所在的目录是否存在
        if (!file.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            log.info("目标文件所在目录不存在，准备创建它！");
            if (!file.getParentFile().mkdirs()) {
                log.info("创建目标文件所在为目录失败！");
                return false;
            }
        }
        //创建目标文件
        try {
            if (file.createNewFile()) {
                log.info("创建单个文件{}成功！", destFileName);
                return true;
            } else {
                log.info("创建单个文件{}失败！", destFileName);
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            log.error("创建单个文件{}失败！,{}", destFileName, e.getMessage());
            return false;
        }

    }

    /**
     * 创建临时文件
     *
     * @param prefix  前缀
     * @param suffix  后缀
     * @param dirName 目录路径
     * @return 返回临时文件的路径
     */
    public static String createTempFile(String prefix, String suffix, String dirName) {
        File tempFile = null;
        if (dirName == null) {
            try {
                //在默认文件夹下创建临时文件
                tempFile = File.createTempFile(prefix, suffix);
                //返回临时文件的路径
                return tempFile.getCanonicalPath();
            } catch (IOException e) {
                e.printStackTrace();
                log.error("创建临时文件失败！, {}", e.getMessage());
                return null;
            }
        } else {
            File dir = new File(dirName);
            //如果临时文件所在目录不存在，首先创建
            if (!dir.exists()) {
                if (!createDir(dirName)) {
                    log.info("创建临时文件失败，不能创建临时文件所在的目录！");
                    return null;
                }
            }
            try {
                //在指定目录下创建临时文件
                tempFile = File.createTempFile(prefix, suffix, dir);
                return tempFile.getCanonicalPath();
            } catch (IOException e) {
                e.printStackTrace();
                log.error("创建临时文件失败！{}", e.getMessage());
                return null;
            }
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i< Objects.requireNonNull(children).length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}
