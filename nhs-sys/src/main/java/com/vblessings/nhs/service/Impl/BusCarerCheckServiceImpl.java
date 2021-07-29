package com.vblessings.nhs.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.mapper.BusCarerCheckMapper;
import com.vblessings.nhs.model.entity.business.BusCarerCheck;
import com.vblessings.nhs.model.po.businessVO.QueryCheckVO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusCarerCheckService;
import com.vblessings.nhs.util.OperateUtil;
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
