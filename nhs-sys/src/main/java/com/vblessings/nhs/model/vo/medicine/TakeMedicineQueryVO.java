package com.vblessings.nhs.model.vo.medicine;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "代配药查询出参")
@Data
public class TakeMedicineQueryVO {
    @ApiModelProperty(value = "床号")
    private String bedCode;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "药品名称")
    private String drugName;

    @ApiModelProperty(value = "计量")
    private String measure;

    @ApiModelProperty(value = "频次")
    private String dosage;
}
