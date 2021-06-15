package com.nurse.healthy.service.Impl;

import com.nurse.healthy.component.RedisCache;
import com.nurse.healthy.component.SnowflakeComponent;
import com.nurse.healthy.exception.MyException;
import com.nurse.healthy.jwt.JWTInfo;
import com.nurse.healthy.jwt.jwtHelper;
import com.nurse.healthy.mapper.LoginMapper;
import com.nurse.healthy.model.entity.sys.SysEmployeeInfo;
import com.nurse.healthy.model.entity.sys.SysLogin;
import com.nurse.healthy.model.entity.auth.SysUserLogin;
import com.nurse.healthy.result.UserInfoToken;
import com.nurse.healthy.service.LoginService;
import com.nurse.healthy.service.SysEmployeeInfoService;
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


    @Override
    public UserInfoToken login(SysUserLogin sysUserLogin) {

        Example example = new Example(SysLogin.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("employeeCode",sysUserLogin.getEmployeeCode());
        SysLogin sysLogin = loginMapper.selectOneByExample(example);

        if(sysLogin==null){
            throw new MyException("该账号不存在");
        }

        if(!sysUserLogin.getPassword().equals(sysLogin.getPassword())){
            throw new MyException("密码错误");
        }

        //查询使用该账号员工的信息
        //当前用户
        SysEmployeeInfo sysEmployeeInfo = sysEmployeeInfoService.selectOneInfo(sysUserLogin.getEmployeeCode());

        UserInfoToken userInfoToken = new UserInfoToken();


        String token;
        try {
         token = "Bearer "+jwtHelper.createToken(new JWTInfo(sysEmployeeInfo.getEmployeeCode(), sysEmployeeInfo.getId() , sysEmployeeInfo.getName()));
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
    public Boolean updatePassword(String password,UserInfoToken userInfo) {
      SysEmployeeInfo sysEmployeeInfo =  sysEmployeeInfoService.selectById(userInfo.getUserId());
      sysEmployeeInfo.setPassword(password);
      sysEmployeeInfoService.updatePassword(sysEmployeeInfo);

      Example example = new Example(SysLogin.class);
      Example.Criteria c = example.createCriteria();

        SysLogin sysLogin = SysLogin.builder().employeeCode(userInfo.getEmployeeCode()).password(password).build();
        c.andEqualTo("employeeCode",userInfo.getEmployeeCode());
        loginMapper.updateByExampleSelective(sysLogin,example);
        return true;
    }

}
