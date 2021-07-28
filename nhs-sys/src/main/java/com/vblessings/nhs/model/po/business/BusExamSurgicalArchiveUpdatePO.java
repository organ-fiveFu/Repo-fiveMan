package com.vblessings.nhs.model.po.business;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "住院档案-外科更新入参")
public class BusExamSurgicalArchiveUpdatePO {

    @ApiModelProperty(value = "主键", name = "id")
    private Long id;

    @ApiModelProperty(value = "身高", name = "height")
    private BigDecimal height;

    @ApiModelProperty(value = "体重", name = "weight")
    private BigDecimal weight;

    @ApiModelProperty(value = "体重签字", name = "shapeSign")
    private String shapeSign;

    @ApiModelProperty(value = "皮肤编码", name = "skinCode")
    private String skinCode;

    @ApiModelProperty(value = "皮肤名称", name = "skinName")
    private String skinName;

    @ApiModelProperty(value = "四肢编码", name = "limbsCode")
    private String limbsCode;

    @ApiModelProperty(value = "四肢名称", name = "limbsName")
    private String limbsName;

    @ApiModelProperty(value = "淋巴编码", name = "lymphaticCode")
    private String lymphaticCode;

    @ApiModelProperty(value = "淋巴名称", name = "lymphaticName")
    private String lymphaticName;

    @ApiModelProperty(value = "关节编码", name = "jointsCode")
    private String jointsCode;

    @ApiModelProperty(value = "关节名称", name = "jointsName")
    private String jointsName;

    @ApiModelProperty(value = "脊柱编码", name = "spineCode")
    private String spineCode;

    @ApiModelProperty(value = "脊柱名称", name = "spineName")
    private String spineName;

    @ApiModelProperty(value = "甲状腺编码", name = "thyroidCode")
    private String thyroidCode;

    @ApiModelProperty(value = "甲状腺名称", name = "thyroidName")
    private String thyroidName;

    @ApiModelProperty(value = "其他", name = "other")
    private String other;

    @ApiModelProperty(value = "签字", name = "signature")
    private String signature;

    @ApiModelProperty(value = "医生建议", name = "suggestion")
    private String suggestion;
}
