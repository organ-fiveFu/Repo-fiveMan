package com.vblessings.nhs.model.vo.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "首页查询出参")
public class PageQueryVO {

    @ApiModelProperty(value = "楼层编码")
    private String floorCode;

    @ApiModelProperty(value = "楼层名称")
    private String floorName;

    @ApiModelProperty(value = "首页房间详情出参")
    private List<PageRoomQueryVO> pageRoomQueryVOList;
}
