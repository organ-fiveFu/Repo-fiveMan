package com.vblessings.nhs.ftp.service;

import com.vblessings.nhs.ftp.FtpBatchDelPO;
import com.vblessings.nhs.ftp.FtpBatchUploadPO;
import com.vblessings.nhs.ftp.UploadSuccessVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

public interface IFtpService {

    /**
     * 文件上传
     *
     * @param file    文件
     * @return 上传回参
     */
    UploadSuccessVO uploadFile(File file);

    /**
     * 文件上传
     *
     * @param multipartFile web文件
     * @param workPath      工作目录
     * @return 上传回参
     */
    UploadSuccessVO upload(MultipartFile multipartFile, String workPath);

    /**
     * 批量上传
     *
     * @param ftpBatchUploadPo 批量文件信息
     * @return 上传回参
     */
    Map<String, UploadSuccessVO> batchUpload(FtpBatchUploadPO ftpBatchUploadPo);

    /**
     * 删除文件
     *
     * @param ftpName 文件名称
     * @return 是否删除成功
     */
    Boolean delete(String ftpName);

    /**
     * 批量删除
     *
     * @param ftpBatchDelPo 删除入参
     * @return 是否删除成功
     */
    Boolean batchDel(FtpBatchDelPO ftpBatchDelPo);

    /**
     * 下载文件到服务器
     * @param fileName  文件名称
     * @param localPath 本地路径
     * @return 是否下载成功
     */
    Boolean downloadFile(String fileName, String localPath) throws FileNotFoundException;
}
