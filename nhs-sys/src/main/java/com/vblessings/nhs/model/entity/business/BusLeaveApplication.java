package com.vblessings.nhs.model.entity.business;

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
 * BusLeaveApplication
 *
 * @author linxiazhu-auto-build
 * @since  2021-08-06 17:16:34
 */
@Data
@Table(name = "bus_leave_application")
public class BusLeaveApplication implements Serializable {
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@ApiModelProperty(name="id",value = "主键")
    @Id
    private Long id;
	
		/**
	 * 住院号
	 */
	@ApiModelProperty(name="businessNo", value = "住院号")
    @Column(name = "business_no")
    private String businessNo;
	
		/**
	 * 姓名
	 */
	@ApiModelProperty(name="name", value = "姓名")
    @Column(name = "name")
    private String name;
	
		/**
	 * 请假开始日期
	 */
	@ApiModelProperty(name="leaveStartTime", value = "请假开始日期")
    @Column(name = "leave_start_time")
    private Date leaveStartTime;
	
		/**
	 * 请假结束日期
	 */
	@ApiModelProperty(name="leaveEndTime", value = "请假结束日期")
    @Column(name = "leave_end_time")
    private Date leaveEndTime;
	
		/**
	 * 请假原因
	 */
	@ApiModelProperty(name="reasonForLeave", value = "请假原因")
    @Column(name = "reason_for_leave")
    private String reasonForLeave;
	
		/**
	 * 监护人
	 */
	@ApiModelProperty(name="guardian", value = "监护人")
    @Column(name = "guardian")
    private String guardian;
	
		/**
	 * 医院负责人
	 */
	@ApiModelProperty(name="hospitalDirector", value = "医院负责人")
    @Column(name = "hospital_director")
    private String hospitalDirector;
	
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
	 * 删除标志
	 */
	@ApiModelProperty(name="isDel", value = "删除标志")
    @Column(name = "is_del")
    private Integer isDel;
	
}
