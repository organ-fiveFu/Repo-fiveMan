package com.vblessings.nhs.ftp;

import lombok.Data;

import java.util.List;

/**
 * ftp文件批量删除入参
 */
@Data
public class FtpBatchDelPO {

    /**
     * ftp服务器文件名称集合
     */
    private List<String> ftpName;

    /**
     * ftp路径
     */
    private String ftpPath = "";


    public FtpBatchDelPO() {

    }

    public FtpBatchDelPO(List<String> ftpName, String ftpPath) {
        this.ftpName = ftpName;
        this.ftpPath = ftpPath;
    }

    public FtpBatchDelPO(List<String> ftpName){
        this.ftpName = ftpName;
    }
}
