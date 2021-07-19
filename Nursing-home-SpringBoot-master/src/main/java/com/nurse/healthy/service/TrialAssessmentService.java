package com.nurse.healthy.service;

import com.nurse.healthy.model.po.trialassessment.TrialAssessmentInsertPO;
import com.nurse.healthy.model.po.trialassessment.TrialAssessmentUpdatePO;
import com.nurse.healthy.model.vo.trialassessment.TrialAssessmentQueryVO;
import com.nurse.healthy.result.UserInfoToken;

public interface TrialAssessmentService {
    /**
     * 新增试用期评估信息
     * @author linxiazhu
     * @date 15:29 2021/7/19
     * @param trialAssessmentInsertPO  入参
     * @param userInfoToken   token
     * @return  boolean
     */
    boolean insertTrialAssessment(TrialAssessmentInsertPO trialAssessmentInsertPO, UserInfoToken userInfoToken);

    /**
     * 更新试用期评估信息
     * @author linxiazhu
     * @date 15:58 2021/7/19
     * @param trialAssessmentUpdatePO  入参
     * @param userInfoToken   token
     * @return  boolean
     */
    boolean updateTrialAssessment(TrialAssessmentUpdatePO trialAssessmentUpdatePO, UserInfoToken userInfoToken);

    /**
     * 查询试用期评估信息
     * @author linxiazhu
     * @date 16:58 2021/7/19
     * @param archiveId   老人档案id
     * @return  com.nurse.healthy.model.vo.trialassessment.TrialAssessmentQueryVO
     */
    TrialAssessmentQueryVO queryTrialAssessment(String archiveId);

    /**
     * 删除试用期评估信息
     * @author linxiazhu
     * @date 17:16 2021/7/19
     * @param archiveId  老人档案id
     * @param userInfoToken   token
     * @return  boolean
     */
    boolean deleteTrialAssessment(String archiveId, UserInfoToken userInfoToken);
}
