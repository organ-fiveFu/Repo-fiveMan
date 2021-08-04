package com.vblessings.nhs.model.po.comprehensive;

import com.vblessings.nhs.model.po.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "行政查房查询入参")
@Data
public class AdministrativeQueryPO extends PageParam {

    @ApiModelProperty(value = "查找人", name = "wardRounds")
    private String wardRounds;

    @ApiModelProperty(value = "开始日期", name = "startTime")
    private String startTime;

    @ApiModelProperty(value = "结束日期", name = "endTime")
    private String endTime;
}
