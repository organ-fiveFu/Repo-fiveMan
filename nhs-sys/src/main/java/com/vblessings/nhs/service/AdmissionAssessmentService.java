package com.vblessings.nhs.service;

import com.vblessings.nhs.model.po.trialassessment.AdmissionAssessmentInsertPO;
import com.vblessings.nhs.model.po.trialassessment.AdmissionAssessmentUpdatePO;
import com.vblessings.nhs.model.vo.trialassessment.AdmissionAssessmentQueryVO;
import com.vblessings.nhs.result.UserInfoToken;

public interface AdmissionAssessmentService {
    boolean insertAdmissionAssessment(AdmissionAssessmentInsertPO admissionAssessmentInsertPO, UserInfoToken userInfoToken);

    boolean updateAdmissionAssessment(AdmissionAssessmentUpdatePO admissionAssessmentUpdatePO, UserInfoToken userInfoToken);

    AdmissionAssessmentQueryVO queryAdmissionAssessment(String businessNo);

    boolean deleteAdmissionAssessment(String businessNo, UserInfoToken userInfoToken);
}
