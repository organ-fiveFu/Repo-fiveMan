package com.vblessings.nhs.model.vo.nurse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "请假单查询出参")
@Data
public class LeaveApplicationQueryVO {

    @ApiModelProperty(value = "主键", required = false)
    private Long id;

    @ApiModelProperty(value = "住院号", required = false)
    private String businessNo;

    @ApiModelProperty(value = "姓名", required = false)
    private String name;

    @ApiModelProperty(value = "请假开始日期", required = false)
    private String leaveStartTime;

    @ApiModelProperty(value = "请假结束日期", required = false)
    private String leaveEndTime;

    @ApiModelProperty(value = "请假原因", required = false)
    private String reasonForLeave;

    @ApiModelProperty(value = "监护人", required = false)
    private String guardian;

    @ApiModelProperty(value = "医院负责人", required = false)
    private String hospitalDirector;
}
