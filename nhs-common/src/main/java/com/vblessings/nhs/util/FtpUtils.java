package com.vblessings.nhs.util;

import cn.hutool.extra.spring.SpringUtil;
import com.vblessings.nhs.ftp.FtpBatchDelPO;
import com.vblessings.nhs.ftp.FtpProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.logging.log4j.util.Strings;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * ftp 工具类  上传 下载
 */
@Slf4j
public class FtpUtils {
    /**
     * 本地字符编码
     */
    private static String LOCAL_CHARSET = "GBK";

    /**
     * FTP协议里面，规定文件名编码为iso-8859-1
     */
    private static final String SERVER_CHARSET = "ISO-8859-1";

    private static final String PATH_LINE = "/";

    private static final String OPTS = "OPTS UTF8";

    private static final String STATUS = "ON";

    private static FtpProperties ftpProperties;

    /**
     * 根据配置获取ftp客户端
     *
     * @return ftp 客户端
     */
    private static FTPClient getClient() {
        if (Objects.isNull(ftpProperties)) {
            ftpProperties = SpringUtil.getBean(FtpProperties.class);
        }
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ftpProperties.getHost(), ftpProperties.getPort());
            ftpClient.login(ftpProperties.getUser(), ftpProperties.getPassword());
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                log.error("连接到ftp失败,用户名或者密码错误");
                ftpClient.disconnect();
            } else {
                log.info("ftp连接成功!");
            }
        } catch (IOException e) {
            log.error("ftp连接失败!{}", e.getMessage());
        }
        return ftpClient;
    }


    /**
     * 文件上传,支持追加路径
     *
     * @param path        文件路径
     * @param deleteLocal 上传成功后是否删除本地文件
     * @return 文件名
     * @throws IOException io
     */
    public static String uploadFile(String path, Boolean deleteLocal) throws IOException {
        File file = new File(path);
        return uploadFileImpl(file, deleteLocal);
    }


    /**
     * 文件上传
     *
     * @param file        文件
     * @param deleteLocal 上传成功后是否删除本地文件
     * @return 文件名
     * @throws IOException io
     */
    public static String uploadFile(File file, Boolean deleteLocal) throws IOException {
        return uploadFileImpl(file, deleteLocal);
    }


    /**
     * 批量上传
     *
     * @param fileMap     文件map  key是标识
     * @param deleteLocal 是否删除本地文件
     * @param addPath     追加路径,默认追加在工作目录后
     * @return 文件名
     */
    public static Map<String, String> batchUploadFile(Map<String, File> fileMap, Boolean deleteLocal, String addPath) {
        Map<String, String> result = new HashMap<>();
        try {
            result = batchUploadFileImpl(fileMap, deleteLocal, addPath);
        } catch (IOException e) {
            log.error("批量上传文件失败");
        }
        return result;
    }


    /**
     * 批量上传
     *
     * @param fileMap     文件map  key是标识
     * @param deleteLocal 是否删除本地文件
     * @return 文件名
     */
    public static Map<String, String> batchUploadFile(Map<String, File> fileMap, Boolean deleteLocal) {
        Map<String, String> result = new HashMap<>();
        try {
            result = batchUploadFileImpl(fileMap, deleteLocal, "");
        } catch (IOException e) {
            log.error("批量上传文件失败");
        }
        return result;
    }

    /**
     * 文件上传实现
     *
     * @param file        文件
     * @param deleteLocal 上传成功后是否删除本地文件
     * @return 文件名
     */
    private static String uploadFileImpl(File file, Boolean deleteLocal) throws IOException {
        FTPClient ftpClient = getClient();
        String finalName = file.getName();
        if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
            log.info("连接ftp失败");
            return "";
        }
        try {
            if (!recurDictionary(ftpClient, true)) {
                log.error("创建目录失败!");
                return "";
            }
            // 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
            if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(OPTS, STATUS))) {
                LOCAL_CHARSET = "UTF-8";
            }
            String filename = new String(file.getName().getBytes(LOCAL_CHARSET), SERVER_CHARSET);
            FileInputStream fileInputStream = new FileInputStream(file);
            ftpClient.setControlEncoding(LOCAL_CHARSET);
            // 设置被动模式
            ftpClient.enterLocalPassiveMode();
            // 设置上传文件的类型为二进制类型
            // 设置传输的模式
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            // 上传文件
            if (!ftpClient.storeFile(filename, fileInputStream)) {
                finalName = "";
            }
            fileInputStream.close();
            if (deleteLocal) {
                // 上传之后删除本地文件
                boolean delete = file.delete();
                if (!delete) {
                    log.error("删除文件失败!");
                }
            }
        } catch (Exception e) {
            log.error("上传文件到ftp失败:{}", e.getMessage());
        } finally {
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                //关闭连接
                ftpClient.disconnect();
            }
        }
        if (Strings.isNotBlank(finalName)) {
            log.info("文件上传成功:{}", finalName);
        }
        return finalName;
    }

    /**
     * 批量上传
     *
     * @param fileMap     文件map  key是标识
     * @param deleteLocal 是否删除本地文件
     * @param addPath     追加路径,默认追加在工作目录后
     * @return 文件名
     */
    private static Map<String, String> batchUploadFileImpl(Map<String, File> fileMap, Boolean deleteLocal, String addPath) throws IOException {
        Map<String, String> resultMap = new HashMap<>();
        FTPClient ftpClient = getClient();
        if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
            log.info("连接ftp失败");
            return resultMap;
        }
        try {
            if (!recurDictionary(ftpClient, true)) {
                log.error("创建目录失败!");
                return resultMap;
            }
            // 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
            if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(OPTS, STATUS))) {
                LOCAL_CHARSET = "UTF-8";
            }
            for (Map.Entry<String, File> entry : fileMap.entrySet()) {
                File file = entry.getValue();
                if (Objects.isNull(file)) {
                    continue;
                }
                String finalName = file.getName();
                String filename = new String(finalName.getBytes(LOCAL_CHARSET), SERVER_CHARSET);
                FileInputStream fileInputStream = new FileInputStream(file);
                ftpClient.setControlEncoding(LOCAL_CHARSET);
                // 设置被动模式
                ftpClient.enterLocalPassiveMode();
                // 设置上传文件的类型为二进制类型
                // 设置传输的模式
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                // 上传文件
                if (!ftpClient.storeFile(filename, fileInputStream)) {
                    finalName = "";
                }
                fileInputStream.close();
                if (deleteLocal) {
                    // 上传之后删除本地文件
                    boolean delete = file.delete();
                    if (!delete) {
                        log.error("删除文件失败!");
                    }
                }
                resultMap.put(entry.getKey(), finalName);
            }
        } catch (IOException e) {
            log.error("上传文件到ftp失败:{}", e.getMessage());
        } finally {
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                //关闭连接
                ftpClient.disconnect();
            }
        }
        return resultMap;
    }

    /**
     * 下载文件
     *
     * @param fileName     文件名称
     * @param outputStream 输出流
     */
    public static boolean downloadFile(String fileName, OutputStream outputStream) throws IOException {
        return downloadFileImpl(fileName, outputStream);
    }


    /**
     * 下载文件实现
     *
     * @param fileName     文件名称
     * @param outputStream 输出流
     */
    private static boolean downloadFileImpl(String fileName, OutputStream outputStream) throws IOException {
        FTPClient ftpClient = getClient();
        boolean retrieveFile = false;
        try {
            //切换工作目录
            if (!recurDictionary(ftpClient, false)) {
                log.error("切换工作目录失败!");
                return false;
            }
            // 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
            if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(OPTS, STATUS))) {
                LOCAL_CHARSET = "UTF-8";
            }
            ftpClient.setControlEncoding(LOCAL_CHARSET);
            // 设置被动模式
            ftpClient.enterLocalPassiveMode();
            // 设置传输的模式
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            // 上传文件
            // 对中文文件名进行转码，否则中文名称的文件下载失败
            String fileNameTemp = new String(fileName.getBytes(LOCAL_CHARSET), SERVER_CHARSET);
            // 流接收文件
            retrieveFile = ftpClient.retrieveFile(fileNameTemp, outputStream);
            if (retrieveFile) {
                log.info("下载成功:{}", fileName);
            } else {
                log.info("文件下载失败: {}",fileName);
            }
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            log.error("文件读取错误:{}",e.getMessage());
        } finally {
            //退出登录
            ftpClient.logout();
            //关闭连接
            ftpClient.disconnect();
        }
        return retrieveFile;
    }


    /**
     * 删除文件
     *
     * @param fileName 文件名称
     * @return 是否删除成功
     */
    public static Boolean delFile(String fileName) throws IOException {
        return delFileImpl(fileName);
    }

    /**
     * 删除文件实现
     *
     * @param fileName 文件名
     * @return 是否删除成功
     */
    private static Boolean delFileImpl(String fileName) throws IOException {
        FTPClient ftpClient = getClient();
        try {
            //切换工作目录
            if (!recurDictionary(ftpClient, false)) {
                log.error("切换工作目录失败!");
                return false;
            }
            return ftpClient.deleteFile(fileName);
        } catch (Exception e) {
            log.error("删除失败{}", e.getMessage());
        } finally {
            //退出登录
            ftpClient.logout();
            //关闭连接
            ftpClient.disconnect();
        }
        return false;
    }

    public static Boolean batchDelFile(FtpBatchDelPO ftpBatchDelPo) throws IOException {
        FTPClient ftpClient = getClient();
        //切换工作目录
        try {
            if (!recurDictionary(ftpClient, false)) {
                log.error("切换工作目录失败!");
                return false;
            }
        } catch (IOException e) {
            log.error("切换工作目录失败!");
            return false;
        }
        try {
            for (String s : ftpBatchDelPo.getFtpName()) {
                ftpClient.deleteFile(s);
            }
        } catch (Exception e) {
            log.error("删除失败{}", e.getMessage());
        } finally {
            //退出登录
            ftpClient.logout();
            //关闭连接
            ftpClient.disconnect();
        }
        return true;
    }

    /**
     * 是否存在
     *
     * @param fileName 文件名
     * @return true/false
     */
    public static Boolean isExist(String fileName) {
        FTPClient ftpClient = getClient();
        try {
            //切换工作目录
            if (!recurDictionary(ftpClient, false)) {
                log.error("切换工作目录失败!");
                return false;
            }
            InputStream inputStream = ftpClient.retrieveFileStream(fileName);
            return inputStream != null || ftpClient.getReplyCode() != 550;
        } catch (Exception e) {
            log.error("切换工作目录失败!");
        }
        return false;
    }

    /**
     * 递归创建/切换多级目录
     *
     * @param ftpClient 客户端
     * @param make      是否创建
     */
    private static boolean recurDictionary(FTPClient ftpClient, Boolean make) throws IOException {
        String addPath = ftpProperties.getWorkspace();
        if (Strings.isBlank(addPath)) {
            return true;
        }
        String workPath = ftpClient.printWorkingDirectory();
        String[] split = addPath.split(PATH_LINE);
        for (String s : split) {
            if (ftpClient.changeWorkingDirectory(workPath)) {
                if (Strings.isNotBlank(s)) {
                    if (make) {
                        ftpClient.makeDirectory(s);
                    }
                    workPath += PATH_LINE + s;
                }
            } else {
                String info = make ? "创建" : "切换";
                log.info(info + "工作目录失败...");
                break;
            }
        }
        return ftpClient.changeWorkingDirectory(workPath);
    }

    /**
     * 获取文件Url
     */
    public static String getAccessPath() {
        if (Objects.isNull(ftpProperties)) {
            ftpProperties = SpringUtil.getBean(FtpProperties.class);
        }
        return ftpProperties.getAccessPath();
    }

    /**
     * 获取ftp路径
     */
    public static String getWorkspace() {
        if (Objects.isNull(ftpProperties)) {
            ftpProperties = SpringUtil.getBean(FtpProperties.class);
        }
        return ftpProperties.getWorkspace();
    }
}
