package com.vblessings.nhs.model.po.comprehensive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "交接班新增入参")
@Data
public class ChangeShiftInsertPO {

    @ApiModelProperty(value = "交班员", name = "handoverOfficer")
    private String handoverOfficer;

    @ApiModelProperty(value = "接班员", name = "successor")
    private String successor;

    @ApiModelProperty(value = "交班开始时间", name = "shiftHandoverStartTime")
    private Date shiftHandoverStartTime;

    @ApiModelProperty(value = "交班结束时间", name = "shiftHandoverEndTime")
    private Date shiftHandoverEndTime;

    @ApiModelProperty(value = "交班事宜", name = "shiftHandover")
    private String shiftHandover;

    @ApiModelProperty(value = "备注", name = "remark")
    private String remark;

}
