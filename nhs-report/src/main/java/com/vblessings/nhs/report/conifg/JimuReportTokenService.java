package com.vblessings.nhs.report.conifg;

import org.apache.commons.lang.StringUtils;
import org.jeecg.modules.jmreport.api.JmReportTokenServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName : JmReportTokenService  //类名
 * @Description : 积木报表权限服务  //描述
 * @Author : GEWW  //作者
 * @Date: 2021-07-07 15:11  //时间
 */
@Component
public class JimuReportTokenService implements JmReportTokenServiceI {
    /**
     * 发起http 请求 header添加参数
     */
    public static final  String TOKEN_HEADER = "Authorization";

    @Resource
    private HttpServletRequest request;

    @Override
    public String getToken(HttpServletRequest request) {
        return request.getHeader(TOKEN_HEADER);
    }

    @Override
    public String getUsername(String s) {
        return "vblessings";
    }

    @Override
    public Boolean verifyToken(String s) {
        return true;
    }

    @Override
    public HttpHeaders customApiHeader() {
        HttpHeaders headers =new HttpHeaders();
        headers.add(TOKEN_HEADER,request.getHeader(TOKEN_HEADER));
        return headers;
    }

}
