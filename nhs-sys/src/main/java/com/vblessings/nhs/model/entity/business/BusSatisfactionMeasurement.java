package com.vblessings.nhs.model.entity.business;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "bus_satisfaction_measurement")
public class BusSatisfactionMeasurement implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "", required = false)
    @Id
    private Long id;

    /**
     * 生活环境测评
     */
    @ApiModelProperty(value = "生活环境测评", required = false)
    @Column(name = "environment")
    private String environment;

    /**
     * 护理质量测评
     */
    @ApiModelProperty(value = "护理质量测评", required = false)
    @Column(name = "care_quality")
    private String careQuality;

    /**
     * 客服人员测评
     */
    @ApiModelProperty(value = "客服人员测评", required = false)
    @Column(name = "customer_service")
    private String customerService;

    /**
     * 医疗服务测评
     */
    @ApiModelProperty(value = "医疗服务测评", required = false)
    @Column(name = "medical_service")
    private String medicalService;

    /**
     * 文娱活动测评
     */
    @ApiModelProperty(value = "文娱活动测评", required = false)
    @Column(name = "recreational_activity")
    private String recreationalActivity;

    /**
     * 饮食测评
     */
    @ApiModelProperty(value = "饮食测评", required = false)
    @Column(name = "diet")
    private String diet;

    /**
     * 事务处理测评
     */
    @ApiModelProperty(value = "事务处理测评", required = false)
    @Column(name = "deal")
    private String deal;

    /**
     * 综合服务测评
     */
    @ApiModelProperty(value = "综合服务测评", required = false)
    @Column(name = "synthetic_service")
    private String syntheticService;

    /**
     * 建议
     */
    @ApiModelProperty(value = "建议", required = false)
    @Column(name = "suggestion")
    private String suggestion;

    /**
     * 总计
     */
    @ApiModelProperty(value = "总计", required = false)
    @Column(name = "total")
    private String total;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", required = false)
    @Column(name = "creator_id")
    private Long creatorId;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人", required = false)
    @Column(name = "updater_id")
    private Long updaterId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = false)
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", required = false)
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用", required = false)
    @Column(name = "is_del")
    private Integer isDel;

    /**
     * 评价人
     */
    @ApiModelProperty(value = "评价人", required = false)
    @Column(name = "name")
    private String name;

    /**
     * 评价人号码
     */
    @ApiModelProperty(value = "评价人号码", required = false)
    @Column(name = "phone")
    private String phone;
}

