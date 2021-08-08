package com.vblessings.nhs.model.po.nurse;

import com.vblessings.nhs.model.po.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "请假单查询入参")
@Data
public class LeaveApplicationQueryPO extends PageParam {

    @ApiModelProperty(value = "病人姓名", name = "name")
    private String name;

    @ApiModelProperty(value = "住院号", name = "businessNo")
    private String businessNo;

    @ApiModelProperty(value = "开始日期", name = "startTime")
    private String startTime;

    @ApiModelProperty(value = "结束日期", name = "endTime")
    private String endTime;
}
