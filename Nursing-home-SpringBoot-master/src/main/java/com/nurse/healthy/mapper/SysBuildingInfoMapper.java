package com.nurse.healthy.mapper;

import com.nurse.healthy.model.entity.bed.SysBuildingInfo;
import com.nurse.healthy.model.vo.bed.BedTreeVO;
import tk.mybatis.mapper.common.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright (c) 2017 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * SysBuildingInfoMapper
 *
 * @author linxiazhu-auto-build
 * @since  2021-06-24 17:18:13
 */
@Repository
public interface SysBuildingInfoMapper extends Mapper<SysBuildingInfo> {

    /**
     * 床位tree
     * @author linxiazhu
     * @date 13:32 2021/6/29
     * @return  java.util.List<com.nurse.healthy.model.vo.bed.BedTreeVO>
     */
    List<BedTreeVO> queryBedTree();
}
