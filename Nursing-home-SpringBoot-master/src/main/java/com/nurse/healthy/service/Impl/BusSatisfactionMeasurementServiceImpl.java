package com.nurse.healthy.service.Impl;

import com.nurse.healthy.component.SnowflakeComponent;
import com.nurse.healthy.mapper.BusSatisfactionMeasurementMapper;
import com.nurse.healthy.model.entity.business.BusSatisfactionMeasurement;
import com.nurse.healthy.result.UserInfoToken;
import com.nurse.healthy.service.BusSatisfactionMeasurementService;
import com.nurse.healthy.util.OperateUtil;
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
    public BusSatisfactionMeasurement add(BusSatisfactionMeasurement busSatisfactionMeasurement, UserInfoToken userInfo) {
        Example example = new Example(BusSatisfactionMeasurement.class);
        example.createCriteria().andEqualTo("name",busSatisfactionMeasurement.getName()).andEqualTo("phone",busSatisfactionMeasurement.getPhone());
        BusSatisfactionMeasurement b = busSatisfactionMeasurementMapper.selectOneByExample(example);
        if(b==null){
            Long id = snowflakeComponent.getInstance().nextId();
            OperateUtil.onSaveNew(busSatisfactionMeasurement,userInfo,id);
            busSatisfactionMeasurementMapper.insert(busSatisfactionMeasurement);
            return null;
        }
        return b;
    }
}
