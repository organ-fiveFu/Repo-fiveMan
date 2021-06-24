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
 * SysBuildingInfo
 *
 * @author linxiazhu-auto-build
 * @since  2021-06-24 17:18:13
 */
@Data
@Table(name = "sys_building_info")
public class SysBuildingInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
		/**
	 * 
	 */
	@ApiModelProperty(value = "", required = false)
    @Id
    private Long id;
	
		/**
	 * 楼宇编号
	 */
	@ApiModelProperty(value = "楼宇编号", required = false)
    @Column(name = "building_code")
    private String buildingCode;
	
		/**
	 * 楼宇名称
	 */
	@ApiModelProperty(value = "楼宇名称", required = false)
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
