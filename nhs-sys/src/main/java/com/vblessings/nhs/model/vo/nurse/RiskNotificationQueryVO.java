package com.vblessings.nhs.model.vo.nurse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "风险告知书查询出参")
@Data
public class RiskNotificationQueryVO {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "住院号")
    private String businessNo;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "老人年龄")
    private Integer age;

    @ApiModelProperty(value = "告知人姓名")
    private String informer;

    @ApiModelProperty(value = "告知人签订日期")
    private String informerTime;

    @ApiModelProperty(value = "监护人")
    private String guardian;

    @ApiModelProperty(value = "监护人签订日期")
    private String guardianTime;

    @ApiModelProperty(value = "风险告知书签订状态")
    private String isSignNotification;
}
