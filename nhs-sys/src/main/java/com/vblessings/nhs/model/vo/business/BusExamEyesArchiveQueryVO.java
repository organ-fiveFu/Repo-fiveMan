package com.vblessings.nhs.model.vo.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "住院档案-眼科查询出参")
public class BusExamEyesArchiveQueryVO {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "体检档案id")
    private Long examId;

    @ApiModelProperty(value = "裸眼视力左")
    private BigDecimal ucvaLeft;

    @ApiModelProperty(value = "裸眼视力右")
    private BigDecimal ucvaRight;

    @ApiModelProperty(value = "矫正视力左")
    private BigDecimal cvaLeft;

    @ApiModelProperty(value = "矫正视力右")
    private BigDecimal cvaRight;

    @ApiModelProperty(value = "视觉编码")
    private String colorVisionCode;

    @ApiModelProperty(value = "视觉名称")
    private String colorVisionName;

    @ApiModelProperty(value = "视觉签字")
    private String colorVisionSign;

    @ApiModelProperty(value = "签字")
    private String signature;

    @ApiModelProperty(value = "医生建议")
    private String suggestion;

    @ApiModelProperty(value = "其他")
    private String other;
}
