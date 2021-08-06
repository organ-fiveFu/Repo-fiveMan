package com.vblessings.nhs.model.vo.comprehensive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "交接班查询入参")
@Data
public class ChangeShiftQueryVO {

    @ApiModelProperty(value = "id", required = false)
    private Long id;

    @ApiModelProperty(value = "交班员", required = false)
    private String handoverOfficer;

    @ApiModelProperty(value = "接班员", required = false)
    private String successor;

    @ApiModelProperty(value = "提交时间", required = false)
    private String submissionTime;

    @ApiModelProperty(value = "交班开始时间", required = false)
    private String shiftHandoverStartTime;

    @ApiModelProperty(value = "交班结束时间", required = false)
    private String shiftHandoverEndTime;

    @ApiModelProperty(value = "交班事宜", required = false)
    private String shiftHandover;

    @ApiModelProperty(value = "备注", required = false)
    private String remark;
}
