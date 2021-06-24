package com.nurse.healthy.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.nurse.healthy.component.SnowflakeComponent;
import com.nurse.healthy.mapper.*;
import com.nurse.healthy.model.entity.business.*;
import com.nurse.healthy.model.po.ExamArchivePO;
import com.nurse.healthy.result.UserInfoToken;
import com.nurse.healthy.service.BusExamArchiveService;
import com.nurse.healthy.util.OperateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class BusExamArchiveServiceImpl implements BusExamArchiveService {

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private BusExamArchiveMapper busExamArchiveMapper;

    @Resource
    private BusExamEntArchiveMapper busExamEntArchiveMapper;

    @Resource
    private BusExamEyesArchiveMapper busExamEyesArchiveMapper;

    @Resource
    private BusExamInternalArchiveMapper busExamInternalArchiveMapper;

    @Resource
    private BusExamOtherArchiveMapper busExamOtherArchiveMapper;

    @Resource
    private BusExamSurgicalArchiveMapper busExamSurgicalArchiveMapper;


    /**
     * 新增体检档案
     * @param examArchivePO
     * @param userInfo
     */
    @Override
    @Transactional
    public void save(ExamArchivePO examArchivePO, UserInfoToken userInfo) {
        //主表相关数据
        BusExamArchive busExamArchive = new BusExamArchive();
        //从表相关数据
        BusExamEntArchive busExamEntArchive = new BusExamEntArchive();
        BusExamEyesArchive busExamEyesArchive = new BusExamEyesArchive();
        BusExamInternalArchive busExamInternalArchive = new BusExamInternalArchive();
        BusExamOtherArchive busExamOtherArchive = new BusExamOtherArchive();
        BusExamSurgicalArchive busExamSurgicalArchive = new BusExamSurgicalArchive();


        BeanUtil.copyProperties(examArchivePO.getBusExamArchive(),busExamArchive);
        BeanUtil.copyProperties(examArchivePO.getBusExamEntArchive(),busExamEntArchive);
        BeanUtil.copyProperties(examArchivePO.getBusExamEyesArchive(),busExamEyesArchive);
        BeanUtil.copyProperties(examArchivePO.getBusExamInternalArchive(),busExamInternalArchive);
        BeanUtil.copyProperties(examArchivePO.getBusExamOtherArchive(),busExamOtherArchive);
        BeanUtil.copyProperties(examArchivePO.getBusExamSurgicalArchive(),busExamSurgicalArchive);


        //主表id
        Long id = snowflakeComponent.getInstance().nextId();
        //新增体检登记主表数据
        OperateUtil.onSaveNew(busExamArchive,userInfo,id);
        busExamArchiveMapper.insert(busExamArchive);

        //新增从表数据
        Long entId = snowflakeComponent.getInstance().nextId();
        busExamEntArchive.setExamId(id);
        busExamEntArchive.setId(entId);
        busExamEntArchiveMapper.insert(busExamEntArchive);

        Long eyeId = snowflakeComponent.getInstance().nextId();
        busExamEyesArchive.setExamId(id);
        busExamEyesArchive.setId(eyeId);
        busExamEyesArchiveMapper.insert(busExamEyesArchive);

        Long internalId = snowflakeComponent.getInstance().nextId();
        busExamInternalArchive.setExamId(id);
        busExamInternalArchive.setId(internalId);
        busExamInternalArchiveMapper.insert(busExamInternalArchive);

        Long surgicalId = snowflakeComponent.getInstance().nextId();
        busExamSurgicalArchive.setExamId(id);
        busExamSurgicalArchive.setId(surgicalId);
        busExamSurgicalArchiveMapper.insert(busExamSurgicalArchive);

        Long otherId = snowflakeComponent.getInstance().nextId();
        busExamOtherArchive.setExamId(id);
        busExamOtherArchive.setId(otherId);
        busExamOtherArchiveMapper.insert(busExamOtherArchive);

    }

    /**
     * 住院号查询
     * @param businessNo
     * @return
     */
    @Override
    public List<ExamArchivePO> select(String businessNo) {
        Example example = new Example(BusExamArchive.class);
        example.createCriteria().andEqualTo("businessNo",businessNo);
        //todo
        return null;
    }
}
