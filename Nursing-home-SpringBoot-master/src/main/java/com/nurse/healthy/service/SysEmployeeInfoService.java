package com.nurse.healthy.service;

import com.nurse.healthy.model.entity.sys.SysEmployeeInfo;

public interface SysEmployeeInfoService{
    /**
     * 根据员工账号查询员工信息
     * @param employeeCode
     * @return
     */
    SysEmployeeInfo selectOneInfo(String employeeCode);
}
