package com.vblessings.nhs.service.Impl;

import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.exception.MyException;
import com.vblessings.nhs.mapper.BusSatisfactionMeasurementMapper;
import com.vblessings.nhs.model.entity.business.BusSatisfactionMeasurement;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusSatisfactionMeasurementService;
import com.vblessings.nhs.util.OperateUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class BusSatisfactionMeasurementServiceImpl implements BusSatisfactionMeasurementService {
    @Resource
    private BusSatisfactionMeasurementMapper busSatisfactionMeasurementMapper;

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Override
    public void add(BusSatisfactionMeasurement busSatisfactionMeasurement) {
        List<BusSatisfactionMeasurement> b = busSatisfactionMeasurementMapper.selectByTime(busSatisfactionMeasurement.getPhone());
        if(b!= null && b.size()>=1){
            throw new MyException("今天已评价，不可重复提交");
        }
        Long id = snowflakeComponent.getInstance().nextId();
        busSatisfactionMeasurement.setId(id);
        busSatisfactionMeasurement.setCreateTime(new Date());
        busSatisfactionMeasurement.setUpdateTime(new Date());
        busSatisfactionMeasurement.setIsDel(0);
        busSatisfactionMeasurementMapper.insert(busSatisfactionMeasurement);
    }

    @Override
    public BusSatisfactionMeasurement selectMeasurement(String phone) {
        List<BusSatisfactionMeasurement> b = busSatisfactionMeasurementMapper.selectByTime(phone);
        BusSatisfactionMeasurement busSatisfactionMeasurement = new BusSatisfactionMeasurement();
        if (b != null && b.size() > 0) {
            busSatisfactionMeasurement = b.get(0);
        }
        return busSatisfactionMeasurement;
    }
}
