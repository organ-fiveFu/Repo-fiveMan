package com.nurse.healthy.service.Impl;

import com.github.pagehelper.util.StringUtil;
import com.nurse.healthy.component.SnowflakeComponent;
import com.nurse.healthy.exception.MyException;
import com.nurse.healthy.mapper.CareMapper;
import com.nurse.healthy.model.entity.sys.SysCarerInfo;
import com.nurse.healthy.model.entity.sys.SysDictType;
import com.nurse.healthy.result.UserInfoToken;
import com.nurse.healthy.service.CareService;
import com.nurse.healthy.util.OperateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service

public class CareServiceImpl implements CareService {
    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private CareMapper careMapper;
    @Override
    public void add(SysCarerInfo sysCarerInfo, UserInfoToken userInfo) {
        Long id = snowflakeComponent.getInstance().nextId();
        //验证code是否重复
        List<String> codes = careMapper.selectAllCode();
        if(codes!=null && !codes.isEmpty()){
            if(codes.contains(sysCarerInfo.getCareCode())){
                throw new MyException("code已重复,不可增加此护工");
            }
        }
        OperateUtil.onSaveNew(sysCarerInfo,userInfo,id);
        careMapper.insert(sysCarerInfo);

    }

    @Override
    @Transactional
    public void update(SysCarerInfo sysCarerInfo, UserInfoToken userInfo) {
        //验证code是否重复
      /*  List<String> codes = careMapper.selectAllCode();
        if(codes!=null && !codes.isEmpty()){
            if(codes.contains(sysCarerInfo.getCareCode())){
                throw new MyException("code已重复,不可使用相同code");
            }
        }*/
        sysCarerInfo.setUpdateTime(new Date());
        sysCarerInfo.setUpdaterId(userInfo.getUserId());
        careMapper.updateByPrimaryKeySelective(sysCarerInfo);

    }

    @Override
    public List<SysCarerInfo> select(String careCode) {
        Example example = new Example(SysCarerInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel",0);
        if(StringUtil.isNotEmpty(careCode)){
            criteria.andEqualTo("careCode",careCode);
        }
        List<SysCarerInfo> sysCarerInfoList = careMapper.selectByExample(example);
        return sysCarerInfoList;
    }

    @Override
    @Transactional
    public void del(List<String> ids) {
        careMapper.del(ids);
    }
}
