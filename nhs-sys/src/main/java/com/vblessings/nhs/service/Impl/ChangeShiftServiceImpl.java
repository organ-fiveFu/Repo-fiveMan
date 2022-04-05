package com.vblessings.nhs.service.Impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.exception.ResponseEnum;
import com.vblessings.nhs.mapper.BusChangeShiftsMapper;
import com.vblessings.nhs.model.entity.business.BusChangeShifts;
import com.vblessings.nhs.model.po.TimeQueryPO;
import com.vblessings.nhs.model.po.comprehensive.ChangeShiftInsertPO;
import com.vblessings.nhs.model.po.comprehensive.ChangeShiftQueryPO;
import com.vblessings.nhs.model.po.comprehensive.ChangeShiftUpdatePO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.comprehensive.ChangeShiftQueryVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.ChangeShiftService;
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

@Service("changeShiftService")
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ChangeShiftServiceImpl implements ChangeShiftService {

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private BusChangeShiftsMapper busChangeShiftsMapper;

    @Override
    public boolean insertChangeShift(ChangeShiftInsertPO changeShiftInsertPO, UserInfoToken userInfoToken) {
        //查询是否有交接员的记录
        Example example = new Example(BusChangeShifts.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("handoverOfficer", changeShiftInsertPO.getHandoverOfficer());
        criteria.andEqualTo("isDel", 0);
        List<BusChangeShifts> busChangeShiftsList = busChangeShiftsMapper.selectByExample(example);
        if(CollectionUtil.isNotEmpty(busChangeShiftsList)){
            busChangeShiftsList.forEach(busChangeShifts -> {
                //判断交班开始时间是否在历史数据里的时间段内
                DateTime startTime = DateUtil.date(busChangeShifts.getShiftHandoverStartTime());
                DateTime endTime = DateUtil.date(busChangeShifts.getShiftHandoverEndTime());
                DateTime start = DateUtil.date(changeShiftInsertPO.getShiftHandoverStartTime());
                DateTime end = DateUtil.date(changeShiftInsertPO.getShiftHandoverEndTime());
                if (DateUtil.isIn(start, startTime, endTime)) {
                    throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该交班员的交班开始时间在历史数据的开始时间和结束时间的时间段里，无法新增");
                }
                //判断交班结束时间是否在历史数据里的时间段内
                if(DateUtil.isIn(end, startTime, endTime)){
                    throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该交班员的交班结束时间在历史数据的开始时间和结束时间的时间段里，无法新增");
                }
            });
        }
        //新增
        BusChangeShifts busChangeShifts = new BusChangeShifts();
        BeanUtils.copyProperties(changeShiftInsertPO, busChangeShifts);
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(busChangeShifts, userInfoToken, id);
        busChangeShifts.setSubmissionTime(new Date());
        log.info("新增交接班,入参busChangeShifts:{}", JSON.toJSONString(busChangeShifts));
        try {
            busChangeShiftsMapper.insertSelective(busChangeShifts);
        }catch (Exception e){
            log.error("新增交接班失败");
            throw ResponseEnum.FILE_INSERT_FAIL.newException("新增交接班失败");
        }
        return true;
    }

    @Override
    public boolean updateChangeShift(ChangeShiftUpdatePO changeShiftUpdatePO, UserInfoToken userInfoToken) {
        //查询是否有交接员的记录
        Example example = new Example(BusChangeShifts.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("handoverOfficer", changeShiftUpdatePO.getHandoverOfficer());
        criteria.andEqualTo("isDel", 0);
        criteria.andNotEqualTo("id", changeShiftUpdatePO.getId());
        List<BusChangeShifts> busChangeShiftsList = busChangeShiftsMapper.selectByExample(example);
        if(CollectionUtil.isNotEmpty(busChangeShiftsList)){
            busChangeShiftsList.forEach(busChangeShifts -> {
                //判断交班开始时间是否在历史数据里的时间段内
                DateTime startTime = DateUtil.date(busChangeShifts.getShiftHandoverStartTime());
                DateTime endTime = DateUtil.date(busChangeShifts.getShiftHandoverEndTime());
                DateTime start = DateUtil.date(changeShiftUpdatePO.getShiftHandoverStartTime());
                DateTime end = DateUtil.date(changeShiftUpdatePO.getShiftHandoverEndTime());
                if (DateUtil.isIn(start, startTime, endTime)) {
                    throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该交班员的交班开始时间在历史数据的开始时间和结束时间的时间段里，无法更新");
                }
                //判断交班结束时间是否在历史数据里的时间段内
                if(DateUtil.isIn(end, startTime, endTime)){
                    throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该交班员的交班结束时间在历史数据的开始时间和结束时间的时间段里，无法更新");
                }
            });
        }
        //更新
        BusChangeShifts busChangeShifts = new BusChangeShifts();
        BeanUtils.copyProperties(changeShiftUpdatePO, busChangeShifts);
        busChangeShifts.setUpdaterId(userInfoToken.getUserId());
        busChangeShifts.setUpdateTime(new Date());
        log.info("更新交接班,入参busChangeShifts:{}", JSON.toJSONString(busChangeShifts));
        try {
            busChangeShiftsMapper.updateByPrimaryKeySelective(busChangeShifts);
        }catch (Exception e){
            log.error("更新交接班失败");
            throw ResponseEnum.FILE_UPDATE_FAIL.newException("更新交接班失败");
        }
        return true;
    }

    @Override
    public PageVO<ChangeShiftQueryVO> queryChangeShiftList(ChangeShiftQueryPO changeShiftQueryPO) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Page<ChangeShiftQueryVO> result = PageHelper.startPage(changeShiftQueryPO.getPageNum(), changeShiftQueryPO.getPageSize());
        Example example = new Example(BusChangeShifts.class);
        Example.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(changeShiftQueryPO.getHandoverOfficer())){
            criteria.andLike("handoverOfficer", "%" + changeShiftQueryPO.getHandoverOfficer() + "%");
        }
        if (!StringUtils.isEmpty(changeShiftQueryPO.getStartTime()) && !StringUtils.isEmpty(changeShiftQueryPO.getEndTime())) {
            try {
                Date startTime = simpleDateFormat.parse(changeShiftQueryPO.getStartTime());
                Date endTime = simpleDateFormat.parse(changeShiftQueryPO.getEndTime() + " 23:59:59");
                criteria.andBetween("shiftHandoverStartTime", startTime, endTime);
            } catch (Exception e) {
                throw ResponseEnum.ABNORMAL_DATA_VERIFICATION.newException("日期格式错误");
            }
        }
        criteria.andEqualTo("isDel", 0);
        List<BusChangeShifts> busChangeShiftsList = busChangeShiftsMapper.selectByExample(example);
        if(CollectionUtil.isEmpty(busChangeShiftsList)){
            return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), new ArrayList<>());
        }
        List<ChangeShiftQueryVO> changeShiftQueryVOList = new ArrayList<>();
        busChangeShiftsList.forEach(busChangeShifts -> {
            ChangeShiftQueryVO changeShiftQueryVO = new ChangeShiftQueryVO();
            BeanUtils.copyProperties(busChangeShifts, changeShiftQueryVO);
            String submissionTime = null == busChangeShifts.getSubmissionTime() ? "" :
                    DateFormatUtils.format(busChangeShifts.getSubmissionTime(), "yyyy-MM-dd HH:mm:ss");
            String shiftHandoverStartTime = null == busChangeShifts.getShiftHandoverStartTime() ? "" :
                    DateFormatUtils.format(busChangeShifts.getShiftHandoverStartTime(), "yyyy-MM-dd HH:mm:ss");
            String shiftHandoverEndTime = null == busChangeShifts.getShiftHandoverEndTime() ? "" :
                    DateFormatUtils.format(busChangeShifts.getShiftHandoverEndTime(), "yyyy-MM-dd HH:mm:ss");
            changeShiftQueryVO.setSubmissionTime(submissionTime);
            changeShiftQueryVO.setShiftHandoverStartTime(shiftHandoverStartTime);
            changeShiftQueryVO.setShiftHandoverEndTime(shiftHandoverEndTime);
            changeShiftQueryVOList.add(changeShiftQueryVO);
        });
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), changeShiftQueryVOList);
    }

    @Override
    public boolean deleteChangeShift(Long id, UserInfoToken userInfoToken) {
        BusChangeShifts busChangeShifts = busChangeShiftsMapper.selectByPrimaryKey(id);
        busChangeShifts.setIsDel(1);
        busChangeShifts.setUpdateTime(new Date());
        busChangeShifts.setUpdaterId(userInfoToken.getUserId());
        log.info("删除交接班,入参busChangeShifts:" + busChangeShifts);
        try {
            busChangeShiftsMapper.updateByPrimaryKeySelective(busChangeShifts);
        }catch (Exception e){
            log.error("删除交接班失败");
            throw ResponseEnum.FILE_DELETE_FAIL.newException("删除交接班失败");
        }
        return true;
    }

    @Override
    public List<ChangeShiftQueryVO> queryChangeShiftNoToken(TimeQueryPO timeQueryPO) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Example example = new Example(BusChangeShifts.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(timeQueryPO.getStartTime()) && !StringUtils.isEmpty(timeQueryPO.getEndTime())) {
            try {
                Date startTime = simpleDateFormat.parse(timeQueryPO.getStartTime());
                Date endTime = simpleDateFormat.parse(timeQueryPO.getEndTime() + " 23:59:59");
                criteria.andBetween("shiftHandoverStartTime", startTime, endTime);
            } catch (Exception e) {
                throw ResponseEnum.ABNORMAL_DATA_VERIFICATION.newException("日期格式错误");
            }
        }
        criteria.andEqualTo("isDel", 0);
        List<BusChangeShifts> busChangeShiftsList = busChangeShiftsMapper.selectByExample(example);
        if(CollectionUtil.isEmpty(busChangeShiftsList)){
            return new ArrayList<>();
        }
        List<ChangeShiftQueryVO> changeShiftQueryVOList = new ArrayList<>();
        busChangeShiftsList.forEach(busChangeShifts -> {
            ChangeShiftQueryVO changeShiftQueryVO = new ChangeShiftQueryVO();
            BeanUtils.copyProperties(busChangeShifts, changeShiftQueryVO);
            String submissionTime = null == busChangeShifts.getSubmissionTime() ? "" :
                    DateFormatUtils.format(busChangeShifts.getSubmissionTime(), "yyyy-MM-dd HH:mm:ss");
            String shiftHandoverStartTime = null == busChangeShifts.getShiftHandoverStartTime() ? "" :
                    DateFormatUtils.format(busChangeShifts.getShiftHandoverStartTime(), "yyyy-MM-dd HH:mm:ss");
            String shiftHandoverEndTime = null == busChangeShifts.getShiftHandoverEndTime() ? "" :
                    DateFormatUtils.format(busChangeShifts.getShiftHandoverEndTime(), "yyyy-MM-dd HH:mm:ss");
            changeShiftQueryVO.setSubmissionTime(submissionTime);
            changeShiftQueryVO.setShiftHandoverStartTime(shiftHandoverStartTime);
            changeShiftQueryVO.setShiftHandoverEndTime(shiftHandoverEndTime);
            changeShiftQueryVOList.add(changeShiftQueryVO);
        });
        return changeShiftQueryVOList;
    }
}
