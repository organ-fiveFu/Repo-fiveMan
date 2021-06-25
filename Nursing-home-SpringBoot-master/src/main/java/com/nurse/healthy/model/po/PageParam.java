package com.nurse.healthy.model.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * Copyright (c) 2017 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * PageParam
 *
 * @author gouhuajiao
 * @since  18:07 2020/8/17
 */
@Data
public class PageParam {

    @ApiModelProperty("查询页")
    @Range(
            min = 1L,
            max = 1000L,
            message = "查询页超过范围限制: [1--1000]"
    )
    private Integer pageNum = 1;

    // 无分页参数默认查询所有数据
    @ApiModelProperty("每页数量")
    @Range(
            min = 1L,
            max = 999999999L,
            message = "每页数量超过范围限制: [1--999999999]"
    )
    private Integer pageSize = 999999999;
}
