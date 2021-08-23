package com.vblessings.nhs.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.exception.ResponseEnum;
import com.vblessings.nhs.mapper.*;
import com.vblessings.nhs.model.po.business.ExamArchiveInsertPO;
import com.vblessings.nhs.model.po.business.ExamArchiveUpdatePO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusExamArchiveService;
import com.vblessings.nhs.util.OperateUtil;
import com.vblessings.nhs.model.entity.business.*;
import com.vblessings.nhs.model.vo.business.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class BusExamArchiveServiceImpl implements BusExamArchiveService {

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private BusExamArchiveMapper busExamArchiveMapper;

    @Resource
    private BusExamEntArchiveMapper busExamEntArchiveMapper;

    @Resource
    private BusExamEyesArchiveMapper busExamEyesArchiveMapper;

    @Resource
    private BusExamInternalArchiveMapper busExamInternalArchiveMapper;

    @Resource
    private BusExamOtherArchiveMapper busExamOtherArchiveMapper;

    @Resource
    private BusExamSurgicalArchiveMapper busExamSurgicalArchiveMapper;


    /**
     * 新增体检档案
     * @param examArchiveInsertPO 入参
     * @param userInfo token
     */
    @Override
    public boolean save(ExamArchiveInsertPO examArchiveInsertPO, UserInfoToken userInfo) {
        //主表相关数据
        BusExamArchive busExamArchive = new BusExamArchive();
        //从表相关数据
        BusExamEntArchive busExamEntArchive = new BusExamEntArchive();
        BusExamEyesArchive busExamEyesArchive = new BusExamEyesArchive();
        BusExamInternalArchive busExamInternalArchive = new BusExamInternalArchive();
        BusExamOtherArchive busExamOtherArchive = new BusExamOtherArchive();
        BusExamSurgicalArchive busExamSurgicalArchive = new BusExamSurgicalArchive();

        BeanUtil.copyProperties(examArchiveInsertPO.getBusExamArchiveInsertPO(),busExamArchive);
        BeanUtil.copyProperties(examArchiveInsertPO.getBusExamEntArchiveInsertPO(),busExamEntArchive);
        BeanUtil.copyProperties(examArchiveInsertPO.getBusExamEyesArchiveInsertPO(),busExamEyesArchive);
        BeanUtil.copyProperties(examArchiveInsertPO.getBusExamInternalArchiveInsertPO(),busExamInternalArchive);
        BeanUtil.copyProperties(examArchiveInsertPO.getBusExamOtherArchiveInsertPO(),busExamOtherArchive);
        BeanUtil.copyProperties(examArchiveInsertPO.getBusExamSurgicalArchiveInsertPO(),busExamSurgicalArchive);

        //主表id
        Long id = snowflakeComponent.getInstance().nextId();
        //新增体检登记主表数据
        OperateUtil.onSaveNew(busExamArchive,userInfo,id);
        log.info("新增体检登记主表数据,入参busExamArchive:" + busExamArchive);
        try {
            busExamArchiveMapper.insertSelective(busExamArchive);
        }catch (Exception e){
            log.error("新增体检登记主表数据失败");
            throw ResponseEnum.FILE_INSERT_FAIL.newException("新增体检登记主表数据失败");
        }

        //新增从表数据
        Long entId = snowflakeComponent.getInstance().nextId();
        busExamEntArchive.setExamId(id);
        busExamEntArchive.setId(entId);
        log.info("新增体检登记-五官科数据,入参busExamEntArchive:" + busExamEntArchive);
        try {
            busExamEntArchiveMapper.insertSelective(busExamEntArchive);
        }catch (Exception e){
            log.error("新增体检登记-五官科数据失败");
            throw ResponseEnum.FILE_INSERT_FAIL.newException("新增体检登记-五官科数据失败");
        }

        Long eyeId = snowflakeComponent.getInstance().nextId();
        busExamEyesArchive.setExamId(id);
        busExamEyesArchive.setId(eyeId);
        log.info("新增体检登记-眼科数据,入参busExamEyesArchive:" + busExamEyesArchive);
        try {
            busExamEyesArchiveMapper.insertSelective(busExamEyesArchive);
        }catch (Exception e){
            log.error("新增体检登记-眼科数据失败");
            throw ResponseEnum.FILE_INSERT_FAIL.newException("新增体检登记-眼科数据失败");
        }

        Long internalId = snowflakeComponent.getInstance().nextId();
        busExamInternalArchive.setExamId(id);
        busExamInternalArchive.setId(internalId);
        log.info("新增体检登记-内科数据,入参busExamInternalArchive:" + busExamInternalArchive);
        try {
            busExamInternalArchiveMapper.insertSelective(busExamInternalArchive);
        }catch (Exception e){
            log.error("新增体检登记-内科数据失败");
            throw ResponseEnum.FILE_INSERT_FAIL.newException("新增体检登记-内科数据失败");
        }

        Long surgicalId = snowflakeComponent.getInstance().nextId();
        busExamSurgicalArchive.setExamId(id);
        busExamSurgicalArchive.setId(surgicalId);
        log.info("新增体检登记-外科数据,入参busExamSurgicalArchive:" + busExamSurgicalArchive);
        try {
            busExamSurgicalArchiveMapper.insertSelective(busExamSurgicalArchive);
        }catch (Exception e){
            log.error("新增体检登记-外科数据失败");
            throw ResponseEnum.FILE_INSERT_FAIL.newException("新增体检登记-外科数据失败");
        }

        Long otherId = snowflakeComponent.getInstance().nextId();
        busExamOtherArchive.setExamId(id);
        busExamOtherArchive.setId(otherId);
        log.info("新增体检登记-其他检查数据,入参busExamOtherArchive:" + busExamOtherArchive);
        try {
            busExamOtherArchiveMapper.insertSelective(busExamOtherArchive);
        }catch (Exception e){
            log.error("新增体检登记-其他检查数据失败");
            throw ResponseEnum.FILE_INSERT_FAIL.newException("新增体检登记-其他检查数据失败");
        }
        return true;
    }

    /**
     * 查询体检档案
     * @author linxiazhu
     * @date 15:55 2021/7/6
     * @param businessNo   老人档案id
     * @return  com.nurse.healthy.model.vo.business.ExamArchiveQueryVO
     */
    @Override
    public ExamArchiveQueryVO select(String businessNo) {
        if(StringUtils.isEmpty(businessNo)){
            return null;
        }
        ExamArchiveQueryVO examArchiveQueryVO = new ExamArchiveQueryVO();
        //查询体检档案主表
        Example example = new Example(BusExamArchive.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("businessNo", businessNo);
        criteria.andEqualTo("isDel", 0);
        BusExamArchive busExamArchive = busExamArchiveMapper.selectOneByExample(example);
        if(StringUtils.isEmpty(busExamArchive)){
            return null;
        }
        //赋值主表
        BusExamArchiveQueryVO busExamArchiveQueryVO = new BusExamArchiveQueryVO();
        BeanUtils.copyProperties(busExamArchive, busExamArchiveQueryVO);
        String signTime = null == busExamArchive.getSignTime() ? "" :
                DateFormatUtils.format(busExamArchive.getSignTime(), "yyyy-MM-dd HH:mm:ss");
        busExamArchiveQueryVO.setSignTime(signTime);
        examArchiveQueryVO.setBusExamArchiveQueryVO(busExamArchiveQueryVO);
        //查询体检档案-五官科表并赋值
        BusExamEntArchiveQueryVO busExamEntArchiveQueryVO = new BusExamEntArchiveQueryVO();
        Example entExample = new Example(BusExamEntArchive.class);
        Example.Criteria entCriteria = entExample.createCriteria();
        entCriteria.andEqualTo("examId", busExamArchive.getId());
        BusExamEntArchive busExamEntArchive = busExamEntArchiveMapper.selectOneByExample(entExample);
        if(!StringUtils.isEmpty(busExamEntArchive)){
            BeanUtils.copyProperties(busExamEntArchive, busExamEntArchiveQueryVO);
            examArchiveQueryVO.setBusExamEntArchiveQueryVO(busExamEntArchiveQueryVO);
        }
        //查询体检档案-眼科表并赋值
        BusExamEyesArchiveQueryVO busExamEyesArchiveQueryVO = new BusExamEyesArchiveQueryVO();
        Example eyesExample = new Example(BusExamEyesArchive.class);
        Example.Criteria eyesCriteria = eyesExample.createCriteria();
        eyesCriteria.andEqualTo("examId", busExamArchive.getId());
        BusExamEyesArchive busExamEyesArchive = busExamEyesArchiveMapper.selectOneByExample(eyesExample);
        if(!StringUtils.isEmpty(busExamEyesArchive)){
            BeanUtils.copyProperties(busExamEyesArchive, busExamEyesArchiveQueryVO);
            examArchiveQueryVO.setBusExamEyesArchiveQueryVO(busExamEyesArchiveQueryVO);
        }
        //查询体检档案-内科表并赋值
        BusExamInternalArchiveQueryVO busExamInternalArchiveQueryVO = new BusExamInternalArchiveQueryVO();
        Example internalExample = new Example(BusExamInternalArchive.class);
        Example.Criteria internalCriteria = internalExample.createCriteria();
        internalCriteria.andEqualTo("examId", busExamArchive.getId());
        BusExamInternalArchive busExamInternalArchive = busExamInternalArchiveMapper.selectOneByExample(internalExample);
        if(!StringUtils.isEmpty(busExamInternalArchive)){
            BeanUtils.copyProperties(busExamInternalArchive, busExamInternalArchiveQueryVO);
            examArchiveQueryVO.setBusExamInternalArchiveQueryVO(busExamInternalArchiveQueryVO);
        }
        //查询体检档案-外科表并赋值
        BusExamSurgicalArchiveQueryVO busExamSurgicalArchiveQueryVO = new BusExamSurgicalArchiveQueryVO();
        Example surgicalExample = new Example(BusExamSurgicalArchive.class);
        Example.Criteria surgicalCriteria = surgicalExample.createCriteria();
        surgicalCriteria.andEqualTo("examId", busExamArchive.getId());
        BusExamSurgicalArchive busExamSurgicalArchive = busExamSurgicalArchiveMapper.selectOneByExample(surgicalExample);
        if(!StringUtils.isEmpty(busExamSurgicalArchive)){
            BeanUtils.copyProperties(busExamSurgicalArchive, busExamSurgicalArchiveQueryVO);
            examArchiveQueryVO.setBusExamSurgicalArchiveQueryVO(busExamSurgicalArchiveQueryVO);
        }
        //查询体检档案-其他表并赋值
        BusExamOtherArchiveQueryVO busExamOtherArchiveQueryVO = new BusExamOtherArchiveQueryVO();
        Example otherExample = new Example(BusExamOtherArchive.class);
        Example.Criteria otherCriteria = otherExample.createCriteria();
        otherCriteria.andEqualTo("examId", busExamArchive.getId());
        BusExamOtherArchive busExamOtherArchive = busExamOtherArchiveMapper.selectOneByExample(otherExample);
        if(!StringUtils.isEmpty(busExamOtherArchive)){
            BeanUtils.copyProperties(busExamOtherArchive, busExamOtherArchiveQueryVO);
            examArchiveQueryVO.setBusExamOtherArchiveQueryVO(busExamOtherArchiveQueryVO);
        }
        return examArchiveQueryVO;
    }

    /**
     * 更新体检档案
     * @author linxiazhu
     * @date 15:31 2021/7/6
     * @param examArchiveUpdatePO  入参
     * @param userInfoToken   token
     * @return  boolean
     */
    @Override
    public boolean update(ExamArchiveUpdatePO examArchiveUpdatePO, UserInfoToken userInfoToken) {
        //主表相关数据
        BusExamArchive busExamArchive = new BusExamArchive();
        BeanUtil.copyProperties(examArchiveUpdatePO.getBusExamArchiveUpdatePO(),busExamArchive);
        //五官科
        Example example1 = new Example(BusExamEntArchive.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("examId", busExamArchive.getArchiveId());
        BusExamEntArchive busExamEntArchive = busExamEntArchiveMapper.selectOneByExample(example1);
        BusExamEntArchive busExamEntArchive1 = new BusExamEntArchive();
        if(!StringUtils.isEmpty(busExamEntArchive)){
            BeanUtil.copyProperties(examArchiveUpdatePO.getBusExamEntArchiveUpdatePO(),busExamEntArchive);
        }else {
            BeanUtil.copyProperties(examArchiveUpdatePO.getBusExamArchiveUpdatePO(),busExamEntArchive1);
            busExamEntArchive1.setId(snowflakeComponent.getInstance().nextId());
            busExamEntArchive1.setExamId(busExamArchive.getArchiveId());
        }
        //眼科
        Example example2 = new Example(BusExamEyesArchive.class);
        Example.Criteria criteria2 = example2.createCriteria();
        criteria2.andEqualTo("examId", busExamArchive.getArchiveId());
        BusExamEyesArchive busExamEyesArchive = busExamEyesArchiveMapper.selectOneByExample(example2);
        BusExamEyesArchive busExamEyesArchive1 = new BusExamEyesArchive();
        if(!StringUtils.isEmpty(busExamEyesArchive)){
            BeanUtil.copyProperties(examArchiveUpdatePO.getBusExamEyesArchiveUpdatePO(),busExamEyesArchive);
        }else {
            BeanUtil.copyProperties(examArchiveUpdatePO.getBusExamEyesArchiveUpdatePO(),busExamEyesArchive1);
            busExamEyesArchive1.setId(snowflakeComponent.getInstance().nextId());
            busExamEyesArchive1.setExamId(busExamArchive.getArchiveId());
        }
        //内科
        Example example3 = new Example(BusExamInternalArchive.class);
        Example.Criteria criteria3 = example3.createCriteria();
        criteria3.andEqualTo("examId", busExamArchive.getArchiveId());
        BusExamInternalArchive busExamInternalArchive = busExamInternalArchiveMapper.selectOneByExample(example3);
        BusExamInternalArchive busExamInternalArchive1 = new BusExamInternalArchive();
        if(!StringUtils.isEmpty(busExamInternalArchive)){
            BeanUtil.copyProperties(examArchiveUpdatePO.getBusExamInternalArchiveUpdatePO(),busExamInternalArchive);
        }else {
            BeanUtil.copyProperties(examArchiveUpdatePO.getBusExamInternalArchiveUpdatePO(),busExamInternalArchive1);
            busExamInternalArchive1.setId(snowflakeComponent.getInstance().nextId());
            busExamInternalArchive1.setExamId(busExamArchive.getArchiveId());
        }
        //其他科
        Example example4 = new Example(BusExamOtherArchive.class);
        Example.Criteria criteria4 = example4.createCriteria();
        criteria4.andEqualTo("examId", busExamArchive.getArchiveId());
        BusExamOtherArchive busExamOtherArchive = busExamOtherArchiveMapper.selectOneByExample(example4);
        BusExamOtherArchive busExamOtherArchive1 = new BusExamOtherArchive();
        if(!StringUtils.isEmpty(busExamOtherArchive)){
            BeanUtil.copyProperties(examArchiveUpdatePO.getBusExamOtherArchiveUpdatePO(),busExamOtherArchive);
        }else {
            BeanUtil.copyProperties(examArchiveUpdatePO.getBusExamOtherArchiveUpdatePO(),busExamOtherArchive1);
            busExamOtherArchive1.setId(snowflakeComponent.getInstance().nextId());
            busExamOtherArchive1.setExamId(busExamArchive.getArchiveId());
        }
        //外科
        Example example5 = new Example(BusExamSurgicalArchive.class);
        Example.Criteria criteria5 = example5.createCriteria();
        criteria5.andEqualTo("examId", busExamArchive.getArchiveId());
        BusExamSurgicalArchive busExamSurgicalArchive = busExamSurgicalArchiveMapper.selectOneByExample(example5);
        BusExamSurgicalArchive busExamSurgicalArchive1 = new BusExamSurgicalArchive();
        if(!StringUtils.isEmpty(busExamSurgicalArchive)){
            BeanUtil.copyProperties(examArchiveUpdatePO.getBusExamSurgicalArchiveUpdatePO(),busExamSurgicalArchive);
        }else {
            BeanUtil.copyProperties(examArchiveUpdatePO.getBusExamSurgicalArchiveUpdatePO(),busExamSurgicalArchive1);
            busExamSurgicalArchive1.setId(snowflakeComponent.getInstance().nextId());
            busExamSurgicalArchive1.setExamId(busExamArchive.getArchiveId());
        }

        busExamArchive.setUpdaterId(userInfoToken.getUserId());
        busExamArchive.setUpdateTime(new Date());
        log.info("更新体检登记主表数据,入参busExamArchive:" + busExamArchive);
        busExamArchiveMapper.updateByPrimaryKeySelective(busExamArchive);

        //更新从表数据
        if(!StringUtils.isEmpty(busExamEntArchive)){
            busExamEntArchiveMapper.updateByPrimaryKeySelective(busExamEntArchive);
        }
        if(!StringUtils.isEmpty(busExamEntArchive1)){
            busExamEntArchiveMapper.insertSelective(busExamEntArchive1);
        }

        if(!StringUtils.isEmpty(busExamEyesArchive)){
            busExamEyesArchiveMapper.updateByPrimaryKeySelective(busExamEyesArchive);
        }
        if(!StringUtils.isEmpty(busExamEyesArchive1)){
            busExamEyesArchiveMapper.insertSelective(busExamEyesArchive1);
        }

        if(!StringUtils.isEmpty(busExamInternalArchive)){
            busExamInternalArchiveMapper.updateByPrimaryKeySelective(busExamInternalArchive);
        }
        if(!StringUtils.isEmpty(busExamInternalArchive1)){
            busExamInternalArchiveMapper.insertSelective(busExamInternalArchive1);
        }

        if(!StringUtils.isEmpty(busExamSurgicalArchive)){
            busExamSurgicalArchiveMapper.updateByPrimaryKeySelective(busExamSurgicalArchive);
        }
        if(!StringUtils.isEmpty(busExamSurgicalArchive1)){
            busExamSurgicalArchiveMapper.insertSelective(busExamSurgicalArchive1);
        }

        if(!StringUtils.isEmpty(busExamOtherArchive)){
            busExamOtherArchiveMapper.updateByPrimaryKeySelective(busExamOtherArchive);
        }
        if(!StringUtils.isEmpty(busExamOtherArchive1)){
            busExamOtherArchiveMapper.insertSelective(busExamOtherArchive1);
        }
        return true;
    }

    /**
     * 删除体检档案
     * @author linxiazhu
     * @date 16:26 2021/7/8
     * @param businessNo  老人档案id
     * @param userInfoToken   token
     * @return  boolean
     */
    @Override
    public boolean delete(String businessNo, UserInfoToken userInfoToken) {
        //查询体检档案信息
        Example example = new Example(BusExamArchive.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("businessNo", businessNo);
        criteria.andEqualTo("isDel", 0);
        BusExamArchive busExamArchive = busExamArchiveMapper.selectOneByExample(example);
        if(StringUtils.isEmpty(busExamArchive)){
            throw  ResponseEnum.DATA_NOT_FOUND.newException("该档案不存在或已被删除！");
        }
        busExamArchive.setUpdateTime(new Date());
        busExamArchive.setUpdaterId(userInfoToken.getUserId());
        busExamArchive.setIsDel(1);
        log.info("删除体检档案,入参busExamArchive:" + busExamArchive);
        try {
            busExamArchiveMapper.updateByPrimaryKeySelective(busExamArchive);
        }catch (Exception e){
            log.error("删除体检档案数据失败");
            throw ResponseEnum.FILE_DELETE_FAIL.newException("删除体检档案数据失败");
        }
        //删除从表
        if(!StringUtils.isEmpty(busExamArchive)){
            Example example1 = new Example(BusExamEntArchive.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("examId", busExamArchive.getArchiveId());
            BusExamEntArchive busExamEntArchive = busExamEntArchiveMapper.selectOneByExample(example1);
            if(!StringUtils.isEmpty(busExamEntArchive)){
                busExamEntArchiveMapper.delete(busExamEntArchive);
            }
            Example example2 = new Example(BusExamEyesArchive.class);
            Example.Criteria criteria2 = example2.createCriteria();
            criteria2.andEqualTo("examId", busExamArchive.getArchiveId());
            BusExamEyesArchive busExamEyesArchive = busExamEyesArchiveMapper.selectOneByExample(example2);
            if(!StringUtils.isEmpty(busExamEyesArchive)){
                busExamEyesArchiveMapper.delete(busExamEyesArchive);
            }
            Example example3 = new Example(BusExamInternalArchive.class);
            Example.Criteria criteria3 = example3.createCriteria();
            criteria3.andEqualTo("examId", busExamArchive.getArchiveId());
            BusExamInternalArchive busExamInternalArchive = busExamInternalArchiveMapper.selectOneByExample(example3);
            if(!StringUtils.isEmpty(busExamInternalArchive)){
                busExamInternalArchiveMapper.delete(busExamInternalArchive);
            }
            Example example4 = new Example(BusExamOtherArchive.class);
            Example.Criteria criteria4 = example4.createCriteria();
            criteria4.andEqualTo("examId", busExamArchive.getArchiveId());
            BusExamOtherArchive busExamOtherArchive = busExamOtherArchiveMapper.selectOneByExample(example4);
            if(!StringUtils.isEmpty(busExamOtherArchive)){
                busExamOtherArchiveMapper.delete(busExamOtherArchive);
            }
            Example example5 = new Example(BusExamSurgicalArchive.class);
            Example.Criteria criteria5 = example5.createCriteria();
            criteria5.andEqualTo("examId", busExamArchive.getArchiveId());
            BusExamSurgicalArchive busExamSurgicalArchive = busExamSurgicalArchiveMapper.selectOneByExample(example5);
            if(!StringUtils.isEmpty(busExamSurgicalArchive)){
                busExamSurgicalArchiveMapper.delete(busExamSurgicalArchive);
            }
        }
        return true;
    }
}
