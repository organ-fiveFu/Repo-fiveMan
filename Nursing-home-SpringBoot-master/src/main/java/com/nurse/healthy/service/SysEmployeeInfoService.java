package com.nurse.healthy.service;

import com.nurse.healthy.model.entity.sys.SysDictData;
import com.nurse.healthy.model.entity.sys.SysEmployeeInfo;
import com.nurse.healthy.model.po.QueryEmployeePO;
import com.nurse.healthy.model.vo.PageVO;
import com.nurse.healthy.result.UserInfoToken;

import java.util.List;

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
