package com.vblessings.nhs.model.vo.comprehensive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "行政查房查询出参")
public class AdministrativeQueryVO {

    @ApiModelProperty(value = "id", required = false)
    private Long id;

    @ApiModelProperty(value = "查房人", required = false)
    private String wardRounds;

    @ApiModelProperty(value = "查房时间", required = false)
    private String wardRoundTime;

    @ApiModelProperty(value = "生活照料情况", required = false)
    private String livingConditions;

    @ApiModelProperty(value = "生活照料意见", required = false)
    private String lifeOpinion;

    @ApiModelProperty(value = "心理护理情况", required = false)
    private String psychologicalSituation;

    @ApiModelProperty(value = "心理护理意见", required = false)
    private String psychologicalOpinion;

    @ApiModelProperty(value = "后勤保障情况", required = false)
    private String logisticsSituation;

    @ApiModelProperty(value = "后勤保障意见", required = false)
    private String logisticsOpinion;

    @ApiModelProperty(value = "安全隐患情况", required = false)
    private String securitySituation;

    @ApiModelProperty(value = "安全隐患意见", required = false)
    private String safetyAdvice;

    @ApiModelProperty(value = "环境卫生及其他情况", required = false)
    private String environmentalConditions;

    @ApiModelProperty(value = "环境卫生及其他意见", required = false)
    private String environmentalOpinion;

    @ApiModelProperty(value = "意见或建议情况", required = false)
    private String opinionSituation;

    @ApiModelProperty(value = "意见或建立意见", required = false)
    private String opinion;
}
