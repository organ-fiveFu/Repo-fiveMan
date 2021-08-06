package com.vblessings.nhs.model.po.trialassessment;

import com.vblessings.nhs.model.entity.assessment.EvaluationContent;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@ApiModel(value = "入院评估信息更新入参")
@Data
public class AdmissionAssessmentUpdatePO {

    @ApiModelProperty(value = "主键", name = "id")
    private Long id;

    @ApiModelProperty(name="businessNo", value = "住院号")
    private String businessNo;

    @ApiModelProperty(name="archiveId", value = "档案id")
    private Long archiveId;

    @ApiModelProperty(name="name", value = "老人姓名")
    private String name;

    @ApiModelProperty(name="sex", value = "老人性别")
    private String sex;

    @ApiModelProperty(name="age", value = "老人年龄")
    private Integer age;

    @ApiModelProperty(name="bedName", value = "床号")
    private String bedName;

    @ApiModelProperty(name="admissionTime", value = "入院时间")
    private Date admissionTime;

    @ApiModelProperty(name="floorName", value = "楼层")
    private String floorName;

    @ApiModelProperty(name="health", value = "健康状态")
    private String health;

    @ApiModelProperty(name="hearingItems", value = "听力判断")
    private List<EvaluationContent> hearingItems;

    @ApiModelProperty(name="hearingScore", value = "听力得分")
    private Integer hearingScore;

    @ApiModelProperty(name="hearingDegree", value = "听力程度")
    private Integer hearingDegree;

    @ApiModelProperty(name="visionItems", value = "视力判断")
    private List<EvaluationContent> visionItems;

    @ApiModelProperty(name="visionScore", value = "视力判断")
    private Integer visionScore;

    @ApiModelProperty(name="visionDegree", value = "视力程度")
    private Integer visionDegree;

    @ApiModelProperty(name="pressureSoresItems", value = "压疮判断")
    private List<EvaluationContent> pressureSoresItems;

    @ApiModelProperty(name="pressureSoresScore", value = "压疮得分")
    private Integer pressureSoresScore;

    @ApiModelProperty(name="pressureSoresDegree", value = "压疮程度")
    private Integer pressureSoresDegree;

    @ApiModelProperty(name="chokeFeedItems", value = "噎食判断")
    private List<EvaluationContent> chokeFeedItems;

    @ApiModelProperty(name="chokeFeedScore", value = "噎食得分")
    private Integer chokeFeedScore;

    @ApiModelProperty(name="chokeFeedDegree", value = "噎食程度")
    private Integer chokeFeedDegree;

    @ApiModelProperty(name="communicationItems", value = "沟通判断")
    private List<EvaluationContent> communicationItems;

    @ApiModelProperty(name="communicationScore", value = "沟通得分")
    private Integer communicationScore;

    @ApiModelProperty(name="communicationDegree", value = "沟通程度")
    private Integer communicationDegree;

    @ApiModelProperty(name="fallItems", value = "跌倒判断")
    private List<EvaluationContent> fallItems;

    @ApiModelProperty(name="fallScore", value = "跌倒得分")
    private Integer fallScore;

    @ApiModelProperty(name="fallDegree", value = "跌倒程度")
    private Integer fallDegree;

    @ApiModelProperty(name="suicideItems", value = "自杀判断")
    private List<EvaluationContent> suicideItems;

    @ApiModelProperty(name="suicideScore", value = "自杀得分")
    private Integer suicideScore;

    @ApiModelProperty(name="suicideDegree", value = "自杀程度")
    private Integer suicideDegree;

    @ApiModelProperty(name="runAwayItems", value = "出走判断")
    private List<EvaluationContent> runAwayItems;

    @ApiModelProperty(name="runAwayScore", value = "出走得分")
    private Integer runAwayScore;

    @ApiModelProperty(name="runAwayDegree", value = "出走程度")
    private Integer runAwayDegree;
}
