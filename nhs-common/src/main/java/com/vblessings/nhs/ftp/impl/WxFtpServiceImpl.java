package com.vblessings.nhs.ftp.impl;

import cn.hutool.core.lang.Assert;
import com.vblessings.nhs.exception.ResponseEnum;
import com.vblessings.nhs.ftp.FtpBatchDelPO;
import com.vblessings.nhs.ftp.FtpBatchUploadPO;
import com.vblessings.nhs.ftp.UploadSuccessVO;
import com.vblessings.nhs.ftp.WxFtpProperties;
import com.vblessings.nhs.ftp.service.IWxFtpService;
import com.vblessings.nhs.util.MultipartFileToFile;
import com.vblessings.nhs.util.WxFtpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * ftp文件上传实现类
 *
 * @author dixing
 * @since 2021/3/1
 */
@Service
@Slf4j
public class WxFtpServiceImpl implements IWxFtpService {
    @Resource
    private WxFtpProperties wxFtpProperties;

    /**
     * 文件上传
     *
     * @param file 文件
     * @return 上传回参
     */
    @Override
    public UploadSuccessVO uploadFile(File file) {
        String realName = file.getName();
        File rename = MultipartFileToFile.rename(file);
        return uploadImpl(rename, realName);
    }

    /**
     * 文件上传
     *
     * @param multipartFile web文件
     * @return 上传回参
     */
    @Override
    public UploadSuccessVO upload(MultipartFile multipartFile) {
        // 文件校验
        wxFtpProperties.validFile(multipartFile);
        String realName = multipartFile.getOriginalFilename();
        // 转换成file并重命名
        File file = MultipartFileToFile.transFormAndRename(multipartFile);
        return uploadImpl(file, realName);
    }

    /**
     * @param file     文件
     * @param realName 原文件名称
     * @return UploadSuccessVO
     */
    private UploadSuccessVO uploadImpl(File file, String realName) {
        UploadSuccessVO successVO = new UploadSuccessVO();
        successVO.setDocName(realName);
        // 上传文件
        String ftpFileName;
        try {
            ftpFileName = WxFtpUtils.uploadFile(file, true);
        } catch (IOException e) {
            throw ResponseEnum.BAD_REQUEST.newException("文件上传失败");
        }
        if (Strings.isBlank(ftpFileName)) {
            throw ResponseEnum.BAD_REQUEST.newException("文件上传失败");
        }
        successVO.setFtpName(ftpFileName);
        String ftpUrl = normalFtpUrl();
        successVO.setDocUrl(ftpUrl + ftpFileName);
        successVO.setFtpPath(WxFtpUtils.getWorkspace());
        return successVO;
    }

    /**
     * 批量上传
     *
     * @param ftpBatchUploadPo 批量文件
     * @return 上传回参
     */
    @Override
    public Map<String, UploadSuccessVO> batchUpload(FtpBatchUploadPO ftpBatchUploadPo) {
        Map<String, File> uploadFiles = new HashMap<>();
        Map<String, String> realNameMap = new HashMap<>();
        Map<String, String> originalNameMapToKey = new HashMap<>();
        // 校验
        ftpBatchUploadPo.getFileMap().forEach((key, file) -> {
            wxFtpProperties.validFile(file);
            String realName = file.getOriginalFilename();
            uploadFiles.put(realName + key, MultipartFileToFile.transFormAndRename(file));
            originalNameMapToKey.put(realName + key, key);
            realNameMap.put(realName + key, realName);
        });
        Map<String, String> uploadFileMap = WxFtpUtils.batchUploadFile(uploadFiles, true, ftpBatchUploadPo.getAddPath());
        Map<String, UploadSuccessVO> result = new HashMap<>();
        uploadFileMap.forEach((key, value) -> {
            UploadSuccessVO vo = new UploadSuccessVO();
            vo.setDocName(realNameMap.get(key));
            String ftpUrl = normalFtpUrl();
            vo.setDocUrl(ftpUrl + value);
            vo.setFtpName(value);
            vo.setFtpPath(WxFtpUtils.getWorkspace());
            result.put(originalNameMapToKey.get(key), vo);
        });
        return result;
    }

    /**
     * 删除
     *
     * @param ftpName 文件名称
     * @return 是否删除成功
     */
    @Override
    public Boolean delete(String ftpName) {
        Assert.notBlank(ftpName, "删除文件名不能为空");
        // 不存在直接返回true
        Boolean exist = WxFtpUtils.isExist(ftpName);
        if (!exist) {
            return true;
        }
        Boolean delFile;
        try {
            delFile = WxFtpUtils.delFile(ftpName);
        } catch (IOException e) {
            log.error("文件删除失败:{}", ftpName);
            return false;
        }
        if (!delFile) {
            throw ResponseEnum.BAD_REQUEST.newException("文件删除失败");
        }
        return true;
    }

    /**
     * 批量删除
     *
     * @param ftpBatchDelPo 删除信息
     * @return 是否删除成功
     */
    @Override
    public Boolean batchDel(FtpBatchDelPO ftpBatchDelPo) {
        try {
            WxFtpUtils.batchDelFile(ftpBatchDelPo);
        } catch (IOException e) {
            log.error("删除文件失败:{}", e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * @return 拼接的路径
     */
    private String normalFtpUrl() {
//        String s = URLUtil.normalize(wxFtpProperties.getAccessPath() + "/" + wxFtpProperties.getWorkspace() + "/").replaceAll("http://", "");
        String s =  wxFtpProperties.getWorkspace() + "/";
        int i = s.indexOf("/");
        return s.substring(i);
    }

    @Override
    public Boolean downloadFile(String fileName, String localPath) {
        if (!localPath.endsWith(String.valueOf(File.separatorChar))) {
            localPath = localPath + File.separatorChar;
        }
        // 创建不同的文件夹目录
        File path = new File(localPath);
        // 判断文件夹是否存在
        if (!path.exists())
        {
            // 如果文件夹不存在，则创建新的的文件夹
            path.mkdirs();
        }
        boolean downSuccess = false;
        try {
            File file = new File(localPath + new String(fileName.getBytes("GBK"), StandardCharsets.UTF_8));
            // 判断本地是否已存在指定文件，不存在从ftp下载
            if (!file.exists()) {
                OutputStream fileOut = new FileOutputStream(file);
                downSuccess = WxFtpUtils.downloadFile(fileName, fileOut);
                fileOut.close();
            }
        } catch (FileNotFoundException e) {
            log.error("没有找到" + fileName + "文件");
        } catch (IOException e) {
            log.error("文件读取错误。");
        }
        return downSuccess;
    }
}
