package com.nurse.healthy.model.vo.business;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "住院档案-外科查询出参")
public class BusExamSurgicalArchiveQueryVO {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "体检档案id")
    private Long examId;

    @ApiModelProperty(value = "身高")
    private BigDecimal height;

    @ApiModelProperty(value = "体重")
    private BigDecimal weight;

    @ApiModelProperty(value = "体重签字")
    private String shapeSign;

    @ApiModelProperty(value = "皮肤编码")
    private String skinCode;

    @ApiModelProperty(value = "皮肤名称")
    private String skinName;

    @ApiModelProperty(value = "四肢编码")
    private String limbsCode;

    @ApiModelProperty(value = "四肢名称")
    private String limbsName;

    @ApiModelProperty(value = "淋巴编码")
    private String lymphaticCode;

    @ApiModelProperty(value = "淋巴名称")
    private String lymphaticName;

    @ApiModelProperty(value = "关节编码")
    private String jointsCode;

    @ApiModelProperty(value = "关节名称")
    private String jointsName;

    @ApiModelProperty(value = "脊柱编码")
    private String spineCode;

    @ApiModelProperty(value = "脊柱名称")
    private String spineName;

    @ApiModelProperty(value = "甲状腺编码")
    private String thyroidCode;

    @ApiModelProperty(value = "甲状腺名称")
    private String thyroidName;

    @ApiModelProperty(value = "其他")
    private String other;

    @ApiModelProperty(value = "签字")
    private String signature;

    @ApiModelProperty(value = "医生建议")
    private String suggestion;
}
