package com.vblessings.nhs.model.vo.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "住院档案-其他检查查询出参")
public class BusExamOtherArchiveQueryVO {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "体检档案id")
    private Long examId;

    @ApiModelProperty(value = "血常规编码")
    private String routineBloodCode;

    @ApiModelProperty(value = "血常规名称")
    private String routineBloodName;

    @ApiModelProperty(value = "尿常规编码")
    private String routineUrineCode;

    @ApiModelProperty(value = "尿常规名称")
    private String routineUrineName;

    @ApiModelProperty(value = "肝肾功能编码")
    private String altCode;

    @ApiModelProperty(value = "肝肾功能名称")
    private String altName;

    @ApiModelProperty(value = "胸透编码")
    private String chestPerspectiveCode;

    @ApiModelProperty(value = "胸透名称")
    private String chestPerspectiveName;

    @ApiModelProperty(value = "胸透签字")
    private String chestPerspectiveSign;

    @ApiModelProperty(value = "体格检查")
    private String physicalCheck;
}
