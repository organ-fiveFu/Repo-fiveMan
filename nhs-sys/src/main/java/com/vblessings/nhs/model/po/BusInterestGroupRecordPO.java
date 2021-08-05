package com.vblessings.nhs.model.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class BusInterestGroupRecordPO {
    /**
     * 小组活动
     */
    @ApiModelProperty(value = "小组活动", required = false)
    @Column(name = "group_activity")
    private String groupActivity;


    /**
     * 活动开始时间
     */
    @ApiModelProperty(value = "活动开始时间", required = false)
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 活动结束时间
     */
    @ApiModelProperty(value = "活动结束时间", required = false)
    @Column(name = "end_time")
    private Date endTime;

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
}
