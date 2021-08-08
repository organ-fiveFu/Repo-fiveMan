package com.vblessings.nhs.model.po.nurse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "请假单新增入参")
@Data
public class LeaveApplicationInsertPO {

    @ApiModelProperty(name="businessNo", value = "住院号")
    private String businessNo;

    @ApiModelProperty(name="name", value = "姓名")
    private String name;

    @ApiModelProperty(name="leaveStartTime", value = "请假开始日期")
    private Date leaveStartTime;

    @ApiModelProperty(name="leaveEndTime", value = "请假结束日期")
    private Date leaveEndTime;

    @ApiModelProperty(name="reasonForLeave", value = "请假原因")
    private String reasonForLeave;

    @ApiModelProperty(name="guardian", value = "监护人")
    private String guardian;

    @ApiModelProperty(name="hospitalDirector", value = "医院负责人")
    private String hospitalDirector;
}
