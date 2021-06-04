package com.nurse.healthy.service.Impl;

import com.nurse.healthy.mapper.SysEmployeeInfoMapper;
import com.nurse.healthy.model.entity.sys.SysEmployeeInfo;
import com.nurse.healthy.model.entity.sys.SysLogin;
import com.nurse.healthy.service.SysEmployeeInfoService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Service
public class SysEmployeeInfoServiceImpl implements SysEmployeeInfoService {

    @Resource
    private SysEmployeeInfoMapper sysEmployeeInfoMapper;
    @Override
    public SysEmployeeInfo selectOneInfo(String employeeCode) {
        Example example = new Example(SysEmployeeInfo.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("employee_code",employeeCode);
        SysEmployeeInfo sysEmployeeInfo = sysEmployeeInfoMapper.selectOneByExample(example);
        return sysEmployeeInfo;
    }
}
