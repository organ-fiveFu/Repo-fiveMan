package com.vblessings.nhs.service.Impl;

import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.exception.ResponseEnum;
import com.vblessings.nhs.mapper.BusAdmissionAssessmentMapper;
import com.vblessings.nhs.model.entity.assessment.BusAdmissionAssessment;
import com.vblessings.nhs.model.po.trialassessment.AdmissionAssessmentInsertPO;
import com.vblessings.nhs.model.po.trialassessment.AdmissionAssessmentUpdatePO;
import com.vblessings.nhs.model.vo.trialassessment.AdmissionAssessmentQueryVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.AdmissionAssessmentService;
import com.vblessings.nhs.util.OperateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AdmissionAssessmentServiceImpl implements AdmissionAssessmentService {

    @Resource
    private BusAdmissionAssessmentMapper busAdmissionAssessmentMapper;

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Override
    public boolean insertAdmissionAssessment(AdmissionAssessmentInsertPO admissionAssessmentInsertPO, UserInfoToken userInfoToken) {
        //判断住院号是否重复
        Example example = new Example(BusAdmissionAssessment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("businessNo", admissionAssessmentInsertPO.getBusinessNo());
        criteria.andEqualTo("isDel", 0);
        int count = busAdmissionAssessmentMapper.selectCountByExample(example);
        if(count > 0){
            throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该病人已新增过入住评估，无法新增");
        }
        //新增
        BusAdmissionAssessment busAdmissionAssessment = new BusAdmissionAssessment();
        BeanUtils.copyProperties(admissionAssessmentInsertPO, busAdmissionAssessment);
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(busAdmissionAssessment, userInfoToken, id);
        log.info("新增入院评估信息,入参busAdmissionAssessment:" + busAdmissionAssessment);
        try {
            busAdmissionAssessmentMapper.insertSelective(busAdmissionAssessment);
        }catch (Exception e){
            log.error("新增入院评估信息失败");
            throw ResponseEnum.FILE_INSERT_FAIL.newException("新增入院评估信息失败");
        }
        return true;
    }

    @Override
    public boolean updateAdmissionAssessment(AdmissionAssessmentUpdatePO admissionAssessmentUpdatePO, UserInfoToken userInfoToken) {
        //判断住院号是否重复
        Example example = new Example(BusAdmissionAssessment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("businessNo", admissionAssessmentUpdatePO.getBusinessNo());
        criteria.andEqualTo("isDel", 0);
        criteria.andNotEqualTo("id", admissionAssessmentUpdatePO.getId());
        int count = busAdmissionAssessmentMapper.selectCountByExample(example);
        if(count > 0){
            throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该病人已新增过入住评估，无法更新");
        }
        //更新
        BusAdmissionAssessment busAdmissionAssessment = new BusAdmissionAssessment();
        BeanUtils.copyProperties(admissionAssessmentUpdatePO, busAdmissionAssessment);
        busAdmissionAssessment.setUpdaterId(userInfoToken.getUserId());
        busAdmissionAssessment.setUpdateTime(new Date());
        log.info("更新入院评估信息,入参busAdmissionAssessment:" + busAdmissionAssessment);
        try {
            busAdmissionAssessmentMapper.updateByPrimaryKeySelective(busAdmissionAssessment);
        }catch (Exception e){
            log.error("更新入院评估信息失败");
            throw ResponseEnum.FILE_UPDATE_FAIL.newException("更新入院评估信息失败");
        }
        return true;
    }

    @Override
    public AdmissionAssessmentQueryVO queryAdmissionAssessment(String businessNo) {
        Example example = new Example(BusAdmissionAssessment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("businessNo", businessNo);
        criteria.andEqualTo("isDel", 0);
        BusAdmissionAssessment busAdmissionAssessment = busAdmissionAssessmentMapper.selectOneByExample(example);
        if(StringUtils.isEmpty(busAdmissionAssessment)){
            return null;
        }
        AdmissionAssessmentQueryVO admissionAssessmentQueryVO = new AdmissionAssessmentQueryVO();
        BeanUtils.copyProperties(busAdmissionAssessment, admissionAssessmentQueryVO);
        String admissionTime = null == busAdmissionAssessment.getAdmissionTime() ? "" :
                DateFormatUtils.format(busAdmissionAssessment.getAdmissionTime(), "yyyy-MM-dd HH:mm:ss");
        admissionAssessmentQueryVO.setAdmissionTime(admissionTime);
        return admissionAssessmentQueryVO;
    }

    @Override
    public boolean deleteAdmissionAssessment(String businessNo, UserInfoToken userInfoToken) {
        //查询入院评估信息
        Example example = new Example(BusAdmissionAssessment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("businessNo", businessNo);
        criteria.andEqualTo("isDel", 0);
        BusAdmissionAssessment busAdmissionAssessment = busAdmissionAssessmentMapper.selectOneByExample(example);
        if(StringUtils.isEmpty(busAdmissionAssessment)){
            throw  ResponseEnum.DATA_NOT_FOUND.newException("该入院评估不存在或已被删除！");
        }
        busAdmissionAssessment.setUpdateTime(new Date());
        busAdmissionAssessment.setUpdaterId(userInfoToken.getUserId());
        busAdmissionAssessment.setIsDel(1);
        log.info("删除入院评估信息,入参busAdmissionAssessment:" + busAdmissionAssessment);
        try {
            busAdmissionAssessmentMapper.updateByPrimaryKeySelective(busAdmissionAssessment);
        }catch (Exception e){
            log.error("删除入院评估信息失败");
            throw ResponseEnum.FILE_DELETE_FAIL.newException("删除入院评估信息失败");
        }
        return true;
    }
}
