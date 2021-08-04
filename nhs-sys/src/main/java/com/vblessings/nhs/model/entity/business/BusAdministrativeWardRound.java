package com.vblessings.nhs.model.entity.business;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Copyright (c) 2017 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * BusAdministrativeWardRound
 *
 * @author linxiazhu-auto-build
 * @since  2021-08-04 13:43:05
 */
@Data
@Table(name = "bus_administrative_ward_round")
public class BusAdministrativeWardRound implements Serializable {
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = false)
    @Id
    private Long id;
	
		/**
	 * 查房人
	 */
	@ApiModelProperty(value = "查房人", required = false)
    @Column(name = "ward_rounds")
    private String wardRounds;
	
		/**
	 * 查房时间
	 */
	@ApiModelProperty(value = "查房时间", required = false)
    @Column(name = "ward_round_time")
    private Date wardRoundTime;
	
		/**
	 * 生活照料情况
	 */
	@ApiModelProperty(value = "生活照料情况", required = false)
    @Column(name = "living_conditions")
    private String livingConditions;
	
		/**
	 * 生活照料意见
	 */
	@ApiModelProperty(value = "生活照料意见", required = false)
    @Column(name = "life_opinion")
    private String lifeOpinion;
	
		/**
	 * 心理护理情况
	 */
	@ApiModelProperty(value = "心理护理情况", required = false)
    @Column(name = "psychological_situation")
    private String psychologicalSituation;
	
		/**
	 * 心理护理意见
	 */
	@ApiModelProperty(value = "心理护理意见", required = false)
    @Column(name = "psychological_opinion")
    private String psychologicalOpinion;
	
		/**
	 * 后勤保障情况
	 */
	@ApiModelProperty(value = "后勤保障情况", required = false)
    @Column(name = "logistics_situation")
    private String logisticsSituation;
	
		/**
	 * 后勤保障意见
	 */
	@ApiModelProperty(value = "后勤保障意见", required = false)
    @Column(name = "logistics_opinion")
    private String logisticsOpinion;
	
		/**
	 * 安全隐患情况
	 */
	@ApiModelProperty(value = "安全隐患情况", required = false)
    @Column(name = "security_situation")
    private String securitySituation;
	
		/**
	 * 安全隐患意见
	 */
	@ApiModelProperty(value = "安全隐患意见", required = false)
    @Column(name = "safety_advice")
    private String safetyAdvice;
	
		/**
	 * 环境卫生及其他情况
	 */
	@ApiModelProperty(value = "环境卫生及其他情况", required = false)
    @Column(name = "environmental_conditions")
    private String environmentalConditions;
	
		/**
	 * 环境卫生及其他意见
	 */
	@ApiModelProperty(value = "环境卫生及其他意见", required = false)
    @Column(name = "environmental_opinion")
    private String environmentalOpinion;
	
		/**
	 * 意见或建议情况
	 */
	@ApiModelProperty(value = "意见或建议情况", required = false)
    @Column(name = "opinion_situation")
    private String opinionSituation;
	
		/**
	 * 意见或建立意见
	 */
	@ApiModelProperty(value = "意见或建立意见", required = false)
    @Column(name = "opinion")
    private String opinion;
	
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
	 * 删除标志
	 */
	@ApiModelProperty(value = "删除标志", required = false)
    @Column(name = "is_del")
    private Integer isDel;
	
}
