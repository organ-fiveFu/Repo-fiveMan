package com.vblessings.nhs.model.po.bed;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "房间信息更新入参")
@Data
public class SysRoomInfoUpdatePO {
    @ApiModelProperty(value = "房间id", name = "id")
    private Long id;

    @ApiModelProperty(value = "房间编号", name = "roomCode")
    private String roomCode;

    @ApiModelProperty(value = "楼层编号", name = "floorCode")
    private String floorCode;

    @ApiModelProperty(value = "楼宇编号", name = "buildingCode")
    private String buildingCode;

    @ApiModelProperty(value = "房间名称", name = "name")
    private String name;

    @ApiModelProperty(value = "房间类型", name = "roomType")
    private String roomType;

    @ApiModelProperty(value = "房间朝向", name = "roomToward")
    private String roomToward;
}
