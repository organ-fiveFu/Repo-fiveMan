package com.vblessings.nhs.service;

import com.vblessings.nhs.model.entity.sys.SysEmployeeInfo;
import com.vblessings.nhs.model.po.QueryEmployeePO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.result.UserInfoToken;

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
    void updatePassword(SysEmployeeInfo sysEmployeeInfo);


    /**
     * 根据主键查询
     *
     */
    SysEmployeeInfo selectById(Long id);

    /**
     * 新增员工信息
     * @param sysEmployeeInfo
     * @param userInfo
     */
    void add(SysEmployeeInfo sysEmployeeInfo, UserInfoToken userInfo);


    /**
     * 更新员工信息
     * @param sysEmployeeInfo
     * @param userInfo
     */
    void update(SysEmployeeInfo sysEmployeeInfo, UserInfoToken userInfo);

    /**
     * 查询员工信息
     * @param queryEmployeePO
     * @return
     */
    PageVO<SysEmployeeInfo> select(QueryEmployeePO queryEmployeePO);

    void del(String ids);

    /**
     * 重置密码
     * @return
     */
    void resetPassword(String employeeCode);
}
