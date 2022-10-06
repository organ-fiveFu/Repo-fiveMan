package com.vblessings.nhs.mapper;

import com.vblessings.nhs.model.entity.business.BusRiskNotification;
import com.vblessings.nhs.model.po.nurse.RiskNotificationQueryPO;
import com.vblessings.nhs.model.vo.nurse.RiskNotificationQueryVO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Copyright (c) 2017 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * BusRiskNotificationMapper
 *
 * @author linxiazhu-auto-build
 * @since  2021-08-07 15:18:44
 */
@Repository
public interface BusRiskNotificationMapper extends Mapper<BusRiskNotification> {

    List<RiskNotificationQueryVO> queryRiskNotification(RiskNotificationQueryPO riskNotificationQueryPO);
}
