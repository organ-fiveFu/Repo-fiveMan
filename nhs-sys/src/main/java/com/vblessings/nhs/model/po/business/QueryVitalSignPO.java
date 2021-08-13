package com.vblessings.nhs.model.po.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "查询三测单入参")
public class QueryVitalSignPO {

    @NotBlank(message = "住院号不能为空")
    @ApiModelProperty(value = "住院号", required = true)
    private String businessNo;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;
}
