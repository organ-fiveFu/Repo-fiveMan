package com.vblessings.nhs.service.Impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.exception.ResponseEnum;
import com.vblessings.nhs.mapper.BusRiskNotificationMapper;
import com.vblessings.nhs.model.entity.business.BusRiskNotification;
import com.vblessings.nhs.model.po.nurse.RiskNotificationInsertPO;
import com.vblessings.nhs.model.po.nurse.RiskNotificationQueryPO;
import com.vblessings.nhs.model.po.nurse.RiskNotificationUpdatePO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.nurse.RiskNotificationQueryVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.RiskNotificationService;
import com.vblessings.nhs.util.OperateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class RiskNotificationServiceImpl implements RiskNotificationService {

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private BusRiskNotificationMapper busRiskNotificationMapper;

    @Override
    public boolean insertRiskNotification(RiskNotificationInsertPO riskNotificationInsertPO, UserInfoToken userInfoToken) {
        //判断住院号是否重复
        Example example = new Example(BusRiskNotification.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("businessNo", riskNotificationInsertPO.getBusinessNo());
        criteria.andEqualTo("isDel", 0);
        int count = busRiskNotificationMapper.selectCountByExample(example);
        if(count > 0){
            throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该病人已签订风险告知书，无法新增");
        }
        //新增
        BusRiskNotification busRiskNotification = new BusRiskNotification();
        BeanUtils.copyProperties(riskNotificationInsertPO, busRiskNotification);
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(busRiskNotification, userInfoToken, id);
        busRiskNotification.setIsSignNotification("1");
        log.info("新增风险告知书信息,入参busRiskNotification:{}", JSON.toJSONString(busRiskNotification));
        try {
            busRiskNotificationMapper.insertSelective(busRiskNotification);
        }catch (Exception e){
            log.error("新增风险告知书信息失败");
            throw ResponseEnum.FILE_INSERT_FAIL.newException("新增风险告知书信息失败");
        }
        return true;
    }

    @Override
    public boolean updateRiskNotification(RiskNotificationUpdatePO riskNotificationUpdatePO, UserInfoToken userInfoToken) {
        //判断住院号是否重复
        Example example = new Example(BusRiskNotification.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("businessNo", riskNotificationUpdatePO.getBusinessNo());
        criteria.andEqualTo("isDel", 0);
        criteria.andNotEqualTo("id", riskNotificationUpdatePO.getId());
        int count = busRiskNotificationMapper.selectCountByExample(example);
        if(count > 0){
            throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该病人已签订风险告知书，无法更新");
        }
        //新增
        BusRiskNotification busRiskNotification = new BusRiskNotification();
        BeanUtils.copyProperties(riskNotificationUpdatePO, busRiskNotification);
        busRiskNotification.setUpdaterId(userInfoToken.getUserId());
        busRiskNotification.setUpdateTime(new Date());
        log.info("更新风险告知书信息,入参busRiskNotification:{}", JSON.toJSONString(busRiskNotification));
        try {
            busRiskNotificationMapper.updateByPrimaryKeySelective(busRiskNotification);
        }catch (Exception e){
            log.error("更新风险告知书信息失败");
            throw ResponseEnum.FILE_UPDATE_FAIL.newException("更新风险告知书信息失败");
        }
        return true;
    }

    @Override
    public RiskNotificationQueryVO queryRiskNotificationByBusinessNo(String businessNo) {
        Example example = new Example(BusRiskNotification.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("businessNo", businessNo);
        criteria.andEqualTo("isDel", 0);
        BusRiskNotification busRiskNotification = busRiskNotificationMapper.selectOneByExample(example);
        if(StringUtils.isEmpty(busRiskNotification)){
            return null;
        }
        RiskNotificationQueryVO riskNotificationQueryVO = new RiskNotificationQueryVO();
        BeanUtils.copyProperties(busRiskNotification, riskNotificationQueryVO);
        String informerTime = null == busRiskNotification.getInformerTime() ? "" :
                DateFormatUtils.format(busRiskNotification.getInformerTime(), "yyyy-MM-dd HH:mm:ss");
        String guardianTime = null == busRiskNotification.getGuardianTime() ? "" :
                DateFormatUtils.format(busRiskNotification.getGuardianTime(), "yyyy-MM-dd HH:mm:ss");
        riskNotificationQueryVO.setInformerTime(informerTime);
        riskNotificationQueryVO.setGuardianTime(guardianTime);
        return riskNotificationQueryVO;
    }

    @Override
    public PageVO<RiskNotificationQueryVO> queryRiskNotification(RiskNotificationQueryPO riskNotificationQueryPO) {
        Page<RiskNotificationQueryVO> result = PageHelper.startPage(riskNotificationQueryPO.getPageNum(), riskNotificationQueryPO.getPageSize());
        Example example = new Example(BusRiskNotification.class);
        Example.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(riskNotificationQueryPO.getName())){
            criteria.andLike("name", "%" + riskNotificationQueryPO.getName() + "%");
        }
        if(!StringUtils.isEmpty(riskNotificationQueryPO.getBusinessNo())){
            criteria.andEqualTo("businessNo", riskNotificationQueryPO.getBusinessNo());
        }
        criteria.andEqualTo("isDel", 0);
        List<BusRiskNotification> busRiskNotificationList = busRiskNotificationMapper.selectByExample(example);
        if(CollectionUtil.isEmpty(busRiskNotificationList)){
            return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), new ArrayList<>());
        }
        List<RiskNotificationQueryVO> riskNotificationQueryVOList = new ArrayList<>();
        busRiskNotificationList.forEach(busRiskNotification -> {
            RiskNotificationQueryVO riskNotificationQueryVO = new RiskNotificationQueryVO();
            BeanUtils.copyProperties(busRiskNotification, riskNotificationQueryVO);
            String informerTime = null == busRiskNotification.getInformerTime() ? "" :
                    DateFormatUtils.format(busRiskNotification.getInformerTime(), "yyyy-MM-dd HH:mm:ss");
            String guardianTime = null == busRiskNotification.getGuardianTime() ? "" :
                    DateFormatUtils.format(busRiskNotification.getGuardianTime(), "yyyy-MM-dd HH:mm:ss");
            riskNotificationQueryVO.setInformerTime(informerTime);
            riskNotificationQueryVO.setGuardianTime(guardianTime);
            riskNotificationQueryVOList.add(riskNotificationQueryVO);
        });
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), riskNotificationQueryVOList);
    }
}
