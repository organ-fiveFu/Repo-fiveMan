package com.nurse.healthy.service;

import com.nurse.healthy.model.entity.sys.SysEmployeeInfo;

public interface SysEmployeeInfoService{
    /**
     * 根据员工账号查询员工信息
     * @param employeeCode
     * @return
     */
    SysEmployeeInfo selectOneInfo(String employeeCode);


    /**
     * 更新密码
     * @param sysEmployeeInfo
     * @return
     */
    Boolean updatePassword(SysEmployeeInfo sysEmployeeInfo);


    /**
     * 根据主键查询
     *
     */
    SysEmployeeInfo selectById(Long id);
}
