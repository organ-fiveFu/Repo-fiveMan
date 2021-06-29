package com.nurse.healthy.model.po.bed;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "房间下拉列表查询ru入参")
@Data
public class SysRoomInfoGetListPO {
    @ApiModelProperty(value = "楼宇编号", name = "buildingCode")
    private String buildingCode;

    @ApiModelProperty(value = "楼层编号", name = "floorCode")
    private String floorCode;
}
