package com.nurse.healthy.service;

import com.nurse.healthy.model.entity.auth.SysUserLogin;
import com.nurse.healthy.result.UserInfoToken;

public interface LoginService {


    /**
     * 用户登录
     * @param sysUserLogin
     * @return
     */
    UserInfoToken login(SysUserLogin sysUserLogin);


    /**
     * 更新密码
     * @param userInfo
     * @return
     */
    Boolean updatePassword(String password,UserInfoToken userInfo);


}
