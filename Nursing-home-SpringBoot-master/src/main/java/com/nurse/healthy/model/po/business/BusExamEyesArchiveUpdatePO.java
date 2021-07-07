package com.nurse.healthy.model.po.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "住院档案-眼科更新入参")
public class BusExamEyesArchiveUpdatePO {

    @ApiModelProperty(value = "主键", name = "id")
    private Long id;

    @ApiModelProperty(value = "裸眼视力左", name = "ucvaLeft")
    private BigDecimal ucvaLeft;

    @ApiModelProperty(value = "裸眼视力右", name = "ucvaRight")
    private BigDecimal ucvaRight;

    @ApiModelProperty(value = "矫正视力左", name = "cvaLeft")
    private BigDecimal cvaLeft;

    @ApiModelProperty(value = "矫正视力右", name = "cvaRight")
    private BigDecimal cvaRight;

    @ApiModelProperty(value = "视觉编码", name = "colorVisionCode")
    private String colorVisionCode;

    @ApiModelProperty(value = "视觉名称", name = "colorVisionName")
    private String colorVisionName;

    @ApiModelProperty(value = "视觉签字", name = "colorVisionSign")
    private String colorVisionSign;

    @ApiModelProperty(value = "签字", name = "signature")
    private String signature;

    @ApiModelProperty(value = "医生建议", name = "suggestion")
    private String suggestion;

    @ApiModelProperty(value = "其他", name = "other")
    private String other;
}
