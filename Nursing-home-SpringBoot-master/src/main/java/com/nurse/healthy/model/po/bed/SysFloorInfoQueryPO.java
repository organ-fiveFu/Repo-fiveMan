package com.nurse.healthy.model.po.bed;

import com.nurse.healthy.model.po.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "楼层信息查询入参")
@Data
public class SysFloorInfoQueryPO extends PageParam {

    @ApiModelProperty(value = "楼宇id", name = "id")
    private Long id;

    @ApiModelProperty(value = "关键字", name = "keyWords")
    private String keyWords;
}
