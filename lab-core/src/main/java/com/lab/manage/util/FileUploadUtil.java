package com.lab.manage.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * Created by Chengcheng on 2018/4/30.
 */
public abstract class FileUploadUtil {

    private static final Logger LOG = LoggerFactory.getLogger(FileUploadUtil.class);

    /**
     * @Author Chengcheng
     * @Description : 文件上传到weed服务器中
     * @Date 2018/4/28 上午8:38
     * @return weed服务器保存路径
     * @param fileName 文件保存名称
     * @param file 文件上传的输入流
     */
    public static String upload(String fileName, InputStream file) {
//        FileSource fileSource = SpringUtil.getBean(FileSource.class);
//        try {
//            FileTemplate template = new FileTemplate(fileSource.getConnection());
//            FileHandleStatus status = template.saveFileByStream(fileName, file);
//            return status.getFileId();
//        } catch (Exception e) {
//            LOG.error(e.getMessage(), e);
//        }
        return null;
    }

}
