package com.vblessings.nhs.model.vo.bed;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "楼宇信息查询出参")
@Data
public class SysFloorInfoQueryVO {

    @ApiModelProperty(value = "楼层id")
    private Long id;

    @ApiModelProperty(value = "楼层编号")
    private String floorCode;

    @ApiModelProperty(value = "楼宇编号")
    private String buildingCode;

    @ApiModelProperty(value = "楼层名称")
    private String name;
}
