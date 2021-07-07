package com.nurse.healthy.model.po.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "住院档案-内科更新入参")
public class BusExamInternalArchiveUpdatePO {

    @ApiModelProperty(value = "主键", name = "id")
    private Long id;

    @ApiModelProperty(value = "心率", name = "heartRate")
    private Integer heartRate;

    @ApiModelProperty(value = "血压高", name = "bloodPressureHigh")
    private Integer bloodPressureHigh;

    @ApiModelProperty(value = "血压低", name = "bloodPressureLow")
    private Integer bloodPressureLow;

    @ApiModelProperty(value = "心脏签字", name = "heartSign")
    private String heartSign;

    @ApiModelProperty(value = "营养状况编码", name = "nutritureCode")
    private String nutritureCode;

    @ApiModelProperty(value = "营养状况名称", name = "nutritureName")
    private String nutritureName;

    @ApiModelProperty(value = "心血管编码", name = "cardiovascularCode")
    private String cardiovascularCode;

    @ApiModelProperty(value = "心血管名称", name = "cardiovascularName")
    private String cardiovascularName;

    @ApiModelProperty(value = "神经编码", name = "nerveCode")
    private String nerveCode;

    @ApiModelProperty(value = "神经名称", name = "nerveName")
    private String nerveName;

    @ApiModelProperty(value = "肝编码", name = "liverCode")
    private String liverCode;

    @ApiModelProperty(value = "肝名称", name = "liverName")
    private String liverName;

    @ApiModelProperty(value = "肺编码", name = "lungCode")
    private String lungCode;

    @ApiModelProperty(value = "肺名称", name = "lungName")
    private String lungName;

    @ApiModelProperty(value = "脾编码", name = "spleenCode")
    private String spleenCode;

    @ApiModelProperty(value = "脾名称", name = "spleenName")
    private String spleenName;

    @ApiModelProperty(value = "其他", name = "other")
    private String other;

    @ApiModelProperty(value = "签字", name = "signature")
    private String signature;

    @ApiModelProperty(value = "医生建议", name = "suggestion")
    private String suggestion;
}
