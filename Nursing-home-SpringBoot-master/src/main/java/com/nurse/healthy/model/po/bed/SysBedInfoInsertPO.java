package com.nurse.healthy.model.po.bed;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "床位信息新增入参")
@Data
public class SysBedInfoInsertPO {
    @ApiModelProperty(value = "床位编号", name = "bedCode")
    private String bedCode;

    @ApiModelProperty(value = "房间编号", name = "roomCode")
    private String roomCode;

    @ApiModelProperty(value = "楼层编号", name = "floorCode")
    private String floorCode;

    @ApiModelProperty(value = "楼宇编号", name = "buildingCode")
    private String buildingCode;

    @ApiModelProperty(value = "床位名称", name = "name")
    private String name;

    @ApiModelProperty(value = "入住状态", name = "status")
    private Integer status;

    @ApiModelProperty(value = "使用标志(1使用0停用)", name = "useFlag")
    private Integer useFlag;
}
