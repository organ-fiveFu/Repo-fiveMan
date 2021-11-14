package com.vblessings.nhs.model.po.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(value = "三测单插入&更新入参")
public class BusVitalSignRecordPO {

    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 病人姓名
     */
    @ApiModelProperty(value = "病人姓名")
    private String name;

    /**
     * 老人档案id
     */
    @ApiModelProperty(value = "老人档案id")
    private Long archiveId;

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
     * 排便
     */
    @ApiModelProperty(value = "排便")
    private Integer defecate;

    /**
     * 大便方式
     */
    @ApiModelProperty(value = "大便方式")
    private String defecatePattern;

    /**
     * 体重
     */
    @ApiModelProperty(value = "体重")
    private BigDecimal weight;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Long creatorId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 血氧饱和度
     */
    @ApiModelProperty(value = "血氧饱和度")
    private Integer bloodOxygen;
}
