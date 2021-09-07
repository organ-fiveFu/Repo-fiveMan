package com.vblessings.nhs.ftp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 批量上传入参
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FtpBatchUploadPO {

    /**
     * 文件映射表
     */
    private Map<String, MultipartFile> fileMap;

    /**
     * 文件追加路径
     */
    private String addPath;

}
