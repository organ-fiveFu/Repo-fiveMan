package com.vblessings.nhs.model.po.nurse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "血糖记录更新入参")
@Data
public class BloodSugarUpdatePO {

    @ApiModelProperty(value = "id", name = "id")
    private Long id;

    @ApiModelProperty(value = "住院号", name = "businessNo")
    private String businessNo;

    @ApiModelProperty(value = "病人姓名", name = "patientName")
    private String patientName;

    @ApiModelProperty(value = "床位名称", name = "bedName")
    private String bedName;

    @ApiModelProperty(value = "医院诊断", name = "hospitalDiagnosis")
    private String hospitalDiagnosis;

    @ApiModelProperty(value = "日期", name = "bloodSugarRecordDate")
    private Date bloodSugarRecordDate;

    @ApiModelProperty(value = "采样状态", name = "samplingStatus")
    private String samplingStatus;

    @ApiModelProperty(value = "采样时间", name = "samplingTime")
    private Date samplingTime;

    @ApiModelProperty(value = "采样签名", name = "samplingSignature")
    private String samplingSignature;

    @ApiModelProperty(value = "血糖值", name = "bloodGlucoseValue")
    private String bloodGlucoseValue;
}
