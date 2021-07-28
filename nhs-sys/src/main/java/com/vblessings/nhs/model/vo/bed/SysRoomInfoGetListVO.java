package com.vblessings.nhs.model.vo.bed;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "房间下拉列表查询出参")
@Data
public class SysRoomInfoGetListVO {
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
}
