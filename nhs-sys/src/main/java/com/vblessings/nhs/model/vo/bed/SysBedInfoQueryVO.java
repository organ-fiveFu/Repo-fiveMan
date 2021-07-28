package com.vblessings.nhs.model.vo.bed;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "床位信息查询出参")
@Data
public class SysBedInfoQueryVO {

    @ApiModelProperty(value = "床位id")
    private Long id;

    @ApiModelProperty(value = "床位编号")
    private String bedCode;

    @ApiModelProperty(value = "房间编号")
    private String roomCode;

    @ApiModelProperty(value = "楼层编号")
    private String floorCode;

    @ApiModelProperty(value = "楼宇编号")
    private String buildingCode;

    @ApiModelProperty(value = "床位名称")
    private String name;

    @ApiModelProperty(value = "入住状态")
    private Integer status;

    @ApiModelProperty(value = "使用标志(1使用0停用)")
    private Integer useFlag;
}
