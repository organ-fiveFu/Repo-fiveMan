package com.vblessings.nhs.model.vo.bed;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "所有床位信息查询出参")
@Data
public class SysBedInfoAllQueryVO {

    @ApiModelProperty(value = "床位id")
    private Long id;

    @ApiModelProperty(value = "床位编号")
    private String bedCode;

    @ApiModelProperty(value = "床位名称")
    private String bedName;

    @ApiModelProperty(value = "房间编号")
    private String roomCode;

    @ApiModelProperty(value = "房间名称")
    private String roomName;

    @ApiModelProperty(value = "楼层编号")
    private String floorCode;

    @ApiModelProperty(value = "楼层名称")
    private String floorName;

    @ApiModelProperty(value = "楼宇编号")
    private String buildingCode;

    @ApiModelProperty(value = "楼宇名称")
    private String buildingName;

    @ApiModelProperty(value = "房间类型")
    private String roomType;

    @ApiModelProperty(value = "房间类型名称")
    private String roomTypeName;

    @ApiModelProperty(value = "楼宇+楼层+房间+床位名称")
    private String name;

    @ApiModelProperty(value = "入住状态")
    private String status;
}
