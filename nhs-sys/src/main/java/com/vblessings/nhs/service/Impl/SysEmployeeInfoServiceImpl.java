package com.vblessings.nhs.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.exception.MyException;
import com.vblessings.nhs.exception.ResponseEnum;
import com.vblessings.nhs.mapper.LoginMapper;
import com.vblessings.nhs.mapper.SysEmployeeInfoMapper;
import com.vblessings.nhs.model.entity.sys.SysEmployeeInfo;
import com.vblessings.nhs.model.entity.sys.SysLogin;
import com.vblessings.nhs.model.po.QueryEmployeePO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.LoginService;
import com.vblessings.nhs.service.SysEmployeeInfoService;
import com.vblessings.nhs.util.OperateUtil;
import com.vblessings.nhs.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysEmployeeInfoServiceImpl implements SysEmployeeInfoService {

    @Resource
    private SysEmployeeInfoMapper sysEmployeeInfoMapper;

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private LoginService loginService;

    @Resource
    private LoginMapper loginMapper;
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
                throw ResponseEnum.CODE_ALREADY_EXISTS.newException("code已重复,不可增加此员工");
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
        if(sysEmployeeInfo.getUseFlag()!=null){
        Example example = new Example(SysLogin.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("employeeCode",sysEmployeeInfo.getEmployeeCode());
        SysLogin sysLogin = new SysLogin();
        sysLogin.setUseFlag(sysEmployeeInfo.getUseFlag());
        loginMapper.updateByExampleSelective(sysLogin,example);}
    }

    @Override
    public PageVO<SysEmployeeInfo> select(QueryEmployeePO queryEmployeePO) {
        Page<SysEmployeeInfo> result = PageHelper.startPage(queryEmployeePO.getPageNum(), queryEmployeePO.getPageSize());
        Example example = new Example(SysEmployeeInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel",0);
        if(queryEmployeePO.getKeyWord()!=null){
            if(StringUtil.isChinese(queryEmployeePO.getKeyWord())){
                criteria.andLike("name","%"+queryEmployeePO.getKeyWord()+"%");
            }
            //如果是数字说明查code
            if(StringUtil.isNumeric(queryEmployeePO.getKeyWord())){
                criteria.andEqualTo("employeeCode",queryEmployeePO.getKeyWord());
            }
        }
        List<SysEmployeeInfo> sysEmployeeInfoList = sysEmployeeInfoMapper.selectByExample(example);
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), sysEmployeeInfoList);

    }

    @Override
    public void del(String ids) {

        List<String> idList = Arrays.asList(ids.split(","));
        Example example = new Example(SysEmployeeInfo.class);
        example.selectProperties("employeeCode");
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("isDel",0).andIn("id",idList);
        List<SysEmployeeInfo> sysEmployeeInfos = sysEmployeeInfoMapper.selectByExample(example);

        List<String> employeeList = sysEmployeeInfos.stream().map(e->e.getEmployeeCode()).collect(Collectors.toList());
        //将账号表状态置为删除状态
        for (String s:
        employeeList) {
            Example example1 = new Example(SysLogin.class);
            Example.Criteria C = example1.createCriteria();
            C.andEqualTo("employeeCode",s);
            SysLogin sysLogin = new SysLogin();
            sysLogin.setEmployeeCode(s);
            sysLogin.setIsDel(1);
            loginMapper.updateByExampleSelective(sysLogin,example1);
        }

        String[] id = ids.split(",");
        sysEmployeeInfoMapper.del(id);
    }

    /**
     * 重置密码
     */
    @Override
    public void resetPassword(String employeeCode) {
        loginService.resetPassword(employeeCode);
        sysEmployeeInfoMapper.resetPassword(employeeCode);
    }

    /**
     * 停用账号
     * @param employeeCode
     */
    @Override
    public void stop(String employeeCode) {
        Example example = new Example(SysLogin.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("employeeCode",employeeCode);
        SysLogin sysLogin = new SysLogin();
        sysLogin.setUseFlag(0);
        loginMapper.updateByExampleSelective(sysLogin,example);
    }

    /**
     * 启用账号
     * @param employeeCode
     */
    @Override
    public void start(String employeeCode) {
        Example example = new Example(SysLogin.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("employeeCode",employeeCode);
        SysLogin sysLogin = new SysLogin();
        sysLogin.setUseFlag(1);
        loginMapper.updateByExampleSelective(sysLogin,example);
    }


}
