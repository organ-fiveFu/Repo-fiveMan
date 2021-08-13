package com.vblessings.nhs.mapper;

import com.vblessings.nhs.model.entity.business.BusVitalSignRecord;
import com.vblessings.nhs.model.vo.business.BusVitalSignRecordVO;
import tk.mybatis.mapper.common.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright (c) 2017 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * BusVitalSignRecordMapper
 *
 * @author zjy-auto-build
 * @since  2021-08-14 02:09:19
 */
@Repository
public interface BusVitalSignRecordMapper extends Mapper<BusVitalSignRecord> {

    void batchDel(String[] id);

    List<BusVitalSignRecordVO> batchQueryVitalSignRecord(String recordTime);

}
