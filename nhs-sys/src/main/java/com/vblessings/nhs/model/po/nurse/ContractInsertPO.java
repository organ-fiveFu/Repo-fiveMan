package com.vblessings.nhs.model.po.nurse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "合同新增入参")
@Data
public class ContractInsertPO {

    @ApiModelProperty(name="businessNo", value = "住院号")
    private String businessNo;

    @ApiModelProperty(name="name", value = "姓名")
    private String name;

    @ApiModelProperty(name="sex", value = "性别")
    private String sex;

    @ApiModelProperty(name="age", value = "年龄")
    private Integer age;

    @ApiModelProperty(name="director", value = "负责人")
    private String director;

    @ApiModelProperty(name="directorTime", value = "负责人签订时间")
    private Date directorTime;

    @ApiModelProperty(name="guardian", value = "监护人")
    private String guardian;

    @ApiModelProperty(name="guardianTime", value = "监护人签订时间")
    private Date guardianTime;
}
