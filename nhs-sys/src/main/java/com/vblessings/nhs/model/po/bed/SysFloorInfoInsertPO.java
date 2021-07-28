package com.vblessings.nhs.model.po.bed;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "楼层信息新增入参")
@Data
public class SysFloorInfoInsertPO {
    @ApiModelProperty(value = "楼层编号", name = "floorCode")
    private String floorCode;

    @ApiModelProperty(value = "楼宇编号", name = "buildingCode")
    private String buildingCode;

    @ApiModelProperty(value = "楼层名称", name = "name")
    private String name;
}
