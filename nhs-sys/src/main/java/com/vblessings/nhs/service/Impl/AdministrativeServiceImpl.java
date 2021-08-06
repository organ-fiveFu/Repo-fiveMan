package com.vblessings.nhs.service.Impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.exception.ResponseEnum;
import com.vblessings.nhs.mapper.BusAdministrativeWardRoundMapper;
import com.vblessings.nhs.model.entity.business.BusAdministrativeWardRound;
import com.vblessings.nhs.model.po.comprehensive.AdministrativeInsertPO;
import com.vblessings.nhs.model.po.comprehensive.AdministrativeQueryPO;
import com.vblessings.nhs.model.po.comprehensive.AdministrativeUpdatePO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.comprehensive.AdministrativeQueryVO;
import com.vblessings.nhs.model.vo.nurse.BloodSugarQueryVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.AdministrativeService;
import com.vblessings.nhs.util.OperateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("administrativeService")
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class AdministrativeServiceImpl implements AdministrativeService {

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private BusAdministrativeWardRoundMapper busAdministrativeWardRoundMapper;

    @Override
    public boolean insertAdministrative(AdministrativeInsertPO administrativeInsertPO, UserInfoToken userInfoToken) {
        BusAdministrativeWardRound busAdministrativeWardRound = new BusAdministrativeWardRound();
        BeanUtils.copyProperties(administrativeInsertPO, busAdministrativeWardRound);
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(busAdministrativeWardRound, userInfoToken, id);
        log.info("新增行政查房,入参busAdministrativeWardRound:{}", JSON.toJSONString(busAdministrativeWardRound));
        try {
            busAdministrativeWardRoundMapper.insertSelective(busAdministrativeWardRound);
        }catch (Exception e){
            log.error("新增行政查房失败");
            throw ResponseEnum.FILE_INSERT_FAIL.newException("新增行政查房失败");
        }
        return true;
    }

    @Override
    public boolean updateAdministrative(AdministrativeUpdatePO administrativeUpdatePO, UserInfoToken userInfoToken) {
        BusAdministrativeWardRound busAdministrativeWardRound = new BusAdministrativeWardRound();
        BeanUtils.copyProperties(administrativeUpdatePO, busAdministrativeWardRound);
        busAdministrativeWardRound.setUpdaterId(userInfoToken.getUserId());
        busAdministrativeWardRound.setUpdateTime(new Date());
        log.info("更新行政查房,入参busAdministrativeWardRound:{}", JSON.toJSONString(busAdministrativeWardRound));
        try {
            busAdministrativeWardRoundMapper.updateByPrimaryKeySelective(busAdministrativeWardRound);
        }catch (Exception e){
            log.error("更新行政查房失败");
            throw ResponseEnum.FILE_UPDATE_FAIL.newException("更新行政查房失败");
        }
        return true;
    }

    @Override
    public PageVO<AdministrativeQueryVO> queryAdministrativeList(AdministrativeQueryPO administrativeQueryPO) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Page<BloodSugarQueryVO> result = PageHelper.startPage(administrativeQueryPO.getPageNum(), administrativeQueryPO.getPageSize());
        Example example = new Example(BusAdministrativeWardRound.class);
        Example.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(administrativeQueryPO.getWardRounds())){
            criteria.andLike("wardRounds", "%" + administrativeQueryPO.getWardRounds() + "%");
        }
        if (!StringUtils.isEmpty(administrativeQueryPO.getStartTime()) && !StringUtils.isEmpty(administrativeQueryPO.getEndTime())) {
            try {
                Date startTime = simpleDateFormat.parse(administrativeQueryPO.getStartTime());
                Date endTime = simpleDateFormat.parse(administrativeQueryPO.getEndTime() + " 23:59:59");
                criteria.andBetween("wardRoundTime", startTime, endTime);
            } catch (Exception e) {
                throw ResponseEnum.ABNORMAL_DATA_VERIFICATION.newException("日期格式错误");
            }
        }
        criteria.andEqualTo("isDel", 0);
        List<BusAdministrativeWardRound> busAdministrativeWardRoundList = busAdministrativeWardRoundMapper.selectByExample(example);
        if(CollectionUtil.isEmpty(busAdministrativeWardRoundList)){
            return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), new ArrayList<>());
        }
        List<AdministrativeQueryVO> administrativeQueryVOS = new ArrayList<>();
        busAdministrativeWardRoundList.forEach(busAdministrativeWardRound -> {
            AdministrativeQueryVO administrativeQueryVO = new AdministrativeQueryVO();
            BeanUtils.copyProperties(busAdministrativeWardRound, administrativeQueryVO);
            String wardRoundTime = null == busAdministrativeWardRound.getWardRoundTime() ? "" :
                    DateFormatUtils.format(busAdministrativeWardRound.getWardRoundTime(), "yyyy-MM-dd HH:mm:ss");
            administrativeQueryVO.setWardRoundTime(wardRoundTime);
            administrativeQueryVOS.add(administrativeQueryVO);
        });
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), administrativeQueryVOS);
    }

    @Override
    public boolean deleteAdministrative(Long id, UserInfoToken userInfoToken) {
        BusAdministrativeWardRound busAdministrativeWardRound = busAdministrativeWardRoundMapper.selectByPrimaryKey(id);
        busAdministrativeWardRound.setIsDel(1);
        busAdministrativeWardRound.setUpdateTime(new Date());
        busAdministrativeWardRound.setUpdaterId(userInfoToken.getUserId());
        log.info("删除行政查房,入参busAdministrativeWardRound:" + busAdministrativeWardRound);
        try {
            busAdministrativeWardRoundMapper.updateByPrimaryKeySelective(busAdministrativeWardRound);
        }catch (Exception e){
            log.error("删除行政查房失败");
            throw ResponseEnum.DATA_NOT_FOUND.newException("删除行政查房失败");
        }
        return true;
    }
}
