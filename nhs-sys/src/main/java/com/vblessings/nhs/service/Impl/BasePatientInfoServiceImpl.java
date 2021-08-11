package com.vblessings.nhs.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.mapper.BasePatientInfoMapper;
import com.vblessings.nhs.model.entity.base.BasePatientInfo;
import com.vblessings.nhs.model.po.QueryBasePatientPO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BasePatientInfoService;
import com.vblessings.nhs.util.OperateUtil;
import com.vblessings.nhs.util.StringUtil;
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
     */
    @Override
    @Transactional
    public void del(String ids) {
      String[] id = ids.split(",");
        basePatientInfoMapper.del(id);
    }

    @Override
    public List<BasePatientInfo> baseArchiveList(String name) {
        if(name !=null && StringUtil.isChinese(name)){
       return basePatientInfoMapper.baseArchiveList(name);}
        if(name !=null &&StringUtil.isEnglish(name)){
            return basePatientInfoMapper.baseArchiveListCopy(name);
        }
        return null;
    }
}
