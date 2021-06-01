package com.nurse.healthy;

import com.nurse.healthy.component.SnowflakeComponent;
import com.nurse.healthy.model.po.RegisterVO;
import com.nurse.healthy.service.LoginService;
import com.nurse.healthy.util.AESUtill;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BsHealthyApplication.class})
class BsHealthyApplicationTests {

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private LoginService loginService;

    @Test
    public void play(){
        System.out.println(snowflakeComponent.getInstance().nextId());
    }

    @Test
    public void paly1(){
        System.out.println(AESUtill.encryptData(AESUtill.encryptData("qianxiangxiang")));
        System.out.println(AESUtill.deciTwo("Iq4jyR+440Blhh9ahOwuNjHiJgzgez/xel3LYQhQhr8="));
    }

    @Test
    public void play2(){
        RegisterVO registerVO = RegisterVO.builder().accountNumber("zz").password("222").build();
        loginService.register(registerVO);

    }
}
