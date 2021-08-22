package com.vblessings.nhs.model.vo.nurse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "合同查询出参")
@Data
public class ContractQueryVO {

    @ApiModelProperty(value = "主键", required = false)
    private Long id;

    @ApiModelProperty(value = "住院号", required = false)
    private String businessNo;

    @ApiModelProperty(value = "姓名", required = false)
    private String name;

    @ApiModelProperty(value = "性别", required = false)
    private String sex;

    @ApiModelProperty(value = "年龄", required = false)
    private Integer age;

    @ApiModelProperty(value = "负责人", required = false)
    private String director;

    @ApiModelProperty(value = "负责人签订时间", required = false)
    private String directorTime;

    @ApiModelProperty(value = "监护人", required = false)
    private String guardian;

    @ApiModelProperty(value = "监护人签订时间", required = false)
    private String guardianTime;

    @ApiModelProperty(value = "合同签订状态", required = false)
    private String isSignContract;
}
