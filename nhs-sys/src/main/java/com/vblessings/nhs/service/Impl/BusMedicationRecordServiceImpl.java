package com.vblessings.nhs.service.Impl;

import cn.hutool.core.date.DateTime;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.mapper.BusMedicationRecordMapper;
import com.vblessings.nhs.model.entity.business.BusMedicationRecord;
import com.vblessings.nhs.model.entity.business.BusTakeMedicineRecord;
import com.vblessings.nhs.model.po.business.QueryMedicineRecordPO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusMedicationRecordService;
import com.vblessings.nhs.util.OperateUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BusMedicationRecordServiceImpl implements BusMedicationRecordService {
    @Resource
    private BusMedicationRecordMapper busMedicationRecordMapper;

    @Resource
    private SnowflakeComponent snowflakeComponent;
    @Override
    public void add(BusMedicationRecord busMedicationRecord, UserInfoToken userInfo) {
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(busMedicationRecord,userInfo,id);
        busMedicationRecordMapper.insert(busMedicationRecord);
    }

    @Override
    public void update(BusMedicationRecord busMedicationRecord, UserInfoToken userInfo) {
        busMedicationRecord.setUpdaterId(userInfo.getUserId());
        busMedicationRecord.setUpdateTime(new DateTime());
        busMedicationRecordMapper.updateByPrimaryKeySelective(busMedicationRecord);
    }

    @Override
    public PageVO<BusMedicationRecord> pageMedicationRecord(QueryMedicineRecordPO queryMedicineRecordPO) {
        Page<BusMedicationRecord> result = PageHelper.startPage(queryMedicineRecordPO.getPageNum(), queryMedicineRecordPO.getPageSize());
        Example example = new Example(BusMedicationRecord.class);
        Example.Criteria C = example.createCriteria();
        if(queryMedicineRecordPO.getName()!=null){
        C.andLike("name","%"+queryMedicineRecordPO.getName()+"%");}
        C.andEqualTo("isDel",0);
        List<BusMedicationRecord> busMedicationRecordList = busMedicationRecordMapper.selectByExample(example);
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), busMedicationRecordList);
    }

    @Override
    public void delMedicationRecord(String ids) {
        String[] id = ids.split(",");
        busMedicationRecordMapper.del(id);
    }

}
