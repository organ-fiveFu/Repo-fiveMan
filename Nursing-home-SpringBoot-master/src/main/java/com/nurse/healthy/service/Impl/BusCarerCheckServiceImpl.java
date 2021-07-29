package com.nurse.healthy.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nurse.healthy.component.SnowflakeComponent;
import com.nurse.healthy.mapper.BusCarerCheckMapper;
import com.nurse.healthy.model.entity.business.BusCarerCheck;
import com.nurse.healthy.model.entity.business.BusDonationRecord;
import com.nurse.healthy.model.entity.business.BusInterestGroupRecord;
import com.nurse.healthy.model.po.businessVO.QueryCheckVO;
import com.nurse.healthy.model.vo.PageVO;
import com.nurse.healthy.result.UserInfoToken;
import com.nurse.healthy.service.BusCarerCheckService;
import com.nurse.healthy.util.OperateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BusCarerCheckServiceImpl implements BusCarerCheckService {

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private BusCarerCheckMapper busCarerCheckMapper;

    @Override
    public void addCheck(BusCarerCheck busCarerCheck, UserInfoToken userInfo) {
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(busCarerCheck,userInfo,id);
        busCarerCheckMapper.insert(busCarerCheck);
    }

    @Override
    public PageVO<BusCarerCheck> pageCheck(QueryCheckVO queryCheckVO) {
        Page<BusCarerCheck> result = PageHelper.startPage(queryCheckVO.getPageNum(), queryCheckVO.getPageSize());
        List<BusCarerCheck> busCarerChecks =busCarerCheckMapper.selectByTime(queryCheckVO);
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), busCarerChecks);

    }

}
