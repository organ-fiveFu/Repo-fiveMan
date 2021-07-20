package com.nurse.healthy.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nurse.healthy.component.SnowflakeComponent;
import com.nurse.healthy.mapper.BasePatientInfoMapper;
import com.nurse.healthy.model.entity.base.BasePatientInfo;
import com.nurse.healthy.model.po.QueryBasePatientPO;
import com.nurse.healthy.result.UserInfoToken;
import com.nurse.healthy.service.BasePatientInfoService;
import com.nurse.healthy.util.OperateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class BasePatientInfoServiceImpl implements BasePatientInfoService {

    @Resource
    private BasePatientInfoMapper basePatientInfoMapper;

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Override
    public PageInfo<BasePatientInfo> selectPage(QueryBasePatientPO queryBasePatientPO) {
        PageHelper.startPage(queryBasePatientPO.getPageNum(), queryBasePatientPO.getPageSize());
        Example example = new Example(BasePatientInfo.class);
        Example.Criteria C = example.createCriteria();
        C.andEqualTo("isDel",0);
        if(Strings.isNotBlank(queryBasePatientPO.getName())){
            C.andLike("name","%"+queryBasePatientPO.getName()+"%");
        }
        if(queryBasePatientPO.getArchiveId()!=null){
            C.andEqualTo("id",queryBasePatientPO.getArchiveId());
        }
        List<BasePatientInfo> basePatientInfoList = basePatientInfoMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(basePatientInfoList);
        return pageInfo;
    }

    /**
     * 新增档案
     * @param basePatientInfo
     * @param userInfo
     */
    @Override
    @Transactional
    public void add(BasePatientInfo basePatientInfo, UserInfoToken userInfo) {
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(basePatientInfo,userInfo,id);
        basePatientInfoMapper.insert(basePatientInfo);
    }

    /**
     * 根据主键更新档案
     * @param basePatientInfo
     * @param userInfo
     */
    @Override
    @Transactional
    public void update(BasePatientInfo basePatientInfo, UserInfoToken userInfo) {
        Date date = new Date();
        basePatientInfo.setUpdaterId(userInfo.getUserId());
        basePatientInfo.setUpdateTime(date);
        basePatientInfoMapper.updateByPrimaryKeySelective(basePatientInfo);
    }

    /**
     * 删除档案
     * @param id
     * @param userInfo
     */
    @Override
    @Transactional
    public void del(Long id, UserInfoToken userInfo) {
        BasePatientInfo basePatientInfo = new BasePatientInfo();
        basePatientInfo.setId(id);
        basePatientInfo.setIsDel(1);
        basePatientInfoMapper.updateByPrimaryKeySelective(basePatientInfo);
    }
}
