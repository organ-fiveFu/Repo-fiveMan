package com.vblessings.nhs.mapper;

import com.vblessings.nhs.model.entity.business.BusNursingRecord;
import com.vblessings.nhs.model.vo.business.BusNursingRecordQueryVO;
import org.springframework.data.repository.query.Param;
import tk.mybatis.mapper.common.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    List<BusNursingRecordQueryVO> batchQueryNursingRecord(@Param("recordTime") String recordTime,
                                                          @Param("timePoint") String timePoint);
}
