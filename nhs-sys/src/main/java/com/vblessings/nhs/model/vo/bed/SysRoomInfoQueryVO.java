package com.vblessings.nhs.model.vo.bed;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "房间信息查询出参")
@Data
public class SysRoomInfoQueryVO {

    @ApiModelProperty(value = "房间id")
    private Long id;

    @ApiModelProperty(value = "房间编号")
    private String roomCode;

    @ApiModelProperty(value = "楼层编号")
    private String floorCode;

    @ApiModelProperty(value = "楼宇编号")
    private String buildingCode;

    @ApiModelProperty(value = "房间名称")
    private String name;

    @ApiModelProperty(value = "房间类型")
    private String roomType;

    @ApiModelProperty(value = "房间朝向")
    private String roomToward;
}
