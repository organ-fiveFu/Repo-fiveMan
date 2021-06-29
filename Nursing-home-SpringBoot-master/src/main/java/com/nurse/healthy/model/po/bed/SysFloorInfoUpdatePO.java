package com.nurse.healthy.model.po.bed;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "楼层信息更新入参")
@Data
public class SysFloorInfoUpdatePO {
    @ApiModelProperty(value = "楼层id", name = "id")
    private Long id;

    @ApiModelProperty(value = "楼层编号", name = "floorCode")
    private String floorCode;

    @ApiModelProperty(value = "楼宇编号", name = "buildingCode")
    private String buildingCode;

    @ApiModelProperty(value = "楼层名称", name = "name")
    private String name;
}
