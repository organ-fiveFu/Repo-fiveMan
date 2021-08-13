package com.vblessings.nhs.model.entity.bed;

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
 * SysBedInfo
 *
 * @author linxiazhu-auto-build
 * @since  2021-06-24 17:18:13
 */
@Data
@Table(name = "sys_bed_info")
public class SysBedInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
		/**
	 * 
	 */
	@ApiModelProperty(value = "", required = false)
    @Id
    private Long id;
	
		/**
	 * 床位编号
	 */
	@ApiModelProperty(value = "床位编号", required = false)
    @Column(name = "bed_code")
    private String bedCode;
	
		/**
	 * 房间编号
	 */
	@ApiModelProperty(value = "房间编号", required = false)
    @Column(name = "room_code")
    private String roomCode;
	
		/**
	 * 楼层编号
	 */
	@ApiModelProperty(value = "楼层编号", required = false)
    @Column(name = "floor_code")
    private String floorCode;
	
		/**
	 * 楼宇编号
	 */
	@ApiModelProperty(value = "楼宇编号", required = false)
    @Column(name = "building_code")
    private String buildingCode;
	
		/**
	 * 床位名称
	 */
	@ApiModelProperty(value = "床位名称", required = false)
    @Column(name = "name")
    private String name;
	
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
	
		/**
	 * 入住状态
	 */
	@ApiModelProperty(value = "入住状态", required = false)
    @Column(name = "status")
    private String status;
	
		/**
	 * 查询时添加是否显示停用(0为停用 ，1为使用)
	 */
	@ApiModelProperty(value = "查询时添加是否显示停用(0为停用 ，1为使用)", required = false)
    @Column(name = "use_flag")
    private Integer useFlag;

	/**
	 * 楼宇名称
	 */
	@ApiModelProperty(value = "楼宇名称", required = false)
	@Column(name = "building_name")
	private String buildingName;

	/**
	 * 楼层名称
	 */
	@ApiModelProperty(value = "楼层名称", required = false)
	@Column(name = "floor_name")
	private String floorName;

	/**
	 * 房间名称
	 */
	@ApiModelProperty(value = "房间名称", required = false)
	@Column(name = "room_name")
	private String roomName;
	
}
