package com.vblessings.nhs.model.vo.trialassessment;

import com.vblessings.nhs.model.entity.assessment.EvaluationContent;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "入院评估信息查询出参")
@Data
public class AdmissionAssessmentQueryVO {

    @ApiModelProperty(value = "主键", required = false)
    private Long id;

    @ApiModelProperty(value = "住院号", required = false)
    private String businessNo;

    @ApiModelProperty(value = "档案id", required = false)
    private Long archiveId;

    @ApiModelProperty(value = "老人姓名", required = false)
    private String name;

    @ApiModelProperty(value = "老人性别", required = false)
    private String sex;

    @ApiModelProperty(value = "老人年龄", required = false)
    private Integer age;

    @ApiModelProperty(value = "床号", required = false)
    private String bedName;

    @ApiModelProperty(value = "入院时间", required = false)
    private String admissionTime;

    @ApiModelProperty(value = "楼层", required = false)
    private String floorName;

    @ApiModelProperty(value = "健康状态", required = false)
    private String health;

    @ApiModelProperty(value = "听力判断", required = false)
    private List<EvaluationContent> hearingItems;

    @ApiModelProperty(value = "听力得分", required = false)
    private Integer hearingScore;

    @ApiModelProperty(value = "听力程度", required = false)
    private Integer hearingDegree;

    @ApiModelProperty(value = "视力判断", required = false)
    private List<EvaluationContent> visionItems;

    @ApiModelProperty(value = "视力判断", required = false)
    private Integer visionScore;

    @ApiModelProperty(value = "视力程度", required = false)
    private Integer visionDegree;

    @ApiModelProperty(value = "压疮判断", required = false)
    private List<EvaluationContent> pressureSoresItems;

    @ApiModelProperty(value = "压疮得分", required = false)
    private Integer pressureSoresScore;

    @ApiModelProperty(value = "压疮程度", required = false)
    private Integer pressureSoresDegree;

    @ApiModelProperty(value = "噎食判断", required = false)
    private List<EvaluationContent> chokeFeedItems;

    @ApiModelProperty(value = "噎食得分", required = false)
    private Integer chokeFeedScore;

    @ApiModelProperty(value = "噎食程度", required = false)
    private Integer chokeFeedDegree;

    @ApiModelProperty(value = "沟通判断", required = false)
    private List<EvaluationContent> communicationItems;

    @ApiModelProperty(value = "沟通得分", required = false)
    private Integer communicationScore;

    @ApiModelProperty(value = "沟通程度", required = false)
    private Integer communicationDegree;

    @ApiModelProperty(value = "跌倒判断", required = false)
    private List<EvaluationContent> fallItems;

    @ApiModelProperty(value = "跌倒得分", required = false)
    private Integer fallScore;

    @ApiModelProperty(value = "跌倒程度", required = false)
    private Integer fallDegree;

    @ApiModelProperty(value = "自杀判断", required = false)
    private List<EvaluationContent> suicideItems;

    @ApiModelProperty(value = "自杀得分", required = false)
    private Integer suicideScore;

    @ApiModelProperty(value = "自杀程度", required = false)
    private Integer suicideDegree;

    @ApiModelProperty(value = "出走判断", required = false)
    private List<EvaluationContent> runAwayItems;

    @ApiModelProperty(value = "出走得分", required = false)
    private Integer runAwayScore;

    @ApiModelProperty(value = "出走程度", required = false)
    private Integer runAwayDegree;
}
