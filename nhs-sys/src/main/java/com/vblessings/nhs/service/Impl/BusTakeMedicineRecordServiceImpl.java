package com.vblessings.nhs.service.Impl;

import cn.hutool.core.date.DateTime;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.mapper.BusTakeMedicineRecordMapper;
import com.vblessings.nhs.model.entity.business.BusTakeMedicineRecord;
import com.vblessings.nhs.model.po.business.QueryTakeMedicineRecord;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.business.BusDonationRecordVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusTakeMedicineRecordService;
import com.vblessings.nhs.util.OperateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BusTakeMedicineRecordServiceImpl implements BusTakeMedicineRecordService {
    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private BusTakeMedicineRecordMapper busTakeMedicineRecordMapper;


    @Override
    public void add(BusTakeMedicineRecord busTakeMedicineRecord, UserInfoToken userInfo) {
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(busTakeMedicineRecord,userInfo,id);
        busTakeMedicineRecordMapper.insert(busTakeMedicineRecord);
    }

    @Override
    public void update(BusTakeMedicineRecord busTakeMedicineRecord, UserInfoToken userInfo) {
        busTakeMedicineRecord.setUpdaterId(userInfo.getUserId());
        busTakeMedicineRecord.setUpdateTime(new DateTime());
        busTakeMedicineRecordMapper.updateByPrimaryKeySelective(busTakeMedicineRecord);
    }

    @Override
    public PageVO<BusTakeMedicineRecord> pageTakeMedicine(QueryTakeMedicineRecord queryTakeMedicineRecord) {
        Page<BusTakeMedicineRecord> result = PageHelper.startPage(queryTakeMedicineRecord.getPageNum(), queryTakeMedicineRecord.getPageSize());
        List<BusTakeMedicineRecord> busTakeMedicineRecordList = busTakeMedicineRecordMapper.selectByTime(queryTakeMedicineRecord);
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), busTakeMedicineRecordList);
    }

    @Override
    public void delTakeMedicine(String ids) {
        String[] id = ids.split(",");
        busTakeMedicineRecordMapper.del(id);
    }


}
