package com.vblessings.nhs.service.Impl;

import cn.hutool.core.date.DateTime;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.mapper.BusHospitalRecordMapper;
import com.vblessings.nhs.model.entity.business.BusHospitalRecord;
import com.vblessings.nhs.model.po.business.BusHospitalRecordPO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusHospitalRecordService;
import com.vblessings.nhs.util.BusinessNoUtil;
import com.vblessings.nhs.util.OperateUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class BusHospitalRecordServiceImpl implements BusHospitalRecordService {
    @Resource
    private BusHospitalRecordMapper busHospitalRecordMapper;



    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Override
    public void add(BusHospitalRecord busHospitalRecord, UserInfoToken userInfo) {
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(busHospitalRecord,userInfo,id);
        //入院时间
        busHospitalRecord.setAdmissionTime(new Date());
        //住院号
        busHospitalRecord.setBusinessNo(BusinessNoUtil.generateBusinessNo());
        busHospitalRecordMapper.insert(busHospitalRecord);
    }

    /**
     * 分页查询入院登记档案
     * @param userInfo
     * @return
     */
    @Override
    public PageInfo<BusHospitalRecord> select(BusHospitalRecordPO busHospitalRecordPO, UserInfoToken userInfo) {
        PageHelper.startPage(busHospitalRecordPO.getPageNum(), busHospitalRecordPO.getPageSize());
        Example example = new Example(BusHospitalRecord.class);
        Example.Criteria C = example.createCriteria();
        if(Strings.isNotBlank(busHospitalRecordPO.getName())){
           C.andLike("name","%"+busHospitalRecordPO.getName()+"%");
        }
        if(Strings.isNotBlank(busHospitalRecordPO.getBusinessNo())){
            C.andEqualTo("businessNo",busHospitalRecordPO.getBusinessNo());
        }
        C.andEqualTo("isDel",0).andEqualTo("status","0");
        List<BusHospitalRecord> busHospitalRecords = busHospitalRecordMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(busHospitalRecords);
        return pageInfo;
    }

    @Override
    public void out(String businessNo) {
        BusHospitalRecord busHospitalRecord = new BusHospitalRecord();
        busHospitalRecord.setBusinessNo(businessNo);
        busHospitalRecord.setDischargeTime(new Date());
        busHospitalRecord.setStatus("1");

        Example example = new Example(BusHospitalRecord.class);
        example.createCriteria().andEqualTo("businessNo",businessNo);
        busHospitalRecordMapper.updateByExampleSelective(busHospitalRecord,example);
    }

    @Override
    public void update(BusHospitalRecord busHospitalRecord, UserInfoToken userInfo) {
        busHospitalRecord.setUpdaterId(userInfo.getUserId());
        busHospitalRecord.setUpdateTime(new DateTime());
        busHospitalRecordMapper.updateByPrimaryKeySelective(busHospitalRecord);
    }
}
