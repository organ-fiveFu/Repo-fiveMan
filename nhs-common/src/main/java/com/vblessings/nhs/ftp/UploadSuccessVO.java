package com.vblessings.nhs.ftp;

import lombok.Data;


/**
 * 文件上传ftp成功回参
 *
 * 说明: ftpName是随机生成的 18位 uuid,防止文件名重复覆盖问题,docName 是原文件名称 ，两者之间映射
 * ftpPath 是自定义的路径   docUrl 就是文件所在的http路径
 */
@Data
public class UploadSuccessVO {

    /**
     * 文件名称
     */
    private String docName;

    /**
     * ftp服务器文件名称
     */
    private String ftpName;

    /**
     * 文件url
     */
    private String docUrl;

    /**
     * ftp路径
     */
    private String ftpPath;

}
