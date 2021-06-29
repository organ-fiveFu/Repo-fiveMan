package com.nurse.healthy.model.po.bed;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "楼宇信息修改入参")
@Data
public class SysBuildingInfoUpdatePO {

    @ApiModelProperty(value = "楼宇id", name = "id")
    private Long id;

    @ApiModelProperty(value = "楼宇编号", name = "buildingCode")
    private String buildingCode;

    @ApiModelProperty(value = "楼宇名称", name = "name")
    private String name;
}
