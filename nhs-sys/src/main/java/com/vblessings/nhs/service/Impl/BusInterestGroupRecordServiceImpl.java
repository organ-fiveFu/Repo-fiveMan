package com.vblessings.nhs.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.mapper.BusInterestGroupRecordMapper;
import com.vblessings.nhs.model.entity.business.BusDonationRecord;
import com.vblessings.nhs.model.entity.business.BusInterestGroupRecord;
import com.vblessings.nhs.model.po.BusInterestGroupRecordPO;
import com.vblessings.nhs.model.po.businessVO.QueryInterestGroupVO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.business.BusInterestGroupRecordVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusInterestGroupRecordService;
import com.vblessings.nhs.util.OperateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusInterestGroupRecordServiceImpl implements BusInterestGroupRecordService {
    @Resource
    private SnowflakeComponent snowflakeComponent;
    @Resource
    private BusInterestGroupRecordMapper busInterestGroupRecordMapper;

    @Override
    public void addInterestGroup(BusInterestGroupRecord busInterestGroupRecord, UserInfoToken userInfo) {
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(busInterestGroupRecord,userInfo,id);
        busInterestGroupRecordMapper.insert(busInterestGroupRecord);
    }

    @Override
    public PageVO<BusInterestGroupRecordVO> pageInterestGroup(QueryInterestGroupVO queryInterestGroupVO) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Page<BusInterestGroupRecordVO> result = PageHelper.startPage(queryInterestGroupVO.getPageNum(), queryInterestGroupVO.getPageSize());
        List<BusInterestGroupRecord> busInterestGroupRecordList =busInterestGroupRecordMapper.selectByTime(queryInterestGroupVO);
        List<BusInterestGroupRecordVO> busInterestGroupRecordVOList = new ArrayList<>();
        for (BusInterestGroupRecord busInterestGroupRecord:
        busInterestGroupRecordList) {
            BusInterestGroupRecordVO busInterestGroupRecordVO = new BusInterestGroupRecordVO();
            BeanUtils.copyProperties(busInterestGroupRecord,busInterestGroupRecordVO);
            busInterestGroupRecordVO.setActivityDate(sdf.format(busInterestGroupRecord.getActivityDate()));
            busInterestGroupRecordVOList.add(busInterestGroupRecordVO);
        }
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), busInterestGroupRecordVOList);
    }

    @Override
    public void updateInterestGroup(BusInterestGroupRecord busInterestGroupRecord, UserInfoToken userInfo) {
        busInterestGroupRecord.setUpdaterId(userInfo.getUserId());
        busInterestGroupRecord.setUpdateTime(new DateTime());
        busInterestGroupRecordMapper.updateByPrimaryKeySelective(busInterestGroupRecord);
    }

    @Override
    public void delInterestGroup(String ids) {
        String[] id = ids.split(",");
        busInterestGroupRecordMapper.delInterestGroup(id);
    }

    @Override
    public List<BusInterestGroupRecordVO> queryInterestGroupNoToken(QueryInterestGroupVO queryInterestGroupVO) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<BusInterestGroupRecord> busInterestGroupRecordList =busInterestGroupRecordMapper.selectByTime(queryInterestGroupVO);
        List<BusInterestGroupRecordVO> busInterestGroupRecordVOList = new ArrayList<>();
        for (BusInterestGroupRecord busInterestGroupRecord:
                busInterestGroupRecordList) {
            BusInterestGroupRecordVO busInterestGroupRecordVO = new BusInterestGroupRecordVO();
            BeanUtils.copyProperties(busInterestGroupRecord,busInterestGroupRecordVO);
            busInterestGroupRecordVO.setActivityDate(sdf.format(busInterestGroupRecord.getActivityDate()));
            busInterestGroupRecordVOList.add(busInterestGroupRecordVO);
        }
        return busInterestGroupRecordVOList;
    }
}
