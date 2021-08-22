package com.vblessings.nhs.controller;


import cn.hutool.core.util.StrUtil;
import com.vblessings.nhs.annoation.CurrentUser;
import com.vblessings.nhs.annoation.IgnoreUserToken;
import com.vblessings.nhs.component.RedisCache;
import com.vblessings.nhs.exception.MyException;
import com.vblessings.nhs.model.entity.auth.SysUserLogin;
import com.vblessings.nhs.model.po.UpdatePasswordPO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.LoginService;
import com.vblessings.nhs.model.result.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

    @Resource
    private RedisCache redisCache;



    @ApiOperation("登录")
    @PostMapping("/login")
    @IgnoreUserToken
    public ResultBody login(@RequestBody SysUserLogin sysUserLogin) {
        return ResultBody.newSuccessInstance(loginService.login(sysUserLogin));
    }

    /**
     * 退出登录
     * @param userInfo
     * @return
     */
    @ApiOperation("退出登录")
    @PostMapping("/loginOut")
    public ResultBody loginOut(@ApiIgnore @CurrentUser UserInfoToken userInfo){
        if (StrUtil.isAllBlank(userInfo.getToken())) {
            throw new MyException("未获取到token");
        }
        UserInfoToken userInfoToken = redisCache.getCacheObject(userInfo.getToken());
            if (userInfoToken!=null){
                log.info("登出用户:" + userInfoToken.toString());
                redisCache.deleteObject(userInfo.getToken());
        }
         return ResultBody.newSuccessInstance();
    }

    /**
     * 修改密码
     * @param
     * @return
     */
    @ApiOperation("修改密码")
    @PostMapping("/updatePassword")
    public ResultBody updatePassword(@RequestBody UpdatePasswordPO updatePasswordPO) {
        return ResultBody.newSuccessInstance(loginService.updatePassword(updatePasswordPO.getPassword(),updatePasswordPO.getEmployeeCode()));
    }

    @ApiOperation("获取ip")
    @PostMapping("/getIp")
    public ResultBody getIp(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String address = request.getRemoteAddr();
        return ResultBody.newSuccessInstance(address);
    }


}
