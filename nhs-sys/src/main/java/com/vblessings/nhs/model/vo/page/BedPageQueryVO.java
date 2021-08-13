package com.vblessings.nhs.model.vo.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "首页床位查询出参")
public class BedPageQueryVO {

    @ApiModelProperty(value = "总床位数")
    private String bedNum;

    @ApiModelProperty(value = "空闲床位数")
    private String freeBedNum;

    @ApiModelProperty(value = "入住床位数")
    private String checkInBedNum;

    @ApiModelProperty(value = "总房间数")
    private String roomNum;
}
