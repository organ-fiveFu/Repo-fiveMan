package com.nurse.healthy.service;

import com.nurse.healthy.model.entity.Archive;
import com.nurse.healthy.model.po.RegisterVO;
import com.nurse.healthy.vo.ResultBody;

public interface LoginService {
    /**
     * 用户注册
     * @param registerVO
     * @return
     */
    Boolean register(RegisterVO registerVO);

    /**
     * 用户登录
     * @param accountNumber
     * @param password
     * @return
     */
    Boolean login(String accountNumber, String password);
}
