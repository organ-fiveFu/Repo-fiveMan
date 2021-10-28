package com.vblessings.nhs.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
public class QuerySummaryVO {

    @ApiModelProperty(name = "originalNum",value = "原有人数")
    private Integer originalNum;

    @ApiModelProperty(name = "inHospitalNum",value = "入院人数")
    private Integer inHospitalNum;

    @ApiModelProperty(name = "outHospitalNum",value = "出院人数")
    private Integer outHospitalNum;

    @ApiModelProperty(name = "stayHospitalNum",value = "留院人数")
    private Integer stayHospitalNum;

    @ApiModelProperty(name = "takeUpBed",value = "实际占用床位数")
    private Integer takeUpBed;

    @ApiModelProperty(name = "disabilityNum",value = "完全失能老人数")
    private Integer disabilityNum;

    @ApiModelProperty(name = "partialDisability",value = "部分失能老人数")
    private Integer partialDisability;

    @ApiModelProperty(name = "provideForOneself",value = "自理老人数")
    private Integer provideForOneself;

    @ApiModelProperty(name = "takeMedicalNum",value = "带配药人数")
    private Integer takeMedicalNum;


}
