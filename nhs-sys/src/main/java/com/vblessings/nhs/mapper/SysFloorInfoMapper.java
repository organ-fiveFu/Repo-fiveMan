package com.vblessings.nhs.mapper;

import com.vblessings.nhs.model.entity.bed.SysFloorInfo;
import com.vblessings.nhs.model.vo.bed.SysFloorInfoQueryVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright (c) 2017 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * SysFloorInfoMapper
 *
 * @author linxiazhu-auto-build
 * @since  2021-06-24 17:18:13
 */
@Repository
public interface SysFloorInfoMapper extends Mapper<SysFloorInfo> {

    List<SysFloorInfoQueryVO> querySysFloorInfoGetList(@Param(value = "buildingCode") String buildingCode);
}
