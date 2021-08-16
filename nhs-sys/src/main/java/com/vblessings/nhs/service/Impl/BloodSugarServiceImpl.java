package com.vblessings.nhs.service.Impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.enums.DictTypeEnum;
import com.vblessings.nhs.exception.ResponseEnum;
import com.vblessings.nhs.mapper.BusBloodSugarRecordMapper;
import com.vblessings.nhs.model.entity.business.BusBloodSugarRecord;
import com.vblessings.nhs.model.po.nurse.BloodSugarInsertPO;
import com.vblessings.nhs.model.po.nurse.BloodSugarQueryPO;
import com.vblessings.nhs.model.po.nurse.BloodSugarUpdatePO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.nurse.BloodSugarQueryVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BloodSugarService;
import com.vblessings.nhs.service.SysDictDataService;
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
import java.util.*;
import java.util.stream.Collectors;

@Service("bloodSugarService")
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class BloodSugarServiceImpl implements BloodSugarService {

    @Resource
    private BusBloodSugarRecordMapper busBloodSugarRecordMapper;

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private SysDictDataService sysDictDataService;

    @Override
    public boolean insertBloodSugar(BloodSugarInsertPO bloodSugarInsertPO, UserInfoToken userInfoToken) {
        String s = DateUtil.formatDate(bloodSugarInsertPO.getBloodSugarRecordDate());
        //判断当个日期的病人是否重复
        Example example = new Example(BusBloodSugarRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("businessNo", bloodSugarInsertPO.getBusinessNo());
        criteria.andEqualTo("isDel", 0);
        criteria.andLike("bloodSugarRecordDate", s+"%");
        criteria.andEqualTo("samplingStatus", bloodSugarInsertPO.getSamplingStatus());
        int count = busBloodSugarRecordMapper.selectCountByExample(example);
        if(count > 0){
            throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该日期该病人该采样时间段的血糖记录已存在，无法新增");
        }
        //新增
        BusBloodSugarRecord busBloodSugarRecord = new BusBloodSugarRecord();
        BeanUtils.copyProperties(bloodSugarInsertPO, busBloodSugarRecord);
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(busBloodSugarRecord, userInfoToken, id);
        log.info("新增血糖记录,入参busBloodSugarRecord:{}", JSON.toJSONString(busBloodSugarRecord));
        try {
            busBloodSugarRecordMapper.insertSelective(busBloodSugarRecord);
        }catch (Exception e){
            log.error("新增血糖记录失败");
            throw ResponseEnum.FILE_INSERT_FAIL.newException("新增血糖记录失败");
        }
        return true;
    }

    @Override
    public boolean updateBloodSugar(BloodSugarUpdatePO bloodSugarUpdatePO, UserInfoToken userInfoToken) {
        String s = DateUtil.formatDate(bloodSugarUpdatePO.getBloodSugarRecordDate());
        //判断当个日期的病人是否重复
        Example example = new Example(BusBloodSugarRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("id", bloodSugarUpdatePO.getId());
        criteria.andEqualTo("businessNo", bloodSugarUpdatePO.getBusinessNo());
        criteria.andEqualTo("isDel", 0);
        criteria.andLike("bloodSugarRecordDate", s+"%");
        criteria.andEqualTo("samplingStatus", bloodSugarUpdatePO.getSamplingStatus());
        int count = busBloodSugarRecordMapper.selectCountByExample(example);
        if(count > 0){
            throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该日期该病人该采样时间段的血糖记录已存在，无法更新");
        }
        //更新
        BusBloodSugarRecord busBloodSugarRecord = new BusBloodSugarRecord();
        BeanUtils.copyProperties(bloodSugarUpdatePO, busBloodSugarRecord);
        busBloodSugarRecord.setUpdaterId(userInfoToken.getUserId());
        busBloodSugarRecord.setUpdateTime(new Date());
        log.info("更新血糖记录,入参busBloodSugarRecord:{}", JSON.toJSONString(busBloodSugarRecord));
        try {
            busBloodSugarRecordMapper.updateByPrimaryKeySelective(busBloodSugarRecord);
        }catch (Exception e){
            log.error("更新血糖记录失败");
            throw ResponseEnum.FILE_UPDATE_FAIL.newException("更新血糖记录失败");
        }
        return true;
    }

    @Override
    public PageVO<BloodSugarQueryVO> queryBloodSugar(BloodSugarQueryPO bloodSugarQueryPO) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Page<BloodSugarQueryVO> result = PageHelper.startPage(bloodSugarQueryPO.getPageNum(), bloodSugarQueryPO.getPageSize());
        Example example = new Example(BusBloodSugarRecord.class);
        Example.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(bloodSugarQueryPO.getBusinessNo())){
            criteria.andLike("businessNo", "%" + bloodSugarQueryPO.getBusinessNo() + "%");
        }
        if(!StringUtils.isEmpty(bloodSugarQueryPO.getPatientName())){
            criteria.andLike("patientName", "%" + bloodSugarQueryPO.getPatientName() + "%");
        }
        if(Objects.nonNull(bloodSugarQueryPO.getId())){
            criteria.andEqualTo("id", bloodSugarQueryPO.getId());
        }
        if (!StringUtils.isEmpty(bloodSugarQueryPO.getStartTime()) && !StringUtils.isEmpty(bloodSugarQueryPO.getEndTime())) {
            try {
                Date startTime = simpleDateFormat.parse(bloodSugarQueryPO.getStartTime());
                Date endTime = simpleDateFormat.parse(bloodSugarQueryPO.getEndTime() + " 23:59:59");
                criteria.andBetween("bloodSugarRecordDate", startTime, endTime);
            } catch (Exception e) {
                throw ResponseEnum.ABNORMAL_DATA_VERIFICATION.newException("日期格式错误");
            }
        }
        if(!StringUtils.isEmpty(bloodSugarQueryPO.getSamplingStatus())){
            criteria.andEqualTo("samplingStatus", bloodSugarQueryPO.getSamplingStatus());
        }
        criteria.andEqualTo("isDel", 0);
        List<BusBloodSugarRecord> busBloodSugarRecords = busBloodSugarRecordMapper.selectByExample(example);
        if(CollectionUtil.isEmpty(busBloodSugarRecords)){
            return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), new ArrayList<>());
        }
        List<BloodSugarQueryVO> bloodSugarQueryVOList = new ArrayList<>();
        List<String> samplingStatusList = busBloodSugarRecords.stream().map(BusBloodSugarRecord::getSamplingStatus).collect(Collectors.toList());
        Map<String, String> samplingStatusMap = sysDictDataService.getDictName(DictTypeEnum.BLOOD_SAMPLING_STATUS.getCode(), samplingStatusList);
        busBloodSugarRecords.forEach(busBloodSugarRecord -> {
            BloodSugarQueryVO bloodSugarQueryVO = new BloodSugarQueryVO();
            BeanUtils.copyProperties(busBloodSugarRecord, bloodSugarQueryVO);
            //日期
            String s = DateUtil.formatDate(busBloodSugarRecord.getBloodSugarRecordDate());
            //采样时间
            String samplingTime = null == busBloodSugarRecord.getSamplingTime() ? "" :
                    DateFormatUtils.format(busBloodSugarRecord.getSamplingTime(), "yyyy-MM-dd HH:mm:ss");
            bloodSugarQueryVO.setBloodSugarRecordDate(s);
            bloodSugarQueryVO.setSamplingTime(samplingTime);
            bloodSugarQueryVO.setSamplingStatusName(samplingStatusMap.get(busBloodSugarRecord.getSamplingStatus()));
            bloodSugarQueryVOList.add(bloodSugarQueryVO);
        });
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), bloodSugarQueryVOList);
    }

    @Override
    public boolean deleteBloodSugar(Long id, UserInfoToken userInfoToken) {
        BusBloodSugarRecord busBloodSugarRecord = busBloodSugarRecordMapper.selectByPrimaryKey(id);
        busBloodSugarRecord.setIsDel(1);
        busBloodSugarRecord.setUpdateTime(new Date());
        busBloodSugarRecord.setUpdaterId(userInfoToken.getUserId());
        log.info("删除血糖记录,入参busBloodSugarRecord:" + busBloodSugarRecord);
        try {
            busBloodSugarRecordMapper.updateByPrimaryKeySelective(busBloodSugarRecord);
        }catch (Exception e){
            log.error("删除血糖记录失败");
            throw ResponseEnum.FILE_DELETE_FAIL.newException("删除血糖记录失败");
        }
        return true;
    }
}
