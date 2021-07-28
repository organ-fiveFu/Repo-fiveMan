package com.vblessings.nhs;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;


@EnableSwagger2
@ComponentScan({"com.vblessings.nhs.*"})
@MapperScan({"com.vblessings.nhs.mapper"})
@SpringBootApplication
public class BsHealthyApplication {

    public static void main(String[] args) {
        SpringApplication.run(BsHealthyApplication.class, args);
    }

}
