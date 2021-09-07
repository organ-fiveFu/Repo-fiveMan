package com.vblessings.nhs.ftp;

import lombok.Data;

/**
 * ftp文件删除入参
 */
@Data
public class FtpDelPO {
    /**
     * ftp服务器文件名称
     */
    private String ftpName;

    /**
     * ftp路径
     */
    private String ftpPath = "";


    public FtpDelPO() {

    }

    public FtpDelPO(String ftpName, String ftpPath) {
        this.ftpName = ftpName;
        this.ftpPath = ftpPath;
    }
}
