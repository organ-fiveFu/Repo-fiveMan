package com.vblessings.nhs.model.entity.assessment;

import com.vblessings.nhs.model.typehandler.EvaluationContentHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Copyright (c) 2017 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * BusAdmissionAssessment
 *
 * @author linxiazhu-auto-build
 * @since  2021-08-06 13:41:49
 */
@Data
@Table(name = "bus_admission_assessment")
public class BusAdmissionAssessment implements Serializable {
	private static final long serialVersionUID = 1L;
	
		/**
	 * 
	 */
	@ApiModelProperty(name="id",value = "")
    @Id
    private Long id;


	/**
	 * 住院号
	 */
	@ApiModelProperty(name="businessNo", value = "住院号")
	@Column(name = "business_no")
	private String businessNo;
	
		/**
	 * 档案id
	 */
	@ApiModelProperty(name="archiveId", value = "档案id")
    @Column(name = "archive_id")
    private Long archiveId;
	
		/**
	 * 老人姓名
	 */
	@ApiModelProperty(name="name", value = "老人姓名")
    @Column(name = "name")
    private String name;
	
		/**
	 * 老人性别
	 */
	@ApiModelProperty(name="sex", value = "老人性别")
    @Column(name = "sex")
    private String sex;
	
		/**
	 * 老人年龄
	 */
	@ApiModelProperty(name="age", value = "老人年龄")
    @Column(name = "age")
    private Integer age;
	
		/**
	 * 床号
	 */
	@ApiModelProperty(name="bedName", value = "床号")
    @Column(name = "bed_name")
    private String bedName;
	
		/**
	 * 入院时间
	 */
	@ApiModelProperty(name="admissionTime", value = "入院时间")
    @Column(name = "admission_time")
    private Date admissionTime;
	
		/**
	 * 楼层
	 */
	@ApiModelProperty(name="floorName", value = "楼层")
    @Column(name = "floor_name")
    private String floorName;
	
		/**
	 * 健康状态
	 */
	@ApiModelProperty(name="health", value = "健康状态")
    @Column(name = "health")
    private String health;
	
		/**
	 * 听力判断
	 */
	@ApiModelProperty(name="hearingItems", value = "听力判断")
    @Column(name = "hearing_items")
	@ColumnType(typeHandler = EvaluationContentHandler.class)
    private List<EvaluationContent> hearingItems;
	
		/**
	 * 听力得分
	 */
	@ApiModelProperty(name="hearingScore", value = "听力得分")
    @Column(name = "hearing_score")
    private Integer hearingScore;
	
		/**
	 * 听力程度
	 */
	@ApiModelProperty(name="hearingDegree", value = "听力程度")
    @Column(name = "hearing_degree")
    private Integer hearingDegree;
	
		/**
	 * 视力判断
	 */
	@ApiModelProperty(name="visionItems", value = "视力判断")
    @Column(name = "vision_items")
	@ColumnType(typeHandler = EvaluationContentHandler.class)
    private List<EvaluationContent> visionItems;
	
		/**
	 * 视力判断
	 */
	@ApiModelProperty(name="visionScore", value = "视力判断")
    @Column(name = "vision_score")
    private Integer visionScore;
	
		/**
	 * 视力程度
	 */
	@ApiModelProperty(name="visionDegree", value = "视力程度")
    @Column(name = "vision_degree")
    private Integer visionDegree;
	
		/**
	 * 压疮判断
	 */
	@ApiModelProperty(name="pressureSoresItems", value = "压疮判断")
    @Column(name = "pressure_sores_items")
	@ColumnType(typeHandler = EvaluationContentHandler.class)
    private List<EvaluationContent> pressureSoresItems;
	
		/**
	 * 压疮得分
	 */
	@ApiModelProperty(name="pressureSoresScore", value = "压疮得分")
    @Column(name = "pressure_sores_score")
    private Integer pressureSoresScore;
	
		/**
	 * 压疮程度
	 */
	@ApiModelProperty(name="pressureSoresDegree", value = "压疮程度")
    @Column(name = "pressure_sores_degree")
    private Integer pressureSoresDegree;
	
		/**
	 * 噎食判断
	 */
	@ApiModelProperty(name="chokeFeedItems", value = "噎食判断")
    @Column(name = "choke_feed_items")
	@ColumnType(typeHandler = EvaluationContentHandler.class)
    private List<EvaluationContent> chokeFeedItems;
	
		/**
	 * 噎食得分
	 */
	@ApiModelProperty(name="chokeFeedScore", value = "噎食得分")
    @Column(name = "choke_feed_score")
    private Integer chokeFeedScore;
	
		/**
	 * 噎食程度
	 */
	@ApiModelProperty(name="chokeFeedDegree", value = "噎食程度")
    @Column(name = "choke_feed_degree")
    private Integer chokeFeedDegree;
	
		/**
	 * 沟通判断
	 */
	@ApiModelProperty(name="communicationItems", value = "沟通判断")
    @Column(name = "communication_items")
	@ColumnType(typeHandler = EvaluationContentHandler.class)
    private List<EvaluationContent> communicationItems;
	
		/**
	 * 沟通得分
	 */
	@ApiModelProperty(name="communicationScore", value = "沟通得分")
    @Column(name = "communication_score")
    private Integer communicationScore;
	
		/**
	 * 沟通程度
	 */
	@ApiModelProperty(name="communicationDegree", value = "沟通程度")
    @Column(name = "communication_degree")
    private Integer communicationDegree;
	
		/**
	 * 跌倒判断
	 */
	@ApiModelProperty(name="fallItems", value = "跌倒判断")
    @Column(name = "fall_items")
	@ColumnType(typeHandler = EvaluationContentHandler.class)
    private List<EvaluationContent> fallItems;
	
		/**
	 * 跌倒得分
	 */
	@ApiModelProperty(name="fallScore", value = "跌倒得分")
    @Column(name = "fall_score")
    private Integer fallScore;
	
		/**
	 * 跌倒程度
	 */
	@ApiModelProperty(name="fallDegree", value = "跌倒程度")
    @Column(name = "fall_degree")
    private Integer fallDegree;
	
		/**
	 * 自杀判断
	 */
	@ApiModelProperty(name="suicideItems", value = "自杀判断")
    @Column(name = "suicide_items")
	@ColumnType(typeHandler = EvaluationContentHandler.class)
    private List<EvaluationContent> suicideItems;
	
		/**
	 * 自杀得分
	 */
	@ApiModelProperty(name="suicideScore", value = "自杀得分")
    @Column(name = "suicide_score")
    private Integer suicideScore;
	
		/**
	 * 自杀程度
	 */
	@ApiModelProperty(name="suicideDegree", value = "自杀程度")
    @Column(name = "suicide_degree")
    private Integer suicideDegree;
	
		/**
	 * 出走判断
	 */
	@ApiModelProperty(name="runAwayItems", value = "出走判断")
    @Column(name = "run_away_items")
	@ColumnType(typeHandler = EvaluationContentHandler.class)
    private List<EvaluationContent> runAwayItems;
	
		/**
	 * 出走得分
	 */
	@ApiModelProperty(name="runAwayScore", value = "出走得分")
    @Column(name = "run_away_score")
    private Integer runAwayScore;
	
		/**
	 * 出走程度
	 */
	@ApiModelProperty(name="runAwayDegree", value = "出走程度")
    @Column(name = "run_away_degree")
    private Integer runAwayDegree;
	
		/**
	 * 创建人
	 */
	@ApiModelProperty(name="creatorId", value = "创建人")
    @Column(name = "creator_id")
    private Long creatorId;
	
		/**
	 * 更新人
	 */
	@ApiModelProperty(name="updaterId", value = "更新人")
    @Column(name = "updater_id")
    private Long updaterId;
	
		/**
	 * 创建时间
	 */
	@ApiModelProperty(name="createTime", value = "创建时间")
    @Column(name = "create_time")
    private Date createTime;
	
		/**
	 * 更新时间
	 */
	@ApiModelProperty(name="updateTime", value = "更新时间")
    @Column(name = "update_time")
    private Date updateTime;
	
		/**
	 * 是否启用
	 */
	@ApiModelProperty(name="isDel", value = "是否启用")
    @Column(name = "is_del")
    private Integer isDel;
	
}
