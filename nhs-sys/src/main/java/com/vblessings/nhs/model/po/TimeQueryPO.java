package com.vblessings.nhs.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "报表时间查询入参")
@Data
public class TimeQueryPO {

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "楼宇code")
    private String buildingCode;

    @ApiModelProperty(value = "楼层code")
    private String floorCode;

    @ApiModelProperty(value = "住院号")
    private String businessNo;
}
