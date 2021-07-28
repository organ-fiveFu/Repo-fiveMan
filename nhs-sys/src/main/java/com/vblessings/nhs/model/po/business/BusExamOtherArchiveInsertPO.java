package com.vblessings.nhs.model.po.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "住院档案-其他检查新增入参")
public class BusExamOtherArchiveInsertPO {

    @ApiModelProperty(value = "血常规编码", name = "routineBloodCode")
    private String routineBloodCode;

    @ApiModelProperty(value = "血常规名称", name = "routineBloodName")
    private String routineBloodName;

    @ApiModelProperty(value = "尿常规编码", name = "routineUrineCode")
    private String routineUrineCode;

    @ApiModelProperty(value = "尿常规名称", name = "routineUrineName")
    private String routineUrineName;

    @ApiModelProperty(value = "肝肾功能编码", name = "altCode")
    private String altCode;

    @ApiModelProperty(value = "肝肾功能名称", name = "altName")
    private String altName;

    @ApiModelProperty(value = "胸透编码", name = "chestPerspectiveCode")
    private String chestPerspectiveCode;

    @ApiModelProperty(value = "胸透名称", name = "chestPerspectiveName")
    private String chestPerspectiveName;

    @ApiModelProperty(value = "胸透签字", name = "chestPerspectiveSign")
    private String chestPerspectiveSign;

    @ApiModelProperty(value = "体格检查", name = "physicalCheck")
    private String physicalCheck;
}
