package com.nurse.healthy.model.po.bed;

import com.nurse.healthy.model.po.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "床位信息查询入参")
@Data
public class SysBedInfoQueryPO extends PageParam {

    @ApiModelProperty(value = "房间id", name = "id")
    private Long id;

    @ApiModelProperty(value = "关键字", name = "keyWords")
    private String keyWords;

    @ApiModelProperty(value = "是否停用(1为停用，0为全部)", name = "usedFlag")
    private String usedFlag;
}
