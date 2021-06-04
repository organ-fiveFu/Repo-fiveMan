package com.nurse.healthy.controller;


import com.nurse.healthy.annoation.IgnoreUserToken;
import com.nurse.healthy.model.entity.auth.SysUserLogin;
import com.nurse.healthy.service.LoginService;
import com.nurse.healthy.vo.ResultBody;
import io.swagger.annotations.Api;
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



    @ApiOperation("登录")
    @PostMapping("/login")
    @IgnoreUserToken
    public ResultBody login(@RequestBody SysUserLogin sysUserLogin) {
        return ResultBody.newSuccessInstance(loginService.login(sysUserLogin));
    }

}
