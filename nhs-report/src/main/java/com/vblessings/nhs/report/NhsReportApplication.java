package com.vblessings.nhs.report;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;


@Slf4j
@MapperScan(value={"org.jeecg.modules.**.mapper*"})
@SpringBootApplication(scanBasePackages = {"org.jeecg.modules.jmreport","com.vblessings.nhs.report.conifg"})
public class NhsReportApplication {
    public static void main(String[] args) throws UnknownHostException {
        // new application
        ConfigurableApplicationContext application = new SpringApplicationBuilder()
                .sources(NhsReportApplication.class)
                // default properties
                .web(WebApplicationType.SERVLET)
                .run(args);
        log.warn(">o< report服务启动成功！温馨提示：代码千万行，注释第一行，命名不规范，同事泪两行 >o<");
        Environment env = application.getEnvironment();
        // 是否启用https
        boolean httpsFlag = Boolean.valueOf(env.getProperty("server.ssl.enabled"));
        log.info("Swagger文档: \t\t{}://{}:{}/jmreport/list",httpsFlag?"https":"http", InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));

    }
}
