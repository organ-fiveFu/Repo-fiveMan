package com.vblessings.nhs.model.po.comprehensive;

import com.vblessings.nhs.model.po.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "交接班查询入参")
@Data
public class ChangeShiftQueryPO extends PageParam {

    @ApiModelProperty(value = "交班员", name = "handoverOfficer")
    private String handoverOfficer;

    @ApiModelProperty(value = "开始日期", name = "startTime")
    private String startTime;

    @ApiModelProperty(value = "结束日期", name = "endTime")
    private String endTime;
}
