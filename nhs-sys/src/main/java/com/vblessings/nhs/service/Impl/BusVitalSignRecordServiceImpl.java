package com.vblessings.nhs.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.exception.ResponseEnum;
import com.vblessings.nhs.mapper.BusVitalSignRecordMapper;
import com.vblessings.nhs.model.entity.business.BusNursingRecord;
import com.vblessings.nhs.model.entity.business.BusVitalSignRecord;
import com.vblessings.nhs.model.po.business.*;
import com.vblessings.nhs.model.vo.business.BusVitalSignRecordVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusVitalSignRecordService;
import com.vblessings.nhs.util.OperateUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BusVitalSignRecordServiceImpl implements BusVitalSignRecordService {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private BusVitalSignRecordMapper busVitalSignRecordMapper;

    @Override
    public void addVitalSignRecord(BusVitalSignRecordPO busVitalSignRecordPO, UserInfoToken userInfo) {
        Long id = snowflakeComponent.getInstance().nextId();
        BusVitalSignRecord busVitalSignRecord = new BusVitalSignRecord();
        BeanUtil.copyProperties(busVitalSignRecordPO, busVitalSignRecord);
        try {
            busVitalSignRecord.setRecordTime(sdf.parse(busVitalSignRecordPO.getRecordTime()));
        } catch (ParseException e) {
            throw ResponseEnum.DATA_TRANSFER_ERROR.newException("日期格式转换错误");
        }
        OperateUtil.onSaveNew(busVitalSignRecord, userInfo, id);
        busVitalSignRecordMapper.insert(busVitalSignRecord);
    }

    @Override
    public void batchUpdateVitalSignRecord(List<BusVitalSignRecordPO> busVitalSignRecordPOS, UserInfoToken userInfo) {
        Date now = new Date();
        busVitalSignRecordPOS.forEach(busVitalSignRecordPO -> {
            if (busVitalSignRecordPO.getId() != null) {
                BusVitalSignRecord busVitalSignRecord = new BusVitalSignRecord();
                BeanUtil.copyProperties(busVitalSignRecordPO, busVitalSignRecord);
                try {
                    busVitalSignRecord.setRecordTime(sdf.parse(busVitalSignRecordPO.getRecordTime()));
                } catch (ParseException e) {
                    throw ResponseEnum.DATA_TRANSFER_ERROR.newException("日期格式转换错误");
                }
                busVitalSignRecord.setUpdaterId(userInfo.getUserId());
                busVitalSignRecord.setUpdateTime(now);
                busVitalSignRecordMapper.updateByPrimaryKey(busVitalSignRecord);
            } else {
                addVitalSignRecord(busVitalSignRecordPO, userInfo);
            }
        });

    }

    @Override
    public List<BusVitalSignRecordPO> queryVitalSignRecord(QueryVitalSignPO queryVitalSignPO) {
        Example example = new Example(BusVitalSignRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("businessNo", queryVitalSignPO.getBusinessNo());
        criteria.andEqualTo("isDel", 0);
        if (Strings.isNotBlank(queryVitalSignPO.getStartTime()) && Strings.isNotBlank(queryVitalSignPO.getEndTime())) {
            try {
                Date start = sdf.parse(queryVitalSignPO.getStartTime());
                Date end = sdf.parse(queryVitalSignPO.getEndTime());
                criteria.andBetween("recordTime", start, end);
            } catch (ParseException e) {
                throw ResponseEnum.DATA_TRANSFER_ERROR.newException("日期格式转换错误");
            }
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            Date end = calendar.getTime();
            calendar.add(Calendar.DAY_OF_MONTH, -6);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Date start = calendar.getTime();
            criteria.andBetween("recordTime", start, end);
        }
        List<BusVitalSignRecord> busVitalSignRecords = busVitalSignRecordMapper.selectByExample(example);
        List<BusVitalSignRecordPO> busVitalSignRecordPOS = new ArrayList<>();
        busVitalSignRecords.forEach(busVitalSignRecord -> {
            BusVitalSignRecordPO busVitalSignRecordPO = new BusVitalSignRecordPO();
            BeanUtil.copyProperties(busVitalSignRecord, busVitalSignRecordPO);
            busVitalSignRecordPO.setRecordTime(sdf.format(busVitalSignRecord.getRecordTime()));
            busVitalSignRecordPOS.add(busVitalSignRecordPO);
        });
        return busVitalSignRecordPOS;
    }

    @Override
    public List<BusVitalSignRecordVO> batchQueryVitalSignRecord(QueryBatchVitalSignPO queryBatchVitalSignPO) {
        return busVitalSignRecordMapper.batchQueryVitalSignRecord(queryBatchVitalSignPO.getRecordTime());
    }

    @Override
    public void delVitalSignRecord(String ids) {
        String[] id = ids.split(",");
        busVitalSignRecordMapper.batchDel(id);
    }
}
