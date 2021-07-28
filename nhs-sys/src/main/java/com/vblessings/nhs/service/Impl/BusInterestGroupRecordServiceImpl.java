package com.vblessings.nhs.service.Impl;

import cn.hutool.core.date.DateTime;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.mapper.BusInterestGroupRecordMapper;
import com.vblessings.nhs.model.entity.business.BusDonationRecord;
import com.vblessings.nhs.model.entity.business.BusInterestGroupRecord;
import com.vblessings.nhs.model.po.businessVO.QueryInterestGroupVO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusInterestGroupRecordService;
import com.vblessings.nhs.util.OperateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BusInterestGroupRecordServiceImpl implements BusInterestGroupRecordService {
    @Resource
    private SnowflakeComponent snowflakeComponent;
    @Resource
    private BusInterestGroupRecordMapper busInterestGroupRecordMapper;

    @Override
    public void addInterestGroup(BusInterestGroupRecord busDonationRecord, UserInfoToken userInfo) {
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(busDonationRecord,userInfo,id);
        busInterestGroupRecordMapper.insert(busDonationRecord);
    }

    @Override
    public PageVO<BusInterestGroupRecord> pageInterestGroup(QueryInterestGroupVO queryInterestGroupVO) {
        Page<BusDonationRecord> result = PageHelper.startPage(queryInterestGroupVO.getPageNum(), queryInterestGroupVO.getPageSize());
        List<BusInterestGroupRecord> busInterestGroupRecordList =busInterestGroupRecordMapper.selectByTime(queryInterestGroupVO);
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), busInterestGroupRecordList);
    }

    @Override
    public void updateInterestGroup(BusInterestGroupRecord busInterestGroupRecord, UserInfoToken userInfo) {
        busInterestGroupRecord.setUpdaterId(userInfo.getUserId());
        busInterestGroupRecord.setUpdateTime(new DateTime());
        busInterestGroupRecordMapper.updateByPrimaryKeySelective(busInterestGroupRecord);
    }

    @Override
    public void delInterestGroup(Long id) {
        busInterestGroupRecordMapper.delInterestGroup(id);
    }
}
