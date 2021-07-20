package com.nurse.healthy.model.entity.business;

import lombok.Data;
import sun.awt.SunHints;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "bus_complaints_record")
public class BusComplaintsRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "", required = false)
    @Id
    private Long id;

    /**
     * 投诉日期
     */
    @ApiModelProperty(value = "投诉日期", required = false)
    @Column(name = "complaint_date")
    private Date complaintDate;

    /**
     * 投诉人员
     */
    @ApiModelProperty(value = "投诉人员", required = false)
    @Column(name = "complaint_people")
    private String complaintPeople;

    /**
     * 缺席人员
     */
    @ApiModelProperty(value = "缺席人员", required = false)
    @Column(name = "absentee")
    private String absentee;

    /**
     * 投诉主题
     */
    @ApiModelProperty(value = "投诉主题", required = false)
    @Column(name = "theme")
    private String theme;

    /**
     * 主持人
     */
    @ApiModelProperty(value = "主持人", required = false)
    @Column(name = "host")
    private String host;

    /**
     * 意见和建议
     */
    @ApiModelProperty(value = "意见和建议", required = false)
    @Column(name = "suggestion")
    private String suggestion;

    /**
     * 处理和措施
     */
    @ApiModelProperty(value = "处理和措施", required = false)
    @Column(name = "treatment")
    private String treatment;

    /**
     * 落实情况
     */
    @ApiModelProperty(value = "落实情况", required = false)
    @Column(name = "workable")
    private String workable;

    /**
     * 责任人
     */
    @ApiModelProperty(value = "责任人", required = false)
    @Column(name = "person_in_charge")
    private String person_in_charge;

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

}