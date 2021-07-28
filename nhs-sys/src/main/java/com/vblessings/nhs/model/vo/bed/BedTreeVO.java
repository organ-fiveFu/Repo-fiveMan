package com.vblessings.nhs.model.vo.bed;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "床位信息树查询出参")
@Data
public class BedTreeVO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "父类id")
    private Long parentId;

    @ApiModelProperty(value = "子类name")
    private String name;

    @ApiModelProperty(value = "父类name")
    private String parentName;
}
