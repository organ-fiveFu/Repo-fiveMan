package com.vblessings.nhs.ftp;

import com.vblessings.nhs.exception.ResponseEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * ftp 参数
 */
@Configuration
@ConfigurationProperties(prefix = FtpProperties.FTP_PREFIX)
public class FtpProperties {

    public static final String FTP_PREFIX = "ftp";
    /**
     * host ip
     */
    private String host;
    /**
     * 端口 默认21
     */
    private Integer port = 21;

    /**
     * 用户名
     */
    private String user;
    /**
     * 密码
     */
    private String password;
    /**
     * 工作目录
     */
    private String workspace;
    /**
     * 访问路径
     */
    private String accessPath;
    /**
     * 文件允许类型,默认 PDF
     */
    private List<String> allowType = Collections.singletonList("PDF");
    /**
     * 文件限制大小 单位字节 默认 5M
     */
    private Long limitSize = 5242880L;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWorkspace() {
        return workspace;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    public String getAccessPath() {
        return accessPath;
    }

    public void setAccessPath(String accessPath) {
        this.accessPath = accessPath;
    }

    public List<String> getAllowType() {
        return allowType;
    }

    public void setAllowType(List<String> allowType) {
        this.allowType = allowType;
    }

    public Long getLimitSize() {
        return limitSize;
    }

    public void setLimitSize(Long limitSize) {
        this.limitSize = limitSize;
    }

    public void validFile(MultipartFile file) {
        // 校验文件大小及类型
        if (Objects.isNull(file.getContentType())) {
            throw ResponseEnum.BAD_REQUEST.newException("未获取到文件类型");
        }
        boolean flag = false;
        // 允许的文件类型
        for (String s : allowType) {
            if (file.getContentType().toUpperCase().contains(s.toUpperCase())) {
                flag=true;
                break;
            }
        }
        if(!flag){
            throw ResponseEnum.BAD_REQUEST.newException("文件类型仅支持" + allowType);
        }
        if (limitSize < file.getSize()) {
            throw ResponseEnum.BAD_REQUEST.newException("文件最大仅为" + limitSize / 1024 / 1024 + "M");
        }
    }
}
