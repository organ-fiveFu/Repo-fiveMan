package com.vblessings.nhs.mapper;

import com.vblessings.nhs.model.entity.business.BusBloodSugarRecord;
import com.vblessings.nhs.model.po.TimeQueryPO;
import com.vblessings.nhs.model.po.nurse.BloodSugarQueryPO;
import com.vblessings.nhs.model.vo.nurse.BloodSugarQueryVO;
import com.vblessings.nhs.model.vo.nurse.BloodSugarReportQueryVO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Copyright (c) 2017 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * BusBloodSugarRecordMapper
 *
 * @author linxiazhu-auto-build
 * @since  2021-08-02 09:24:02
 */
@Repository
public interface BusBloodSugarRecordMapper extends Mapper<BusBloodSugarRecord> {

    List<BloodSugarReportQueryVO> queryBloodSugarNoToken(TimeQueryPO timeQueryPO);

    List<BloodSugarQueryVO> queryBloodSugar(BloodSugarQueryPO bloodSugarQueryPO);
}
