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
 * BusChangeShifts
 *
 * @author linxiazhu-auto-build
 * @since  2021-08-05 09:58:55
 */
@Data
@Table(name = "bus_change_shifts")
public class BusChangeShifts implements Serializable {
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = false)
    @Id
    private Long id;
	
		/**
	 * 交接员
	 */
	@ApiModelProperty(value = "交班员", required = false)
    @Column(name = "handover_officer")
    private String handoverOfficer;
	
		/**
	 * 接班员
	 */
	@ApiModelProperty(value = "接班员", required = false)
    @Column(name = "successor")
    private String successor;
	
		/**
	 * 提交时间
	 */
	@ApiModelProperty(value = "提交时间", required = false)
    @Column(name = "submission_time")
    private Date submissionTime;
	
		/**
	 * 交班开始时间
	 */
	@ApiModelProperty(value = "交班开始时间", required = false)
    @Column(name = "shift_handover_start_time")
    private Date shiftHandoverStartTime;
	
		/**
	 * 交班结束时间
	 */
	@ApiModelProperty(value = "交班结束时间", required = false)
    @Column(name = "shift_handover_end_time")
    private Date shiftHandoverEndTime;
	
		/**
	 * 交班事宜
	 */
	@ApiModelProperty(value = "交班事宜", required = false)
    @Column(name = "shift_handover")
    private String shiftHandover;
	
		/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注", required = false)
    @Column(name = "remark")
    private String remark;
	
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
	 * 删除标志
	 */
	@ApiModelProperty(value = "删除标志", required = false)
    @Column(name = "is_del")
    private Integer isDel;
	
}
