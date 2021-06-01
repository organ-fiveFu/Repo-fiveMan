package com.nurse.healthy.service.Impl;

import com.nurse.healthy.component.SnowflakeComponent;
import com.nurse.healthy.exception.MyException;
import com.nurse.healthy.mapper.LoginMapper;
import com.nurse.healthy.model.entity.Archive;
import com.nurse.healthy.model.entity.SysLogin;
import com.nurse.healthy.model.po.RegisterVO;
import com.nurse.healthy.service.LoginService;
import com.nurse.healthy.util.AESUtill;
import com.nurse.healthy.vo.ResultBody;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private LoginMapper loginMapper;
    @Override
    public Boolean register(RegisterVO registerVO) {
        //密码加密
        String password = AESUtill.encryptData(registerVO.getPassword());

        SysLogin sysLogin = SysLogin.builder().accountNumber(registerVO.getAccountNumber()).password(password).id(snowflakeComponent.getInstance().nextId()).build();

        loginMapper.insert(sysLogin);
        return true;
    }

    @Override
    public Boolean login(String accountNumber, String password) {
        Example example = new Example(SysLogin.class);
        example.selectProperties("password");
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("account_number", accountNumber);
        SysLogin sysLogin = loginMapper.selectOneByExample(c);
        if(!password.equals(AESUtill.deci(sysLogin.getPassword()))){
            throw new MyException("密码错误");
        }
        return true;
    }
}
