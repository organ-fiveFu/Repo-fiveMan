package com.nurse.healthy;

import com.nurse.healthy.component.RedisCache;
import com.nurse.healthy.component.SnowflakeComponent;
import com.nurse.healthy.jwt.JWTInfo;
import com.nurse.healthy.jwt.jwtHelper;
import com.nurse.healthy.model.entity.sys.SysEmployeeInfo;
import com.nurse.healthy.model.po.RegisterVO;
import com.nurse.healthy.service.LoginService;
import com.nurse.healthy.util.AESUtill;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BsHealthyApplication.class})
class BsHealthyApplicationTests {

    //加密密钥

    private String priKeyPath="adewdaw";

    //过期时间

    private int expire=86400;

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private LoginService loginService;

    @Resource
    RedisCache redisCache;

    @Test
    public void play(){
        System.out.println(snowflakeComponent.getInstance().nextId());
    }

    @Test
    public void paly1(){
        System.out.println(AESUtill.encryptData("123esfre34efresw"));
        System.out.println(AESUtill.deciTwo("Iq4jyR+440Blhh9ahOwuNjHiJgzgez/xel3LYQhQhr8="));
    }

    @Test
    public void paly2() throws Exception {
        SysEmployeeInfo sysEmployeeInfo = new SysEmployeeInfo();
        sysEmployeeInfo.setEmployeeCode("123456");
        sysEmployeeInfo.setPassword("123456");
        sysEmployeeInfo.setName("张三");
        sysEmployeeInfo.setId(snowflakeComponent.getInstance().nextId());
        String  token = jwtHelper.createToken(new JWTInfo(sysEmployeeInfo.getEmployeeCode(), sysEmployeeInfo.getId() , sysEmployeeInfo.getName()));
        System.out.println(token);
    }
    @Test
    public void paly3(){
        System.out.println(redisCache.getCacheObject("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiY2MiLCJleHAiOjE2MjM3NDA3MjYsInVzZXJJZCI6MTMxLCJlbXBsb3llZV9jb2RlIjoiMjAyMSJ9.6PYOol3owH6Aq93SBJHU9d5WxQ0pIG1ywHxYLHgdQY8").toString());
    }

}
