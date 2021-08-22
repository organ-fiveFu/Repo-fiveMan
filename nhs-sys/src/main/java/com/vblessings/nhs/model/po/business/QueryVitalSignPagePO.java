package com.vblessings.nhs.model.po.business;

import com.vblessings.nhs.model.po.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "分页查询三测单入参")
public class QueryVitalSignPagePO extends PageParam {

    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @ApiModelProperty(value = "患者姓名", required = true)
    private String name;

    @ApiModelProperty(value = "住院号", required = true)
    private String businessNo;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;
}
