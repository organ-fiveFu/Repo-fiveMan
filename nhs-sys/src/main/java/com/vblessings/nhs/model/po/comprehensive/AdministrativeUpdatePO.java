package com.vblessings.nhs.model.po.comprehensive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "行政查房更新入参")
@Data
public class AdministrativeUpdatePO {

    @ApiModelProperty(value = "id", name = "id")
    private Long id;

    @ApiModelProperty(value = "查房人", name = "wardRounds")
    private String wardRounds;

    @ApiModelProperty(value = "查房时间", name = "wardRoundTime")
    private Date wardRoundTime;

    @ApiModelProperty(value = "生活照料情况", name = "livingConditions")
    private String livingConditions;

    @ApiModelProperty(value = "生活照料意见", name = "lifeOpinion")
    private String lifeOpinion;

    @ApiModelProperty(value = "心理护理情况", name = "psychologicalSituation")
    private String psychologicalSituation;

    @ApiModelProperty(value = "心理护理意见", name = "psychologicalOpinion")
    private String psychologicalOpinion;

    @ApiModelProperty(value = "后勤保障情况", name = "logisticsSituation")
    private String logisticsSituation;

    @ApiModelProperty(value = "后勤保障意见", name = "logisticsOpinion")
    private String logisticsOpinion;

    @ApiModelProperty(value = "安全隐患情况", name = "securitySituation")
    private String securitySituation;

    @ApiModelProperty(value = "安全隐患意见", name = "safetyAdvice")
    private String safetyAdvice;

    @ApiModelProperty(value = "环境卫生及其他情况", name = "environmentalConditions")
    private String environmentalConditions;

    @ApiModelProperty(value = "环境卫生及其他意见", name = "environmentalOpinion")
    private String environmentalOpinion;

    @ApiModelProperty(value = "意见或建议情况", name = "opinionSituation")
    private String opinionSituation;

    @ApiModelProperty(value = "意见或建立意见", name = "opinion")
    private String opinion;
}
