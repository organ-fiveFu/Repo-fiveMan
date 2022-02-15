package com.vblessings.nhs.model.vo.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "带药管理报表查询出参")
public class TakeMedicineReportQueryVO {

    @ApiModelProperty(value = "住院号")
    private String businessNo;

    @ApiModelProperty(value = "病人姓名")
    private String name;

    @ApiModelProperty(value = "药品名称及规格")
    private String drugName;

    @ApiModelProperty(value = "用法")
    private String useWay;

    @ApiModelProperty(value = "用量")
    private String dosage;

    @ApiModelProperty(value = "楼宇名称")
    private String buildingName;

    @ApiModelProperty(value = "楼层名称")
    private String floorName;

    @ApiModelProperty(value = "房间名称")
    private String roomName;

    @ApiModelProperty(value = "床位名称")
    private String bedName;

    @ApiModelProperty(value = "拼接床位名称")
    private String spliceBedName;
}
