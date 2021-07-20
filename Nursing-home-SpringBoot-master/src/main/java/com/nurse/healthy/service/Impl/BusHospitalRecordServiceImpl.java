package com.nurse.healthy.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nurse.healthy.component.SnowflakeComponent;
import com.nurse.healthy.mapper.BusHospitalRecordMapper;
import com.nurse.healthy.model.entity.base.BasePatientInfo;
import com.nurse.healthy.model.entity.business.BusHospitalRecord;
import com.nurse.healthy.result.UserInfoToken;
import com.nurse.healthy.service.BusHospitalRecordService;
import com.nurse.healthy.util.BusinessNoUtil;
import com.nurse.healthy.util.OperateUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
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
     * @param name
     * @param businessNo
     * @param userInfo
     * @return
     */
    @Override
    public PageInfo<BusHospitalRecord> select(String name, String businessNo,int pageNum, int pageSize, UserInfoToken userInfo) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(BusHospitalRecord.class);
        Example.Criteria C = example.createCriteria();
        if(Strings.isNotBlank(name)){
           C.andLike("name","%"+name+"%");
        }
        if(Strings.isNotBlank(businessNo)){
            C.andEqualTo("businessNo",businessNo);
        }
        C.andEqualTo("isDel",0);
        List<BusHospitalRecord> busHospitalRecords = busHospitalRecordMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(busHospitalRecords);
        return pageInfo;
    }
}
