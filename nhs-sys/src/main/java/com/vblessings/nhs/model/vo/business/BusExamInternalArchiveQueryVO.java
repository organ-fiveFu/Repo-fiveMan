package com.vblessings.nhs.model.vo.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "住院档案-内科查询出参")
public class BusExamInternalArchiveQueryVO {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "体检档案id")
    private Long examId;

    @ApiModelProperty(value = "心率")
    private Integer heartRate;

    @ApiModelProperty(value = "血压高")
    private Integer bloodPressureHigh;

    @ApiModelProperty(value = "血压低")
    private Integer bloodPressureLow;

    @ApiModelProperty(value = "心脏签字")
    private String heartSign;

    @ApiModelProperty(value = "营养状况编码")
    private String nutritureCode;

    @ApiModelProperty(value = "营养状况名称")
    private String nutritureName;

    @ApiModelProperty(value = "心血管编码")
    private String cardiovascularCode;

    @ApiModelProperty(value = "心血管名称")
    private String cardiovascularName;

    @ApiModelProperty(value = "神经编码")
    private String nerveCode;

    @ApiModelProperty(value = "神经名称")
    private String nerveName;

    @ApiModelProperty(value = "肝编码")
    private String liverCode;

    @ApiModelProperty(value = "肝名称")
    private String liverName;

    @ApiModelProperty(value = "肺编码")
    private String lungCode;

    @ApiModelProperty(value = "肺名称")
    private String lungName;

    @ApiModelProperty(value = "脾编码")
    private String spleenCode;

    @ApiModelProperty(value = "脾名称")
    private String spleenName;

    @ApiModelProperty(value = "其他")
    private String other;

    @ApiModelProperty(value = "签字")
    private String signature;

    @ApiModelProperty(value = "医生建议")
    private String suggestion;
}
