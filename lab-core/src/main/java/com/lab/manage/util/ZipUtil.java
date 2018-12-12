package com.lab.manage.util;

import com.lab.manage.enums.FileEnum;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Chengcheng on 2018/3/23.
 * 压缩多文件打包zip
 * @version 1.0
 */
public abstract class ZipUtil {

    public static final String SERVER_PATH = "/tmp/";

//    public static final String SERVER_PATH = "/Users/chcc/workspace/";

    private static Logger logger = LoggerFactory.getLogger(ZipUtil.class);

    /**
     * @Author Chengcheng
     * @Description : 根据文件链接把文件下载下来并且转成字节码
     * @Date 2018/3/29 下午4:47
     */
    public static byte[] getImageFromURL(String urlPath) {
        byte[] data = null;
        InputStream is = null;
        HttpURLConnection conn = null;
        ByteArrayOutputStream baos = null;
        try {
            URL url = new URL(urlPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            // conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(6000);
            is = conn.getInputStream();
            if (conn.getResponseCode() == 200) {
                baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length = -1;
                while ((length = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, length);
                }
                baos.flush();
                data = baos.toByteArray();
            } else {
                data = null;
            }
        } catch (IOException e) {
            logger.error("IOException", e);
        } finally {
            try {
                if(is != null){
                    is.close();
                }
                if(baos != null){
                    baos.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                logger.error("IOException", e);
            }
            conn.disconnect();
        }
        return data;
    }

    /**
     * @Author Chengcheng
     * @Description : 单文件格式统一打包
     * @Date 2018/3/30 上午8:39
     * @param path 文件压缩路径
     * @param files 需要压缩文件
     * @param fileEnum 文件类型枚举
     */
    public static File zipFile(String path, FileEnum fileEnum, String...files){
        return zipFile(path,new FileEnum[]{fileEnum},files);
    }



    /**
     * @Author Chengcheng
     * @Description : 压缩多文件打包zip
     *                可以返回file文件通过输出流返回给客户端下载
     *                也可以指定path到物理路径通过tomcat下载
     * @Date 2018/3/23 上午9:57
     * @param path 文件压缩路径
     * @param files 需要压缩文件
     * @param fileEnum 文件类型枚举，可以添加多个格式类型 跟 文件数量 1：1
     */
    public static File zipFile(String path, FileEnum[] fileEnum , String ... files){
        if(StringUtils.isBlank(path)) path = SERVER_PATH + System.currentTimeMillis() + ".zip";
        ByteArrayOutputStream bos = null;
        ZipOutputStream zos = null;
        FileOutputStream outputStream = null;
        try {
            bos = new ByteArrayOutputStream();
            zos = new ZipOutputStream(bos);
            if(fileEnum.length == files.length){
                for (int i = 0; i < files.length; i++){
                    zos.putNextEntry(new ZipEntry("profile" + i + "." + fileEnum[i].getValue()));
                    byte[] bytes = getImageFromURL(files[i]);
                    zos.write(bytes, 0, bytes.length);
                    zos.closeEntry();
                }
            }else if(fileEnum.length == 1){
                for (int i = 0; i < files.length; i++){
                    zos.putNextEntry(new ZipEntry("profile" + i + "." + fileEnum[0].getValue()));
                    byte[] bytes = getImageFromURL(files[i]);
                    zos.write(bytes, 0, bytes.length);
                    zos.closeEntry();
                }
            }
            zos.close();
            File file = new File(path);
            outputStream = new FileOutputStream(file);
            outputStream.write(bos.toByteArray());
            return file;
        }catch (IOException e){
            logger.error("Exception :", e);
        }finally {
            try {
                if(outputStream != null){
                    outputStream.close();
                }
            }catch (IOException e){
                logger.error("Exception :", e);
            }
        }
        return null;
    }

}
