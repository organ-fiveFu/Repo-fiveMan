package com.vblessings.nhs.model.vo.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(value = "三测单出参")
public class BusVitalSignVO {

    /**
     * 老人姓名
     */
    @ApiModelProperty(value = "老人姓名")
    private String name;

    /**
     * 老人性别
     */
    @ApiModelProperty(value = "老人性别")
    private String sex;

    /**
     * 老人年龄
     */
    @ApiModelProperty(value = "老人年龄")
    private Integer age;

    /**
     * 床位编号
     */
    @ApiModelProperty(value = "床位编号")
    private String bedCode;

    /**
     * 病区
     */
    @ApiModelProperty(value = "病区")
    private String ward;

    /**
     * 三测单记录
     */
    @ApiModelProperty(value = "三测单记录")
    private List<BusVitalSignRecordVO> busVitalSignRecordVOS;
}
