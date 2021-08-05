package com.vblessings.nhs.service.Impl;

import cn.hutool.core.date.DateTime;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.mapper.BusCarerCheckMapper;
import com.vblessings.nhs.model.entity.business.BusCarerCheck;
import com.vblessings.nhs.model.po.businessVO.QueryCheckVO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.business.BusCarerCheckVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusCarerCheckService;
import com.vblessings.nhs.util.OperateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public PageVO<BusCarerCheckVO> pageCheck(QueryCheckVO queryCheckVO) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Page<BusCarerCheckVO> result = PageHelper.startPage(queryCheckVO.getPageNum(), queryCheckVO.getPageSize());
        List<BusCarerCheck> busCarerChecks =busCarerCheckMapper.selectByTime(queryCheckVO);
        List<BusCarerCheckVO> busCarerCheckVOList = new ArrayList<>();
        for (BusCarerCheck busCarerCheck:
        busCarerChecks) {
            BusCarerCheckVO busCarerCheckVO = new BusCarerCheckVO();
            BeanUtils.copyProperties(busCarerCheck,busCarerCheckVO);
            busCarerCheckVO.setInspectionTime(sdf.format(busCarerCheck.getInspectionTime()));
            busCarerCheckVOList.add(busCarerCheckVO);
        }
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), busCarerCheckVOList);

    }

    @Override
    public void updateCheck(BusCarerCheck busCarerCheck, UserInfoToken userInfo) {
        busCarerCheck.setUpdaterId(userInfo.getUserId());
        busCarerCheck.setUpdateTime(new DateTime());
        busCarerCheckMapper.updateByPrimaryKeySelective(busCarerCheck);
    }

    @Override
    public void delCheck(String ids) {
        String[] id = ids.split(",");
        busCarerCheckMapper.delCheck(id);
    }

}
