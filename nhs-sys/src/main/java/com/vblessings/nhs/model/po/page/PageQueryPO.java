package com.vblessings.nhs.model.po.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "首页查询入参")
@Data
public class PageQueryPO {

    @ApiModelProperty(value = "楼宇code")
    private String buildingCode;
}
