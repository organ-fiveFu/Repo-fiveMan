package com.vblessings.nhs.controller;

import com.vblessings.nhs.annoation.IgnoreUserToken;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

@RestController
@RequestMapping("/test")
@Api(tags = "测试")
@Slf4j
public class TestController {
    @PostMapping("/a")
    @IgnoreUserToken
    public void doPost(HttpServletRequest request,
                          HttpServletResponse response, BufferedReader br)
            throws ServletException, IOException {
//Header部分
        System.out.print(request.getHeaderNames());
        Enumeration<?> enum1 = request.getHeaderNames();
        while (enum1.hasMoreElements()) {
            String key = (String) enum1.nextElement();
            String value = request.getHeader(key);
            System.out.println(key + "\t" + value);
            log.info(key + "\t" + value);
        }
//body部分
        String inputLine;
        String str = "";
        try {
            while ((inputLine = br.readLine()) != null) {
                str += inputLine;
            }
            br.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        System.out.println("str:" + str);
        log.info("str:" + str);
    }
}
