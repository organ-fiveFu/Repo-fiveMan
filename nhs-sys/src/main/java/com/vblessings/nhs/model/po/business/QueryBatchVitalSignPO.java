package com.vblessings.nhs.model.po.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "批量查询三测单入参")
public class QueryBatchVitalSignPO {

    @NotBlank(message = "记录时间不能为空")
    @ApiModelProperty(value = "记录时间", required = true)
    private String recordTime;

    @NotBlank(message = "记录时间点不能为空")
    @ApiModelProperty(value = "记录时间点", required = true)
    private String timePoint;
}
