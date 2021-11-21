package com.vblessings.nhs.mapper;

import com.vblessings.nhs.model.entity.bed.SysBedInfo;
import com.vblessings.nhs.model.po.bed.SysBedInfoAllQueryPO;
import com.vblessings.nhs.model.vo.bed.SysBedInfoAllQueryVO;
import com.vblessings.nhs.model.vo.page.PageDetailQueryVO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Copyright (c) 2017 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * SysBedInfoMapper
 *
 * @author linxiazhu-auto-build
 * @since  2021-06-24 17:18:13
 */
@Repository
public interface SysBedInfoMapper extends Mapper<SysBedInfo> {

    List<PageDetailQueryVO> queryPatient(String buildingCode, List<String> floorCodeList, List<String> roomCodeList);

    List<SysBedInfoAllQueryVO> querySysBedInfoGetList(SysBedInfoAllQueryPO sysBedInfoAllQueryPO);
}
