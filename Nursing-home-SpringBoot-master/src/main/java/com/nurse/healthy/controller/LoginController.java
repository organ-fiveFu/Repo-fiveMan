package com.nurse.healthy.controller;


import com.nurse.healthy.model.entity.Archive;
import com.nurse.healthy.model.po.RegisterVO;
import com.nurse.healthy.service.LoginService;
import com.nurse.healthy.vo.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 验证
 */
@RestController
@RequestMapping("/validation")
@Api(tags = "登录api")
@Slf4j
public class LoginController {

    @Resource
    private LoginService loginService;

    @ApiOperation("注册")
    @PostMapping("/register")
    public ResultBody register(@RequestBody RegisterVO registerVO){
        return ResultBody.newSuccessInstance(loginService.register(registerVO));
    }




    @ApiOperation("登录")
    @PostMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountNumber", value = "账号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
    })
    public ResultBody login(String accountNumber,String password ) {
        return ResultBody.newSuccessInstance(loginService.login(accountNumber,password));
    }

}
