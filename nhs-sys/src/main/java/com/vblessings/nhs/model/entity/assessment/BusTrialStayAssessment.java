package com.vblessings.nhs.model.entity.assessment;

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
 * BusTrialStayAssessment
 *
 * @author linxiazhu-auto-build
 * @since  2021-07-14 14:46:43
 */
@Data
@Table(name = "bus_trial_stay_assessment")
public class BusTrialStayAssessment implements Serializable {
	private static final long serialVersionUID = 1L;
	
		/**
	 * 
	 */
	@ApiModelProperty(value = "", required = false)
    @Id
    private Long id;
	
		/**
	 * 住院号
	 */
	@ApiModelProperty(value = "住院号", required = false)
    @Column(name = "business_no")
    private String businessNo;
	
		/**
	 * 老人姓名
	 */
	@ApiModelProperty(value = "老人姓名", required = false)
    @Column(name = "name")
    private String name;
	
		/**
	 * 老人性别
	 */
	@ApiModelProperty(value = "老人性别", required = false)
    @Column(name = "sex")
    private String sex;
	
		/**
	 * 老人年龄
	 */
	@ApiModelProperty(value = "老人年龄", required = false)
    @Column(name = "age")
    private Integer age;
	
		/**
	 * 床号
	 */
	@ApiModelProperty(value = "床号", required = false)
    @Column(name = "bed_code")
    private String bedCode;
	
		/**
	 * 楼层
	 */
	@ApiModelProperty(value = "楼层", required = false)
    @Column(name = "floor_code")
    private String floorCode;
	
		/**
	 * 入院时间
	 */
	@ApiModelProperty(value = "入院时间", required = false)
    @Column(name = "admission_time")
    private Date admissionTime;
	
		/**
	 * 生活能力
	 */
	@ApiModelProperty(value = "生活能力", required = false)
    @Column(name = "life_ability")
    private String lifeAbility;
	
		/**
	 * 生活能力评估人
	 */
	@ApiModelProperty(value = "生活能力评估人", required = false)
    @Column(name = "life_ability_evaluator")
    private String lifeAbilityEvaluator;
	
		/**
	 * 生活能力评估日期
	 */
	@ApiModelProperty(value = "生活能力评估日期", required = false)
    @Column(name = "life_evaluate_time")
    private Date lifeEvaluateTime;
	
		/**
	 * 认知能力
	 */
	@ApiModelProperty(value = "认知能力", required = false)
    @Column(name = "cognitive_ability")
    private String cognitiveAbility;
	
		/**
	 * 认知能力评估人
	 */
	@ApiModelProperty(value = "认知能力评估人", required = false)
    @Column(name = "cognitive_ability_evaluator")
    private String cognitiveAbilityEvaluator;
	
		/**
	 * 认知能力评估日期
	 */
	@ApiModelProperty(value = "认知能力评估日期", required = false)
    @Column(name = "cognitive_evaluate_time")
    private Date cognitiveEvaluateTime;
	
		/**
	 * 情绪行为
	 */
	@ApiModelProperty(value = "情绪行为", required = false)
    @Column(name = "emotion_behavior")
    private String emotionBehavior;
	
		/**
	 * 情绪行为评估人
	 */
	@ApiModelProperty(value = "情绪行为评估人", required = false)
    @Column(name = "emotion_behavior_evaluator")
    private String emotionBehaviorEvaluator;
	
		/**
	 * 情绪行为评估日期
	 */
	@ApiModelProperty(value = "情绪行为评估日期", required = false)
    @Column(name = "emotion_behavior_evaluate_time")
    private Date emotionBehaviorEvaluateTime;
	
		/**
	 * 健康状况
	 */
	@ApiModelProperty(value = "健康状况", required = false)
    @Column(name = "health")
    private String health;
	
		/**
	 * 健康状况评估人
	 */
	@ApiModelProperty(value = "健康状况评估人", required = false)
    @Column(name = "health_evaluator")
    private String healthEvaluator;
	
		/**
	 * 健康状况评估时间
	 */
	@ApiModelProperty(value = "健康状况评估时间", required = false)
    @Column(name = "health_evaluate_time")
    private Date healthEvaluateTime;
	
		/**
	 * 特殊需求
	 */
	@ApiModelProperty(value = "特殊需求", required = false)
    @Column(name = "special_requirement")
    private String specialRequirement;
	
		/**
	 * 特殊需求评估人
	 */
	@ApiModelProperty(value = "特殊需求评估人", required = false)
    @Column(name = "special_requirement_evaluator")
    private String specialRequirementEvaluator;
	
		/**
	 * 特殊需求日期
	 */
	@ApiModelProperty(value = "特殊需求日期", required = false)
    @Column(name = "special_requirement_time")
    private Date specialRequirementTime;
	
		/**
	 * 评估小组意见
	 */
	@ApiModelProperty(value = "评估小组意见", required = false)
    @Column(name = "assessment_team_suggest")
    private String assessmentTeamSuggest;
	
		/**
	 * 评估小组签名
	 */
	@ApiModelProperty(value = "评估小组签名", required = false)
    @Column(name = "assessment_team_sign")
    private String assessmentTeamSign;
	
		/**
	 * 评估小组签名日期
	 */
	@ApiModelProperty(value = "评估小组签名日期", required = false)
    @Column(name = "team_sign_time")
    private Date teamSignTime;
	
		/**
	 * 院长意见
	 */
	@ApiModelProperty(value = "院长意见", required = false)
    @Column(name = "dean_suggest")
    private String deanSuggest;
	
		/**
	 * 院长签名
	 */
	@ApiModelProperty(value = "院长签名", required = false)
    @Column(name = "dean_sign")
    private String deanSign;
	
		/**
	 * 院长签名时间
	 */
	@ApiModelProperty(value = "院长签名时间", required = false)
    @Column(name = "dean_sign_time")
    private Date deanSignTime;
	
		/**
	 * 家属和老人意见
	 */
	@ApiModelProperty(value = "家属和老人意见", required = false)
    @Column(name = "patient_suggest")
    private String patientSuggest;
	
		/**
	 * 家属和老人签字
	 */
	@ApiModelProperty(value = "家属和老人签字", required = false)
    @Column(name = "patient_sign")
    private String patientSign;
	
		/**
	 * 家属和老人签字日期
	 */
	@ApiModelProperty(value = "家属和老人签字日期", required = false)
    @Column(name = "patient_sign_time")
    private Date patientSignTime;
	
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
	 * 楼宇
	 */
	@ApiModelProperty(value = "楼宇", required = false)
	@Column(name = "building_code")
	private String buildingCode;

	@ApiModelProperty(value = "老人档案id", required = false)
	@Column(name = "archive_id")
	private Long archiveId;
}
