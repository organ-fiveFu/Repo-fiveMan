package com.vblessings.nhs.service.Impl;

import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.mapper.BusSatisfactionMeasurementMapper;
import com.vblessings.nhs.model.entity.business.BusSatisfactionMeasurement;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusSatisfactionMeasurementService;
import com.vblessings.nhs.util.OperateUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Service
public class BusSatisfactionMeasurementServiceImpl implements BusSatisfactionMeasurementService {
    @Resource
    private BusSatisfactionMeasurementMapper busSatisfactionMeasurementMapper;

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Override
    public void add(BusSatisfactionMeasurement busSatisfactionMeasurement, UserInfoToken userInfo) {
            Long id = snowflakeComponent.getInstance().nextId();
            OperateUtil.onSaveNew(busSatisfactionMeasurement,userInfo,id);
            busSatisfactionMeasurementMapper.insert(busSatisfactionMeasurement);
    }

    @Override
    public BusSatisfactionMeasurement selectMeasurement(String phone) {
        Example example = new Example(BusSatisfactionMeasurement.class);
        example.createCriteria().andEqualTo("phone",phone);
        BusSatisfactionMeasurement b = busSatisfactionMeasurementMapper.selectOneByExample(example);
        return b;
    }
}
