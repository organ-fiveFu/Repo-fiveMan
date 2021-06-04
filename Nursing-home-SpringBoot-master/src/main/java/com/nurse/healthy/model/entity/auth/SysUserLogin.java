package com.nurse.healthy.model.entity.auth;

import lombok.Data;

@Data
public class SysUserLogin {
    /**
     * 登录工号
     */
    private String employeeCode;

    /**
     * 登录密码
     */
    private String password;
}
