package com.vblessings.nhs.service.Impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.exception.ResponseEnum;
import com.vblessings.nhs.mapper.BusLeaveApplicationMapper;
import com.vblessings.nhs.model.entity.business.BusLeaveApplication;
import com.vblessings.nhs.model.po.nurse.LeaveApplicationInsertPO;
import com.vblessings.nhs.model.po.nurse.LeaveApplicationQueryPO;
import com.vblessings.nhs.model.po.nurse.LeaveApplicationUpdatePO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.nurse.LeaveApplicationQueryVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.LeaveApplicationService;
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

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class LeaveApplicationServiceImpl implements LeaveApplicationService {

    @Resource
    private BusLeaveApplicationMapper busLeaveApplicationMapper;

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Override
    public boolean insertLeaveApplication(LeaveApplicationInsertPO leaveApplicationInsertPO, UserInfoToken userInfoToken) {
        //查询是否有该病人请假的记录
        Example example = new Example(BusLeaveApplication.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("businessNo", leaveApplicationInsertPO.getBusinessNo());
        criteria.andEqualTo("isDel", 0);
        List<BusLeaveApplication> busLeaveApplicationList = busLeaveApplicationMapper.selectByExample(example);
        if(CollectionUtil.isNotEmpty(busLeaveApplicationList)){
            busLeaveApplicationList.forEach(busLeaveApplication -> {
                if(!StringUtils.isEmpty(busLeaveApplication.getLeaveStartTime()) && !StringUtils.isEmpty(busLeaveApplication.getLeaveEndTime())){
                    //判断病人请假开始时间是否在历史数据里的时间段内
                    DateTime startTime = DateUtil.date(busLeaveApplication.getLeaveStartTime());
                    DateTime endTime = DateUtil.date(busLeaveApplication.getLeaveEndTime());
                    DateTime start = DateUtil.date(leaveApplicationInsertPO.getLeaveStartTime());
                    DateTime end = DateUtil.date(leaveApplicationInsertPO.getLeaveEndTime());
                    if (DateUtil.isIn(start, startTime, endTime)) {
                        throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该病人请假开始时间在历史数据的开始时间和结束时间的时间段里，无法新增");
                    }
                    //判断病人请假结束时间是否在历史数据里的时间段内
                    if(DateUtil.isIn(end, startTime, endTime)){
                        throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该病人请假结束时间在历史数据的开始时间和结束时间的时间段里，无法新增");
                    }
                }
            });
        }
        //新增
        BusLeaveApplication busLeaveApplication = new BusLeaveApplication();
        BeanUtils.copyProperties(leaveApplicationInsertPO, busLeaveApplication);
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(busLeaveApplication, userInfoToken, id);
        log.info("新增请假单信息,入参busLeaveApplication:{}", JSON.toJSONString(busLeaveApplication));
        try {
            busLeaveApplicationMapper.insertSelective(busLeaveApplication);
        }catch (Exception e){
            log.error("新增请假单信息失败");
            throw ResponseEnum.FILE_INSERT_FAIL.newException("新增请假单信息失败");
        }
        return true;
    }

    @Override
    public boolean updateLeaveApplication(LeaveApplicationUpdatePO leaveApplicationUpdatePO, UserInfoToken userInfoToken) {
        //查询是否有该病人请假的记录
        Example example = new Example(BusLeaveApplication.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("businessNo", leaveApplicationUpdatePO.getBusinessNo());
        criteria.andNotEqualTo("id", leaveApplicationUpdatePO.getId());
        criteria.andEqualTo("isDel", 0);
        List<BusLeaveApplication> busLeaveApplicationList = busLeaveApplicationMapper.selectByExample(example);
        if(CollectionUtil.isNotEmpty(busLeaveApplicationList)){
            busLeaveApplicationList.forEach(busLeaveApplication -> {
                //判断病人请假开始时间是否在历史数据里的时间段内
                DateTime startTime = DateUtil.date(busLeaveApplication.getLeaveStartTime());
                DateTime endTime = DateUtil.date(busLeaveApplication.getLeaveEndTime());
                DateTime start = DateUtil.date(leaveApplicationUpdatePO.getLeaveStartTime());
                DateTime end = DateUtil.date(leaveApplicationUpdatePO.getLeaveEndTime());
                if (DateUtil.isIn(start, startTime, endTime)) {
                    throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该病人请假开始时间在历史数据的开始时间和结束时间的时间段里，无法更新");
                }
                //判断病人请假结束时间是否在历史数据里的时间段内
                if(DateUtil.isIn(end, startTime, endTime)){
                    throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该病人请假结束时间在历史数据的开始时间和结束时间的时间段里，无法更新");
                }
            });
        }
        //更新
        BusLeaveApplication busLeaveApplication = new BusLeaveApplication();
        BeanUtils.copyProperties(leaveApplicationUpdatePO, busLeaveApplication);
        busLeaveApplication.setUpdaterId(userInfoToken.getUserId());
        busLeaveApplication.setUpdateTime(new Date());
        log.info("更新请假单信息,入参busLeaveApplication:{}", JSON.toJSONString(busLeaveApplication));
        try {
            busLeaveApplicationMapper.updateByPrimaryKeySelective(busLeaveApplication);
        }catch (Exception e){
            log.error("更新请假单信息失败");
            throw ResponseEnum.FILE_UPDATE_FAIL.newException("更新请假单信息失败");
        }
        return true;
    }

    @Override
    public PageVO<LeaveApplicationQueryVO> queryLeaveApplication(LeaveApplicationQueryPO leaveApplicationQueryPO) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Page<LeaveApplicationQueryVO> result = PageHelper.startPage(leaveApplicationQueryPO.getPageNum(), leaveApplicationQueryPO.getPageSize());
        Example example = new Example(BusLeaveApplication.class);
        Example.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(leaveApplicationQueryPO.getName())){
            criteria.andLike("name", "%" + leaveApplicationQueryPO.getName() + "%");
        }
        if(!StringUtils.isEmpty(leaveApplicationQueryPO.getBusinessNo())){
            criteria.andEqualTo("businessNo", leaveApplicationQueryPO.getBusinessNo());
        }
        if (!StringUtils.isEmpty(leaveApplicationQueryPO.getStartTime()) && !StringUtils.isEmpty(leaveApplicationQueryPO.getEndTime())) {
            try {
                Date startTime = simpleDateFormat.parse(leaveApplicationQueryPO.getStartTime());
                Date endTime = simpleDateFormat.parse(leaveApplicationQueryPO.getEndTime() + " 23:59:59");
                criteria.andBetween("leaveStartTime", startTime, endTime);
            } catch (Exception e) {
                throw ResponseEnum.ABNORMAL_DATA_VERIFICATION.newException("日期格式错误");
            }
        }
        criteria.andEqualTo("isDel", 0);
        List<BusLeaveApplication> busLeaveApplicationList = busLeaveApplicationMapper.selectByExample(example);
        if(CollectionUtil.isEmpty(busLeaveApplicationList)){
            return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), new ArrayList<>());
        }
        List<LeaveApplicationQueryVO> leaveApplicationQueryVOS = new ArrayList<>();
        busLeaveApplicationList.forEach(busLeaveApplication -> {
            LeaveApplicationQueryVO leaveApplicationQueryVO = new LeaveApplicationQueryVO();
            BeanUtils.copyProperties(busLeaveApplication, leaveApplicationQueryVO);
            String leaveStartTime = null == busLeaveApplication.getLeaveStartTime() ? "" :
                    DateFormatUtils.format(busLeaveApplication.getLeaveStartTime(), "yyyy-MM-dd HH:mm:ss");
            String leaveEndTime = null == busLeaveApplication.getLeaveEndTime() ? "" :
                    DateFormatUtils.format(busLeaveApplication.getLeaveEndTime(), "yyyy-MM-dd HH:mm:ss");
            leaveApplicationQueryVO.setLeaveStartTime(leaveStartTime);
            leaveApplicationQueryVO.setLeaveEndTime(leaveEndTime);
            leaveApplicationQueryVOS.add(leaveApplicationQueryVO);
        });
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), leaveApplicationQueryVOS);
    }

    @Override
    public boolean deleteLeaveApplication(Long id, UserInfoToken userInfoToken) {
        BusLeaveApplication busLeaveApplication = busLeaveApplicationMapper.selectByPrimaryKey(id);
        busLeaveApplication.setIsDel(1);
        busLeaveApplication.setUpdateTime(new Date());
        busLeaveApplication.setUpdaterId(userInfoToken.getUserId());
        log.info("删除请假单信息,入参busLeaveApplication:" + busLeaveApplication);
        try {
            busLeaveApplicationMapper.updateByPrimaryKeySelective(busLeaveApplication);
        }catch (Exception e){
            log.error("删除请假单信息失败");
            throw ResponseEnum.FILE_DELETE_FAIL.newException("删除请假单信息失败");
        }
        return true;
    }
}
