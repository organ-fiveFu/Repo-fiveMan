package com.vblessings.nhs;


import com.vblessings.nhs.holder.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@Slf4j
@EnableSwagger2
@ComponentScan({"com.vblessings.nhs.*"})
@Import(SpringContextHolder.class)
@MapperScan({"com.vblessings.nhs.mapper"})
public class BsHealthyApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = new SpringApplicationBuilder()
                .sources(BsHealthyApplication.class)
                // default properties
                .web(WebApplicationType.SERVLET)
                .run(args);
        log.warn(">o< nhs服务启动成功！温馨提示：代码千万行，注释第一行，命名不规范，同事泪两行 >o<");
        Environment env = application.getEnvironment();
        // 是否启用https
        boolean httpsFlag = Boolean.parseBoolean(env.getProperty("server.ssl.enabled"));
        log.info("Swagger文档: \t\t{}://{}:{}/swagger-ui.html",httpsFlag?"https":"http", InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
    }

}
