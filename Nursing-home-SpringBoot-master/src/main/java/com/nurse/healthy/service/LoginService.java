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
     * @param employeeCode
     * @return
     */
    Boolean updatePassword(String password,String employeeCode);

    /**
     * 重置工号密码
     * @param employeeCode
     */
    void resetPassword(String employeeCode);

    /**
     * 增加账号和密码
     */
    void addEmployee(String employeeCode);
}
