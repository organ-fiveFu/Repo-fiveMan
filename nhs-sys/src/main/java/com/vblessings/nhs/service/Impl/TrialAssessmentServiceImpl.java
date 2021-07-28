package com.vblessings.nhs.service.Impl;

import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.exception.ResponseEnum;
import com.vblessings.nhs.mapper.BusTrialStayAssessmentMapper;
import com.vblessings.nhs.model.entity.assessment.BusTrialStayAssessment;
import com.vblessings.nhs.model.po.trialassessment.TrialAssessmentInsertPO;
import com.vblessings.nhs.model.po.trialassessment.TrialAssessmentUpdatePO;
import com.vblessings.nhs.model.vo.trialassessment.TrialAssessmentQueryVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.TrialAssessmentService;
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
public class TrialAssessmentServiceImpl implements TrialAssessmentService {

    @Resource
    private BusTrialStayAssessmentMapper busTrialStayAssessmentMapper;

    @Resource
    private SnowflakeComponent snowflakeComponent;

    /**
     * 新增试用期评估信息
     * @author linxiazhu
     * @date 15:30 2021/7/19
     * @param trialAssessmentInsertPO  入参
     * @param userInfoToken   token
     * @return  boolean
     */
    @Override
    public boolean insertTrialAssessment(TrialAssessmentInsertPO trialAssessmentInsertPO, UserInfoToken userInfoToken) {
        //判断 住院号是否重复
        Example example = new Example(BusTrialStayAssessment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("businessNo", trialAssessmentInsertPO.getBusinessNo());
        criteria.andEqualTo("isDel", 0);
        int count =  busTrialStayAssessmentMapper.selectCountByExample(example);
        if(count > 0){
            throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该病人已经新增过试用期评估信息，无法新增");
        }
        //新增
        BusTrialStayAssessment busTrialStayAssessment = new BusTrialStayAssessment();
        BeanUtils.copyProperties(trialAssessmentInsertPO, busTrialStayAssessment);
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(busTrialStayAssessment, userInfoToken, id);
        log.info("新增试用期评估信息,入参busTrialStayAssessment:" + busTrialStayAssessment);
        try {
            busTrialStayAssessmentMapper.insertSelective(busTrialStayAssessment);
        }catch (Exception e){
            log.error("新增试用期评估信息失败");
            throw ResponseEnum.FILE_INSERT_FAIL.newException("新增试用期评估信息失败");
        }
        return true;
    }

    /**
     * 更新试用期评估信息
     * @author linxiazhu
     * @date 15:58 2021/7/19
     * @param trialAssessmentUpdatePO  入参
     * @param userInfoToken   token
     * @return  boolean
     */
    @Override
    public boolean updateTrialAssessment(TrialAssessmentUpdatePO trialAssessmentUpdatePO, UserInfoToken userInfoToken) {
        //判断 住院号是否重复
        Example example = new Example(BusTrialStayAssessment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("businessNo", trialAssessmentUpdatePO.getBusinessNo());
        criteria.andEqualTo("isDel", 0);
        criteria.andNotEqualTo("id", trialAssessmentUpdatePO.getId());
        int count =  busTrialStayAssessmentMapper.selectCountByExample(example);
        if(count > 0){
            throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该病人已经新增过试用期评估信息，无法新增");
        }
        //更新
        BusTrialStayAssessment busTrialStayAssessment = new BusTrialStayAssessment();
        BeanUtils.copyProperties(trialAssessmentUpdatePO, busTrialStayAssessment);
        busTrialStayAssessment.setUpdaterId(userInfoToken.getUserId());
        busTrialStayAssessment.setUpdateTime(new Date());
        log.info("更新试用期评估信息,入参busTrialStayAssessment:" + busTrialStayAssessment);
        try {
            busTrialStayAssessmentMapper.updateByPrimaryKeySelective(busTrialStayAssessment);
        }catch (Exception e){
            log.error("更新试用期评估信息失败");
            throw ResponseEnum.FILE_INSERT_FAIL.newException("更新试用期评估信息失败");
        }
        return true;
    }

    /**
     * 查询试用期评估信息
     * @author linxiazhu
     * @date 16:58 2021/7/19
     * @param archiveId   老人档案id
     * @return  com.nurse.healthy.model.vo.trialassessment.TrialAssessmentQueryVO
     */
    @Override
    public TrialAssessmentQueryVO queryTrialAssessment(String archiveId) {
        Example example = new Example(BusTrialStayAssessment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("archiveId", archiveId);
        criteria.andEqualTo("isDel", 0);
        BusTrialStayAssessment busTrialStayAssessment = busTrialStayAssessmentMapper.selectOneByExample(example);
        if(StringUtils.isEmpty(busTrialStayAssessment)){
            return null;
        }
        TrialAssessmentQueryVO trialAssessmentQueryVO = new TrialAssessmentQueryVO();
        BeanUtils.copyProperties(busTrialStayAssessment, trialAssessmentQueryVO);
        //入院时间
        String admissionTime = null == busTrialStayAssessment.getAdmissionTime() ? "" :
                DateFormatUtils.format(busTrialStayAssessment.getAdmissionTime(), "yyyy-MM-dd HH:mm:ss");
        //生活能力评估日期
        String lifeEvaluateTime = null == busTrialStayAssessment.getLifeEvaluateTime() ? "" :
                DateFormatUtils.format(busTrialStayAssessment.getLifeEvaluateTime(), "yyyy-MM-dd HH:mm:ss");
        //认知能力评估日期
        String cognitiveEvaluateTime = null == busTrialStayAssessment.getCognitiveEvaluateTime() ? "" :
                DateFormatUtils.format(busTrialStayAssessment.getCognitiveEvaluateTime(), "yyyy-MM-dd HH:mm:ss");
        //情绪行为评估日期
        String emotionBehaviorEvaluateTime = null == busTrialStayAssessment.getEmotionBehaviorEvaluateTime() ? "" :
                DateFormatUtils.format(busTrialStayAssessment.getEmotionBehaviorEvaluateTime(), "yyyy-MM-dd HH:mm:ss");
        //健康状况评估时间
        String healthEvaluateTime = null == busTrialStayAssessment.getHealthEvaluateTime() ? "" :
                DateFormatUtils.format(busTrialStayAssessment.getHealthEvaluateTime(), "yyyy-MM-dd HH:mm:ss");
        //特殊需求日期
        String specialRequirementTime = null == busTrialStayAssessment.getSpecialRequirementTime() ? "" :
                DateFormatUtils.format(busTrialStayAssessment.getSpecialRequirementTime(), "yyyy-MM-dd HH:mm:ss");
        //评估小组签名日期
        String teamSignTime = null == busTrialStayAssessment.getTeamSignTime() ? "" :
                DateFormatUtils.format(busTrialStayAssessment.getTeamSignTime(), "yyyy-MM-dd HH:mm:ss");
        //院长签名时间
        String deanSignTime = null == busTrialStayAssessment.getDeanSignTime() ? "" :
                DateFormatUtils.format(busTrialStayAssessment.getDeanSignTime(), "yyyy-MM-dd HH:mm:ss");
        //家属和老人签字时间
        String patientSignTime = null == busTrialStayAssessment.getPatientSignTime() ? "" :
                DateFormatUtils.format(busTrialStayAssessment.getPatientSignTime(), "yyyy-MM-dd HH:mm:ss");
        trialAssessmentQueryVO.setAdmissionTime(admissionTime);
        trialAssessmentQueryVO.setLifeEvaluateTime(lifeEvaluateTime);
        trialAssessmentQueryVO.setCognitiveEvaluateTime(cognitiveEvaluateTime);
        trialAssessmentQueryVO.setEmotionBehaviorEvaluateTime(emotionBehaviorEvaluateTime);
        trialAssessmentQueryVO.setHealthEvaluateTime(healthEvaluateTime);
        trialAssessmentQueryVO.setSpecialRequirementTime(specialRequirementTime);
        trialAssessmentQueryVO.setTeamSignTime(teamSignTime);
        trialAssessmentQueryVO.setDeanSignTime(deanSignTime);
        trialAssessmentQueryVO.setPatientSignTime(patientSignTime);
        return trialAssessmentQueryVO;
    }

    /**
     * 删除试用期评估信息
     * @author linxiazhu
     * @date 17:16 2021/7/19
     * @param archiveId  老人档案id
     * @param userInfoToken   token
     * @return  boolean
     */
    @Override
    public boolean deleteTrialAssessment(String archiveId, UserInfoToken userInfoToken) {
        //查询体检档案信息
        Example example = new Example(BusTrialStayAssessment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("archiveId", archiveId);
        criteria.andEqualTo("isDel", 0);
        BusTrialStayAssessment busTrialStayAssessment = busTrialStayAssessmentMapper.selectOneByExample(example);
        if(StringUtils.isEmpty(busTrialStayAssessment)){
            throw  ResponseEnum.DATA_NOT_FOUND.newException("该试用期评估不存在或已被删除！");
        }
        busTrialStayAssessment.setUpdateTime(new Date());
        busTrialStayAssessment.setUpdaterId(userInfoToken.getUserId());
        busTrialStayAssessment.setIsDel(1);
        log.info("删除试用期评估信息,入参busTrialStayAssessment:" + busTrialStayAssessment);
        try {
            busTrialStayAssessmentMapper.updateByPrimaryKeySelective(busTrialStayAssessment);
        }catch (Exception e){
            log.error("删除试用期评估信息失败");
            throw ResponseEnum.FILE_INSERT_FAIL.newException("删除试用期评估信息失败");
        }
        return true;
    }
}
