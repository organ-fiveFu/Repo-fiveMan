package com.vblessings.nhs.mapper;

import com.vblessings.nhs.model.entity.business.BusNursingRecord;
import tk.mybatis.mapper.common.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Copyright (c) 2017 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * BusNursingRecordMapper
 *
 * @author zjy-auto-build
 * @since  2021-08-13 00:53:15
 */
@Repository
public interface BusNursingRecordMapper extends Mapper<BusNursingRecord> {

    void batchDel(String[] id);
}
