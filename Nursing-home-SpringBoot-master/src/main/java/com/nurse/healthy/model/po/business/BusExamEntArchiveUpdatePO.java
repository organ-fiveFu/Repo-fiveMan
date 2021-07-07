package com.nurse.healthy.model.po.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "住院档案-五官科更新入参")
public class BusExamEntArchiveUpdatePO {

    @ApiModelProperty(value = "主键", name = "id")
    private Long id;

    @ApiModelProperty(value = "听力左", name = "hearingLeft")
    private BigDecimal hearingLeft;

    @ApiModelProperty(value = "听力右", name = "hearingRight")
    private BigDecimal hearingRight;

    @ApiModelProperty(value = "听力签字", name = "hearingSign")
    private String hearingSign;

    @ApiModelProperty(value = "耳疾编码", name = "earCode")
    private String earCode;

    @ApiModelProperty(value = "耳疾名称", name = "earName")
    private String earName;

    @ApiModelProperty(value = "嗅觉编码", name = "smellCode")
    private String smellCode;

    @ApiModelProperty(value = "嗅觉名称", name = "smellName")
    private String smellName;

    @ApiModelProperty(value = "嗅觉签字", name = "smellSign")
    private String smellSign;

    @ApiModelProperty(value = "口吃编码", name = "stutteringCode")
    private String stutteringCode;

    @ApiModelProperty(value = "口吃名称", name = "stutteringName")
    private String stutteringName;

    @ApiModelProperty(value = "面部编码", name = "faceCode")
    private String faceCode;

    @ApiModelProperty(value = "面部名称", name = "faceName")
    private String faceName;

    @ApiModelProperty(value = "其他", name = "other")
    private String other;

    @ApiModelProperty(value = "签字", name = "signature")
    private String signature;

    @ApiModelProperty(value = "医生建议", name = "suggestion")
    private String suggestion;
}
