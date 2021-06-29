package com.nurse.healthy.model.po.bed;

import com.nurse.healthy.model.po.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "房间信息查询入参")
@Data
public class SysRoomInfoQueryPO extends PageParam {

    @ApiModelProperty(value = "楼层id", name = "id")
    private Long id;

    @ApiModelProperty(value = "关键字", name = "keyWords")
    private String keyWords;
}
