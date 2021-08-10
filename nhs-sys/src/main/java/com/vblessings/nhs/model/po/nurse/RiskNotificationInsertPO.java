package com.vblessings.nhs.model.po.nurse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "风险告知书新增入参")
@Data
public class RiskNotificationInsertPO {

    @ApiModelProperty(name="businessNo", value = "住院号")
    private String businessNo;

    @ApiModelProperty(name="name", value = "姓名")
    private String name;

    @ApiModelProperty(name="sex", value = "性别")
    private String sex;

    @ApiModelProperty(name="age", value = "老人年龄")
    private Integer age;

    @ApiModelProperty(name="informer", value = "告知人姓名")
    private String informer;

    @ApiModelProperty(name="informerTime", value = "告知人签订日期")
    private Date informerTime;

    @ApiModelProperty(name="guardian", value = "监护人")
    private String guardian;

    @ApiModelProperty(name="guardianTime", value = "监护人签订日期")
    private Date guardianTime;
}
