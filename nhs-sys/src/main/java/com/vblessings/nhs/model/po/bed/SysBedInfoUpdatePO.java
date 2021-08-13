package com.vblessings.nhs.model.po.bed;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "床位信息修改入参")
@Data
public class SysBedInfoUpdatePO {

    @ApiModelProperty(value = "床位id", name = "id")
    private Long id;

    @ApiModelProperty(value = "床位编号", name = "bedCode")
    private String bedCode;

    @ApiModelProperty(value = "房间编号", name = "roomCode")
    private String roomCode;

    @ApiModelProperty(value = "房间名称", name = "roomName")
    private String roomName;

    @ApiModelProperty(value = "楼层编号", name = "floorCode")
    private String floorCode;

    @ApiModelProperty(value = "楼层名称", name = "floorName")
    private String floorName;

    @ApiModelProperty(value = "楼宇编号", name = "buildingCode")
    private String buildingCode;

    @ApiModelProperty(value = "楼宇名称", name = "buildingName")
    private String buildingName;

    @ApiModelProperty(value = "床位名称", name = "name")
    private String name;

    @ApiModelProperty(value = "入住状态", name = "status")
    private String status;

    @ApiModelProperty(value = "使用标志(1使用0停用)", name = "useFlag")
    private Integer useFlag;
}
