package com.nurse.healthy.model.entity.bed;

import lombok.Data;
import sun.awt.SunHints;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Copyright (c) 2017 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * SysRoomInfo
 *
 * @author linxiazhu-auto-build
 * @since  2021-06-27 14:46:13
 */
@Data
@Table(name = "sys_room_info")
public class SysRoomInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
		/**
	 * 
	 */
	@ApiModelProperty(value = "", required = false)
    @Id
    private Long id;
	
		/**
	 * 房间编号
	 */
	@ApiModelProperty(value = "房间编号", required = false)
    @Column(name = "room_code")
    private String roomCode;
	
		/**
	 * 楼宇编号
	 */
	@ApiModelProperty(value = "楼宇编号", required = false)
    @Column(name = "building_code")
    private String buildingCode;
	
		/**
	 * 楼层编号
	 */
	@ApiModelProperty(value = "楼层编号", required = false)
    @Column(name = "floor_code")
    private String floorCode;
	
		/**
	 * 房间名称
	 */
	@ApiModelProperty(value = "房间名称", required = false)
    @Column(name = "name")
    private String name;
	
		/**
	 * 房间类型
	 */
	@ApiModelProperty(value = "房间类型", required = false)
    @Column(name = "room_type")
    private String roomType;
	
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
	 * 房间朝向
	 */
	@ApiModelProperty(value = "房间朝向", required = false)
	@Column(name = "room_toward")
	private String roomToward;
}
