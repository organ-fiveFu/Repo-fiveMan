package com.vblessings.nhs.model.entity.business;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


@Data
@Table(name = "bus_interest_group_record")
public class BusInterestGroupRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "", required = false)
    @Id
    private Long id;

    /**
     * 小组活动
     */
    @ApiModelProperty(value = "小组活动", required = false)
    @Column(name = "group_activity")
    private String groupActivity;

    /**
     * 活动时期
     */
    @ApiModelProperty(value = "活动时期", required = false)
    @Column(name = "activity_date")
    private Date activityDate;

    /**
     * 活动开始时间
     */
    @ApiModelProperty(value = "活动开始时间", required = false)
    @Column(name = "start_time")
    private String startTime;

    /**
     * 活动结束时间
     */
    @ApiModelProperty(value = "活动结束时间", required = false)
    @Column(name = "end_time")
    private String endTime;

    /**
     * 活动地点
     */
    @ApiModelProperty(value = "活动地点", required = false)
    @Column(name = "activity_site")
    private String activitySite;

    /**
     * 活动人数
     */
    @ApiModelProperty(value = "活动人数", required = false)
    @Column(name = "number")
    private Integer number;

    /**
     * 活动内容
     */
    @ApiModelProperty(value = "活动内容", required = false)
    @Column(name = "active_content")
    private String activeContent;

    /**
     * 负责人签字
     */
    @ApiModelProperty(value = "负责人签字", required = false)
    @Column(name = "signature")
    private String signature;

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
    @ApiModelProperty(value = "是否删除", required = false)
    @Column(name = "is_del")
    private Integer isDel;

}
