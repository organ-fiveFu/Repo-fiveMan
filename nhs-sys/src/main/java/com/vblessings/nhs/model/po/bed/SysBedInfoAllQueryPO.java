package com.vblessings.nhs.model.po.bed;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "所有床位信息查询入参")
@Data
public class SysBedInfoAllQueryPO {
    @ApiModelProperty(value = "关键字", name = "keyWords")
    private String keyWords;
}
