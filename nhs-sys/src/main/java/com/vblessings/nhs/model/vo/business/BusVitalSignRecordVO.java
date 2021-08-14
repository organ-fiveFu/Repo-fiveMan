package com.vblessings.nhs.model.vo.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(value = "三测单记录")
public class BusVitalSignRecordVO {

    @ApiModelProperty(value = "")
    private Long id;

    /**
     * 住院号
     */
    @ApiModelProperty(value = "住院号")
    private String businessNo;

    /**
     * 记录时间
     */
    @ApiModelProperty(value = "记录时间")
    private String recordTime;

    /**
     * 记录时间节点
     */
    @ApiModelProperty(value = "记录时间节点")
    private String timePoint;

    /**
     * 住院天数
     */
    @ApiModelProperty(value = "住院天数")
    private Integer lengthOfStay;

    /**
     * 体温
     */
    @ApiModelProperty(value = "体温")
    private BigDecimal temperature;

    /**
     * 脉搏
     */
    @ApiModelProperty(value = "脉搏")
    private Integer pulse;

    /**
     * 呼吸
     */
    @ApiModelProperty(value = "呼吸")
    private Integer breathing;

    /**
     * 血压低值
     */
    @ApiModelProperty(value = "血压低值")
    private Integer lowBloodPressure;

    /**
     * 血压高值
     */
    @ApiModelProperty(value = "血压高值")
    private Integer highBloodPressure;

    /**
     * 入量
     */
    @ApiModelProperty(value = "入量")
    private BigDecimal intake;

    /**
     * 出量
     */
    @ApiModelProperty(value = "出量")
    private BigDecimal output;

    /**
     * 尿量
     */
    @ApiModelProperty(value = "尿量")
    private BigDecimal urine;

    /**
     * 体重
     */
    @ApiModelProperty(value = "体重")
    private BigDecimal weight;

    /**
     * 血氧饱和度
     */
    @ApiModelProperty(value = "血氧饱和度")
    private Integer bloodOxygen;
}
