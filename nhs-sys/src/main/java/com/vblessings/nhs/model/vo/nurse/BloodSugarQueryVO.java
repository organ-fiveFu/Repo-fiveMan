package com.vblessings.nhs.model.vo.nurse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "血糖记录查询出参")
public class BloodSugarQueryVO {

    @ApiModelProperty(value = "id", required = false)
    private Long id;

    @ApiModelProperty(value = "住院号", required = false)
    private String businessNo;

    @ApiModelProperty(value = "病人姓名", required = false)
    private String patientName;

    @ApiModelProperty(value = "床位名称", required = false)
    private String bedName;

    @ApiModelProperty(value = "医院诊断", required = false)
    private String hospitalDiagnosis;

    @ApiModelProperty(value = "日期", required = false)
    private String bloodSugarRecordDate;

    @ApiModelProperty(value = "采样状态", required = false)
    private String samplingStatus;

    @ApiModelProperty(value = "采样状态名称", required = false)
    private String samplingStatusName;

    @ApiModelProperty(value = "采样时间", required = false)
    private String samplingTime;

    @ApiModelProperty(value = "采样签名", required = false)
    private String samplingSignature;

    @ApiModelProperty(value = "血糖值", required = false)
    private String bloodGlucoseValue;

    @ApiModelProperty(value = "楼宇名称")
    private String buildingName;

    @ApiModelProperty(value = "楼层名称")
    private String floorName;

    @ApiModelProperty(value = "房间名称")
    private String roomName;
}
