package com.vblessings.nhs.service;

import com.vblessings.nhs.model.po.nurse.RiskNotificationInsertPO;
import com.vblessings.nhs.model.po.nurse.RiskNotificationQueryPO;
import com.vblessings.nhs.model.po.nurse.RiskNotificationUpdatePO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.nurse.RiskNotificationQueryVO;
import com.vblessings.nhs.result.UserInfoToken;

public interface RiskNotificationService {
    boolean insertRiskNotification(RiskNotificationInsertPO riskNotificationInsertPO, UserInfoToken userInfoToken);

    boolean updateRiskNotification(RiskNotificationUpdatePO riskNotificationUpdatePO, UserInfoToken userInfoToken);

    RiskNotificationQueryVO queryRiskNotificationByBusinessNo(String businessNo);

    PageVO<RiskNotificationQueryVO> queryRiskNotification(RiskNotificationQueryPO riskNotificationQueryPO);
}
