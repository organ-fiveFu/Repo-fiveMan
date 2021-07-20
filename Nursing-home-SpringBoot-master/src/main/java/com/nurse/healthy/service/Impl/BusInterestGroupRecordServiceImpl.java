package com.nurse.healthy.service.Impl;

import cn.hutool.core.date.DateTime;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nurse.healthy.component.SnowflakeComponent;
import com.nurse.healthy.mapper.BusInterestGroupRecordMapper;
import com.nurse.healthy.model.entity.business.BusDonationRecord;
import com.nurse.healthy.model.entity.business.BusInterestGroupRecord;
import com.nurse.healthy.model.po.businessVO.QueryInterestGroupVO;
import com.nurse.healthy.model.vo.PageVO;
import com.nurse.healthy.result.UserInfoToken;
import com.nurse.healthy.service.BusInterestGroupRecordService;
import com.nurse.healthy.util.OperateUtil;
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
