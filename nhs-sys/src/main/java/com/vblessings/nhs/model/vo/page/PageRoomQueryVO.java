package com.vblessings.nhs.model.vo.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "首页房间查询出参")
public class PageRoomQueryVO {
    @ApiModelProperty(value = "楼层编码")
    private String floorCode;

    @ApiModelProperty(value = "楼层名称")
    private String floorName;

    @ApiModelProperty(value = "房间编码")
    private String roomCode;

    @ApiModelProperty(value = "房间名称")
    private String roomName;

    @ApiModelProperty(value = "房间类型code")
    private String roomType;

    @ApiModelProperty(value = "房间类型name")
    private String roomTypeName;

    @ApiModelProperty(value = "首页查询详情出参")
    private List<PageDetailQueryVO> pageDetailQueryVOS;

}
