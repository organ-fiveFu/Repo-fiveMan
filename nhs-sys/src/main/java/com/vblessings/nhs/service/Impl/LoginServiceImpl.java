package com.vblessings.nhs.service.Impl;

import com.vblessings.nhs.component.RedisCache;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.exception.MyException;
import com.vblessings.nhs.exception.ResponseEnum;
import com.vblessings.nhs.jwt.JWTInfo;
import com.vblessings.nhs.jwt.jwtHelper;
import com.vblessings.nhs.mapper.LoginMapper;
import com.vblessings.nhs.mapper.SysEmployeeInfoMapper;
import com.vblessings.nhs.model.entity.sys.SysEmployeeInfo;
import com.vblessings.nhs.model.entity.sys.SysLogin;
import com.vblessings.nhs.model.entity.auth.SysUserLogin;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.LoginService;
import com.vblessings.nhs.service.SysEmployeeInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

    //加密密钥
    @Value("${jwt.rsa-secret}")
    private String priKeyPath;

    //过期时间
    @Value("${jwt.expire}")
    private int expire;

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private LoginMapper loginMapper;

    @Resource
    private SysEmployeeInfoService sysEmployeeInfoService;

    @Resource
    private RedisCache redisCache;

    @Resource
    private SysEmployeeInfoMapper sysEmployeeInfoMapper;


    @Override
    public UserInfoToken login(SysUserLogin sysUserLogin) {

        Example example = new Example(SysLogin.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("employeeCode",sysUserLogin.getEmployeeCode());
        c.andEqualTo("isDel",0);
        SysLogin sysLogin = loginMapper.selectOneByExample(example);


        if(sysLogin==null){

            throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该账号不存在");
        }

        if(!sysUserLogin.getPassword().equals(sysLogin.getPassword())){
            throw ResponseEnum.ACCOUNT_PWD_ERROR.newException("账号或密码错误.");
        }

        if(sysLogin!=null && sysLogin.getUseFlag()==0){
            throw ResponseEnum.ACCOUNT_IS_DEL.newException("账号已停用.");

        }

        //查询使用该账号员工的信息
        //当前用户
        SysEmployeeInfo sysEmployeeInfo = sysEmployeeInfoService.selectOneInfo(sysUserLogin.getEmployeeCode());

        UserInfoToken userInfoToken = new UserInfoToken();


        String token;
        try {
         token = "Bearer "+ jwtHelper.createToken(new JWTInfo(sysEmployeeInfo.getEmployeeCode(), sysEmployeeInfo.getId() , sysEmployeeInfo.getName()));
            userInfoToken.setToken(token);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new MyException("token生成失败！");
        }

        userInfoToken.setUserId(sysEmployeeInfo.getId());
        userInfoToken.setName(sysEmployeeInfo.getName());
        userInfoToken.setEmployeeCode(sysEmployeeInfo.getEmployeeCode());
        //保存用户信息
        redisCache.setCacheObject(token, userInfoToken);
        redisCache.expire(token, expire);


        return userInfoToken;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updatePassword(String password,String employeeCode) {
        Example example1 = new Example(SysEmployeeInfo.class);
        Example.Criteria c1 = example1.createCriteria();
        c1.andEqualTo("employeeCode",employeeCode);
        SysEmployeeInfo sysEmployeeInfo = sysEmployeeInfoMapper.selectOneByExample(example1);
        sysEmployeeInfo.setPassword(password);
        sysEmployeeInfoService.updatePassword(sysEmployeeInfo);

        Example example = new Example(SysLogin.class);
        Example.Criteria c = example.createCriteria();

        SysLogin sysLogin = SysLogin.builder().employeeCode(employeeCode).password(password).build();
        c.andEqualTo("employeeCode",employeeCode);
        loginMapper.updateByExampleSelective(sysLogin,example);
        return true;
    }

    @Override
    public void resetPassword(String employeeCode) {
        loginMapper.resetPassword(employeeCode);
    }

    @Override
    public void addEmployee(String employeeCode) {
      Long id = snowflakeComponent.getInstance().nextId();
        SysLogin sysLogin = new SysLogin();
        sysLogin.setId(id);
        sysLogin.setEmployeeCode(employeeCode);
        sysLogin.setPassword("000000");
        sysLogin.setIsDel(0);
        sysLogin.setUseFlag(1);
        loginMapper.insert(sysLogin);
    }

}
