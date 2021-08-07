package com.vblessings.nhs.model.entity.business;

import com.vblessings.nhs.model.typehandler.ListStringHandler;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import tk.mybatis.mapper.annotation.ColumnType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * Copyright (c) 2017 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * BusMenuInfo
 *
 * @author zjy-auto-build
 * @since  2021-08-07 14:42:19
 */
@Data
@Table(name = "bus_menu_info")
public class BusMenuInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
		/**
	 * 
	 */
	@ApiModelProperty(value = "", required = false)
    @Id
    private Long id;
	
		/**
	 * 早餐——主食
	 */
	@ApiModelProperty(value = "早餐——主食", required = false)
    @Column(name = "breakfast_staple_food")
	@ColumnType(typeHandler = ListStringHandler.class)
    private List<String> breakfastStapleFood;
	
		/**
	 * 早餐——菜单
	 */
	@ApiModelProperty(value = "早餐——菜单", required = false)
    @Column(name = "breakfast_menu")
	@ColumnType(typeHandler = ListStringHandler.class)
    private List<String> breakfastMenu;
	
		/**
	 * 午餐——主食
	 */
	@ApiModelProperty(value = "午餐——主食", required = false)
    @Column(name = "lunch_staple_food")
	@ColumnType(typeHandler = ListStringHandler.class)
    private List<String> lunchStapleFood;
	
		/**
	 * 午餐——菜单
	 */
	@ApiModelProperty(value = "午餐——菜单", required = false)
    @Column(name = "lunch_menu")
	@ColumnType(typeHandler = ListStringHandler.class)
    private List<String> lunchMenu;
	
		/**
	 * 晚餐——主食
	 */
	@ApiModelProperty(value = "晚餐——主食", required = false)
    @Column(name = "dinner_staple_food")
	@ColumnType(typeHandler = ListStringHandler.class)
    private List<String> dinnerStapleFood;
	
		/**
	 * 晚餐——菜单
	 */
	@ApiModelProperty(value = "晚餐——菜单", required = false)
    @Column(name = "dinner_meun")
	@ColumnType(typeHandler = ListStringHandler.class)
    private List<String> dinnerMeun;
	
		/**
	 * 星期
	 */
	@ApiModelProperty(value = "星期", required = false)
    @Column(name = "date")
    private Date date;
	
		/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人", required = false)
    @Column(name = "creator_id")
    private Long creatorId;
	
		/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间", required = false)
    @Column(name = "create_time")
    private Date createTime;
	
		/**
	 * 更新人
	 */
	@ApiModelProperty(value = "更新人", required = false)
    @Column(name = "updater_id")
    private Long updaterId;
	
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
