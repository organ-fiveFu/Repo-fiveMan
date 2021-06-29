package com.nurse.healthy.service.Impl;

import com.github.pagehelper.util.StringUtil;
import com.nurse.healthy.component.SnowflakeComponent;
import com.nurse.healthy.exception.MyException;
import com.nurse.healthy.mapper.SysEmployeeInfoMapper;
import com.nurse.healthy.model.entity.sys.SysEmployeeInfo;
import com.nurse.healthy.result.UserInfoToken;
import com.nurse.healthy.service.LoginService;
import com.nurse.healthy.service.SysEmployeeInfoService;
import com.nurse.healthy.util.OperateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SysEmployeeInfoServiceImpl implements SysEmployeeInfoService {

    @Resource
    private SysEmployeeInfoMapper sysEmployeeInfoMapper;

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private LoginService loginService;
    @Override
    public SysEmployeeInfo selectOneInfo(String employeeCode) {
        Example example = new Example(SysEmployeeInfo.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("employeeCode",employeeCode);
        SysEmployeeInfo sysEmployeeInfo = sysEmployeeInfoMapper.selectOneByExample(example);
        return sysEmployeeInfo;
    }

    @Override
    public void updatePassword(SysEmployeeInfo sysEmployeeInfo) {
        sysEmployeeInfoMapper.updateByPrimaryKey(sysEmployeeInfo);
    }

    @Override
    public SysEmployeeInfo selectById(Long id) {
        return sysEmployeeInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void add(SysEmployeeInfo sysEmployeeInfo, UserInfoToken userInfo) {
        Long id = snowflakeComponent.getInstance().nextId();
        //验证code是否重复
        List<String> codes = sysEmployeeInfoMapper.selectAllCode();
        if(codes!=null && !codes.isEmpty()){
            if(codes.contains(sysEmployeeInfo.getEmployeeCode())){
                throw new MyException("code已重复,不可增加此员工");
            }
        }
        OperateUtil.onSaveNew(sysEmployeeInfo,userInfo,id);
        sysEmployeeInfoMapper.insert(sysEmployeeInfo);
        loginService.addEmployee(sysEmployeeInfo.getEmployeeCode());
    }

    @Override
    @Transactional
    public void update(SysEmployeeInfo sysEmployeeInfo, UserInfoToken userInfo) {
        //验证code是否重复
        /*List<String> codes = sysEmployeeInfoMapper.selectAllCode();
        if(codes!=null && !codes.isEmpty()){
            if(codes.contains(sysEmployeeInfo.getEmployeeCode())){
                throw new MyException("code已使用");
            }
        }*/
        sysEmployeeInfo.setUpdateTime(new Date());
        sysEmployeeInfo.setUpdaterId(userInfo.getUserId());
        sysEmployeeInfoMapper.updateByPrimaryKeySelective(sysEmployeeInfo);
    }

    @Override
    public List<SysEmployeeInfo> select(String employeeCode) {
        Example example = new Example(SysEmployeeInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel",0);
        if(StringUtil.isNotEmpty(employeeCode)){
            criteria.andEqualTo("employeeCode",employeeCode);
        }
        List<SysEmployeeInfo> sysEmployeeInfoList = sysEmployeeInfoMapper.selectByExample(example);
        return sysEmployeeInfoList;
    }

    @Override
    public void del(List<String> ids) {
        sysEmployeeInfoMapper.del(ids);
    }

    /**
     * 重置密码
     */
    @Override
    public void resetPassword(String employeeCode) {
        loginService.resetPassword(employeeCode);
        sysEmployeeInfoMapper.resetPassword(employeeCode);
    }


}
