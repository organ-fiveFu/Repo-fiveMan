package com.vblessings.nhs.model.entity.bed;

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
 * SysFloorInfo
 *
 * @author linxiazhu-auto-build
 * @since  2021-06-24 17:18:13
 */
@Data
@Table(name = "sys_floor_info")
public class SysFloorInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
		/**
	 * 
	 */
	@ApiModelProperty(value = "", required = false)
    @Id
    private Long id;
	
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
	 * 楼层名称
	 */
	@ApiModelProperty(value = "楼层名称", required = false)
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
	
}
