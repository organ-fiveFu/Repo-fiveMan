package com.vblessings.nhs.model.po.nurse;

import com.vblessings.nhs.model.po.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "血糖记录查询入参")
@Data
public class BloodSugarQueryPO extends PageParam {
    @ApiModelProperty(value = "住院号", name = "businessNo")
    private String businessNo;

    @ApiModelProperty(value = "病人姓名", name = "patientName")
    private String patientName;

    @ApiModelProperty(value = "开始日期", name = "startTime")
    private String startTime;

    @ApiModelProperty(value = "结束日期", name = "endTime")
    private String endTime;

    @ApiModelProperty(value = "采样状态", name = "samplingStatus")
    private String samplingStatus;
}
