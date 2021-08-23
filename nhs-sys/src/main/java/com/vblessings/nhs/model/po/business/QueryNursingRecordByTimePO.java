package com.vblessings.nhs.model.po.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "根据时间点查询护理记录入参")
public class QueryNursingRecordByTimePO {

    @ApiModelProperty(value = "住院号")
    private String businessNo;

    @ApiModelProperty(value = "记录时间")
    private String recordTime;

    @ApiModelProperty(value = "时间点")
    private String timePoint;
}
