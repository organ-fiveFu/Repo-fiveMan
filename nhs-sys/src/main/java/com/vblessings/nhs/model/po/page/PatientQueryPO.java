package com.vblessings.nhs.model.po.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "病人基础信息查询入参")
@Data
public class PatientQueryPO {
    @ApiModelProperty(value = "关键字", name = "keyWords")
    private String keyWords;
}
