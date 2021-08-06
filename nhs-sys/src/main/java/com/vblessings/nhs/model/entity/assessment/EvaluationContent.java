package com.vblessings.nhs.model.entity.assessment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EvaluationContent {

    @ApiModelProperty(value = "名称", name = "name")
    private String name;

    @ApiModelProperty(value = "分数", name = "point")
    private Integer point;
}
