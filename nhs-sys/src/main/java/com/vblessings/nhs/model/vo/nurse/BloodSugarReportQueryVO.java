package com.vblessings.nhs.model.vo.nurse;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "血糖记录报表查询出参")
public class BloodSugarReportQueryVO {

    @ApiModelProperty(value = "住院号")
    private String businessNo;

    @ApiModelProperty(value = "病人姓名")
    private String patientName;

    @ExcelProperty(value = "采样日期")
    private String samplingTime;

    @ExcelProperty(value = "采样状态")
    private String samplingStatus;

    @ExcelProperty(value = "采样状态名称")
    private String samplingStatusName;

    @ExcelProperty(value = "血糖值")
    private String bloodGlucoseValue;

    @ApiModelProperty(value = "楼宇名称")
    private String buildingName;

    @ApiModelProperty(value = "楼层名称")
    private String floorName;

    @ApiModelProperty(value = "房间名称")
    private String roomName;

    @ApiModelProperty(value = "床位名称")
    private String bedName;

    @ApiModelProperty(value = "拼接床位名称")
    private String spliceBedName;
}
