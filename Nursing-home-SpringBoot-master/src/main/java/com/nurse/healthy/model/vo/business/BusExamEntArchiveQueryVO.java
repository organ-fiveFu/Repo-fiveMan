package com.nurse.healthy.model.vo.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "住院档案-五官科查询出参")
public class BusExamEntArchiveQueryVO {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "体检档案id")
    private Long examId;

    @ApiModelProperty(value = "听力左")
    private BigDecimal hearingLeft;

    @ApiModelProperty(value = "听力右")
    private BigDecimal hearingRight;

    @ApiModelProperty(value = "听力签字")
    private String hearingSign;

    @ApiModelProperty(value = "耳疾编码")
    private String earCode;

    @ApiModelProperty(value = "耳疾名称")
    private String earName;

    @ApiModelProperty(value = "嗅觉编码")
    private String smellCode;

    @ApiModelProperty(value = "嗅觉名称")
    private String smellName;

    @ApiModelProperty(value = "嗅觉签字")
    private String smellSign;

    @ApiModelProperty(value = "口吃编码")
    private String stutteringCode;

    @ApiModelProperty(value = "口吃名称")
    private String stutteringName;

    @ApiModelProperty(value = "面部编码")
    private String faceCode;

    @ApiModelProperty(value = "面部名称")
    private String faceName;

    @ApiModelProperty(value = "其他")
    private String other;

    @ApiModelProperty(value = "签字")
    private String signature;

    @ApiModelProperty(value = "医生建议")
    private String suggestion;
}
