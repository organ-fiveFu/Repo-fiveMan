package com.vblessings.nhs.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.exception.ResponseEnum;
import com.vblessings.nhs.mapper.BusHospitalRecordMapper;
import com.vblessings.nhs.mapper.BusNursingRecordMapper;
import com.vblessings.nhs.model.entity.business.BusHospitalRecord;
import com.vblessings.nhs.model.entity.business.BusNursingRecord;
import com.vblessings.nhs.model.po.business.*;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.business.BusNursingRecordQueryVO;
import com.vblessings.nhs.model.vo.business.BusVitalSignRecordVO;
import com.vblessings.nhs.model.vo.business.BusVitalSignVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusNursingRecordService;
import com.vblessings.nhs.util.DateUtils;
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
public class BusNursingRecordServiceImpl implements BusNursingRecordService {
    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private BusNursingRecordMapper busNursingRecordMapper;

    @Resource
    private BusHospitalRecordMapper busHospitalRecordMapper;

    @Override
    public void addNursingRecord(BusNursingRecordPO busNursingRecordPO, UserInfoToken userInfo) {
        Long id = snowflakeComponent.getInstance().nextId();
        BusNursingRecord busNursingRecord = new BusNursingRecord();
        BeanUtil.copyProperties(busNursingRecordPO, busNursingRecord);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            busNursingRecord.setRecordTime(sdf.parse(busNursingRecordPO.getRecordTime()));
        } catch (ParseException e) {
            throw ResponseEnum.DATA_TRANSFER_ERROR.newException("日期格式转换错误");
        }
        OperateUtil.onSaveNew(busNursingRecord, userInfo, id);
        busNursingRecordMapper.insert(busNursingRecord);
    }

    @Override
    public PageVO<BusNursingRecordPO> pageNursingRecord(QueryNursingRecordPO queryNursingRecordPO) {
        Page<BusNursingRecord> result = PageHelper.startPage(queryNursingRecordPO.getPageNum(), queryNursingRecordPO.getPageSize());
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<BusNursingRecordPO> busNursingRecordPOS = new ArrayList<>();

        if (queryNursingRecordPO.getId() != null) {
            BusNursingRecord busNursingRecord = busNursingRecordMapper.selectByPrimaryKey(queryNursingRecordPO.getId());
            BusNursingRecordPO busNursingRecordPO = new BusNursingRecordPO();
            BeanUtil.copyProperties(busNursingRecord, busNursingRecordPO);
            busNursingRecordPO.setRecordTime(sdf1.format(busNursingRecord.getRecordTime()));
            busNursingRecordPO.setCreateTime(sdf2.format(busNursingRecord.getCreateTime()));
            busNursingRecordPOS.add(busNursingRecordPO);
        } else {
            Example example = new Example(BusNursingRecord.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("isDel", 0);
            if (Strings.isNotBlank(queryNursingRecordPO.getName())) {
                criteria.andLike("name", "%" + queryNursingRecordPO.getName() + "%");
            }
            if (Strings.isNotBlank(queryNursingRecordPO.getBusinessNo())) {
                criteria.andEqualTo("businessNo", queryNursingRecordPO.getBusinessNo());
            }
            List<BusNursingRecord> busNursingRecords = busNursingRecordMapper.selectByExample(example);
            busNursingRecords.forEach(busNursingRecord -> {
                BusNursingRecordPO busNursingRecordPO = new BusNursingRecordPO();
                BeanUtil.copyProperties(busNursingRecord, busNursingRecordPO);
                busNursingRecordPO.setRecordTime(sdf1.format(busNursingRecord.getRecordTime()));
                busNursingRecordPO.setCreateTime(sdf2.format(busNursingRecord.getCreateTime()));
                busNursingRecordPOS.add(busNursingRecordPO);
            });
        }
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), busNursingRecordPOS);
    }

    @Override
    public void updateNursingRecord(BusNursingRecordPO busNursingRecordPO, UserInfoToken userInfo) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        BusNursingRecord busNursingRecord = new BusNursingRecord();
        BeanUtil.copyProperties(busNursingRecordPO, busNursingRecord);
        try {
            busNursingRecord.setRecordTime(sdf1.parse(busNursingRecordPO.getRecordTime()));
            busNursingRecord.setCreateTime(sdf2.parse(busNursingRecordPO.getCreateTime()));
        } catch (ParseException e) {
            throw ResponseEnum.DATA_TRANSFER_ERROR.newException("日期格式转换错误");
        }
        busNursingRecord.setUpdaterId(userInfo.getUserId());
        busNursingRecord.setUpdateTime(new DateTime());
        busNursingRecordMapper.updateByPrimaryKey(busNursingRecord);
    }

    @Override
    public void batchUpdateNursingRecord(List<BusNursingRecordPO> busNursingRecordPOS, UserInfoToken userInfo) {
        busNursingRecordPOS.forEach(busNursingRecordPO -> {
            if (busNursingRecordPO.getId() != null) {
                updateNursingRecord(busNursingRecordPO, userInfo);
            } else {
                addNursingRecord(busNursingRecordPO, userInfo);
            }
        });
    }

    @Override
    public void delNursingRecord(String ids) {
        String[] id = ids.split(",");
        busNursingRecordMapper.batchDel(id);
    }

    @Override
    public BusVitalSignVO queryVitalSignRecord(QueryVitalSignPO queryVitalSignPO) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        BusVitalSignVO busVitalSignVO = new BusVitalSignVO();

        // 查询住院信息
        Example exampleHosp = new Example(BusHospitalRecord.class);
        Example.Criteria criteriaHosp = exampleHosp.createCriteria();
        criteriaHosp.andEqualTo("businessNo", queryVitalSignPO.getBusinessNo());
        criteriaHosp.andEqualTo("isDel", 0);
        List<BusHospitalRecord> busHospitalRecords = busHospitalRecordMapper.selectByExample(exampleHosp);
        if (busHospitalRecords.size() == 0) {
            throw ResponseEnum.DATA_NOT_FOUND.newException("未找到患者住院信息");
        }
        BeanUtil.copyProperties(busHospitalRecords.get(0), busVitalSignVO);

        Example example = new Example(BusNursingRecord.class);
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
        List<BusNursingRecord> busNursingRecords = busNursingRecordMapper.selectByExample(example);
        List<BusVitalSignRecordVO> busVitalSignRecordVOS = new ArrayList<>();
        busNursingRecords.forEach(busNursingRecord -> {
            BusVitalSignRecordVO busVitalSignRecordVO = new BusVitalSignRecordVO();
            BeanUtil.copyProperties(busNursingRecord, busVitalSignRecordVO);
            busVitalSignRecordVO.setRecordTime(sdf.format(busNursingRecord.getRecordTime()));
            busVitalSignRecordVO.setLengthOfStay(DateUtils.differentDaysByMillisecond(
                    busHospitalRecords.get(0).getAdmissionTime(), busNursingRecord.getRecordTime()));
            busVitalSignRecordVOS.add(busVitalSignRecordVO);
        });
        busVitalSignVO.setBusVitalSignRecordVOS(busVitalSignRecordVOS);
        return busVitalSignVO;
    }

    @Override
    public List<BusNursingRecordQueryVO> batchQueryVitalSignRecord(QueryBatchVitalSignPO queryBatchVitalSignPO) {
        return busNursingRecordMapper.batchQueryNursingRecord(queryBatchVitalSignPO.getRecordTime(),
                queryBatchVitalSignPO.getTimePoint());
    }
}
