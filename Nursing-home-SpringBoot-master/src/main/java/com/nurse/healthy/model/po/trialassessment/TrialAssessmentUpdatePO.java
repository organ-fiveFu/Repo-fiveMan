package com.nurse.healthy.model.po.trialassessment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "试用期评估信息更新入参")
@Data
public class TrialAssessmentUpdatePO {
    @ApiModelProperty(value = "主键", name = "id")
    private Long id;

    @ApiModelProperty(value = "住院号", name = "businessNo")
    private String businessNo;

    @ApiModelProperty(value = "老人姓名", name = "name")
    private String name;

    @ApiModelProperty(value = "老人性别", name = "sex")
    private String sex;

    @ApiModelProperty(value = "老人年龄", name = "age")
    private Integer age;

    @ApiModelProperty(value = "床号", name = "bedCode")
    private String bedCode;

    @ApiModelProperty(value = "楼层", name = "floorCode")
    private String floorCode;

    @ApiModelProperty(value = "入院时间", name = "admissionTime")
    private Date admissionTime;

    @ApiModelProperty(value = "生活能力", name = "lifeAbility")
    private String lifeAbility;

    @ApiModelProperty(value = "生活能力评估人", name = "lifeAbilityEvaluator")
    private String lifeAbilityEvaluator;

    @ApiModelProperty(value = "生活能力评估日期", name = "lifeEvaluateTime")
    private Date lifeEvaluateTime;

    @ApiModelProperty(value = "认知能力", name = "cognitiveAbility")
    private String cognitiveAbility;

    @ApiModelProperty(value = "认知能力评估人", name = "cognitiveAbilityEvaluator")
    private String cognitiveAbilityEvaluator;

    @ApiModelProperty(value = "认知能力评估日期", name = "cognitiveEvaluateTime")
    private Date cognitiveEvaluateTime;

    @ApiModelProperty(value = "情绪行为", name = "emotionBehavior")
    private String emotionBehavior;

    @ApiModelProperty(value = "情绪行为评估人", name = "emotionBehaviorEvaluator")
    private String emotionBehaviorEvaluator;

    @ApiModelProperty(value = "情绪行为评估日期", name = "emotionBehaviorEvaluateTime")
    private Date emotionBehaviorEvaluateTime;

    @ApiModelProperty(value = "健康状况", name = "health")
    private String health;

    @ApiModelProperty(value = "健康状况评估人", name = "healthEvaluator")
    private String healthEvaluator;

    @ApiModelProperty(value = "健康状况评估时间", name = "healthEvaluateTime")
    private Date healthEvaluateTime;

    @ApiModelProperty(value = "特殊需求", name = "specialRequirement")
    private String specialRequirement;

    @ApiModelProperty(value = "特殊需求评估人", name = "specialRequirementEvaluator")
    private String specialRequirementEvaluator;

    @ApiModelProperty(value = "特殊需求日期", name = "specialRequirementTime")
    private Date specialRequirementTime;

    @ApiModelProperty(value = "评估小组意见", name = "assessmentTeamSuggest")
    private String assessmentTeamSuggest;

    @ApiModelProperty(value = "评估小组签名", name = "assessmentTeamSign")
    private String assessmentTeamSign;

    @ApiModelProperty(value = "评估小组签名日期", name = "teamSignTime")
    private Date teamSignTime;

    @ApiModelProperty(value = "院长意见", name = "deanSuggest")
    private String deanSuggest;

    @ApiModelProperty(value = "院长签名", name = "deanSign")
    private String deanSign;

    @ApiModelProperty(value = "院长签名时间", name = "deanSignTime")
    private Date deanSignTime;

    @ApiModelProperty(value = "家属和老人意见", name = "patientSuggest")
    private String patientSuggest;

    @ApiModelProperty(value = "家属和老人签字", name = "patientSign")
    private String patientSign;

    @ApiModelProperty(value = "家属和老人签字日期", name = "patientSignTime")
    private Date patientSignTime;

    @ApiModelProperty(value = "楼宇", name = "buildingCode")
    private String buildingCode;

    @ApiModelProperty(value = "老人档案id", name = "archiveId")
    private Long archiveId;
}
