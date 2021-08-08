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
 * BusContract
 *
 * @author linxiazhu-auto-build
 * @since  2021-08-08 16:55:23
 */
@Data
@Table(name = "bus_contract")
public class BusContract implements Serializable {
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
	 * 性别
	 */
	@ApiModelProperty(name="sex", value = "性别")
    @Column(name = "sex")
    private String sex;
	
		/**
	 * 年龄
	 */
	@ApiModelProperty(name="age", value = "年龄")
    @Column(name = "age")
    private Integer age;
	
		/**
	 * 负责人
	 */
	@ApiModelProperty(name="director", value = "负责人")
    @Column(name = "director")
    private String director;
	
		/**
	 * 负责人签订时间
	 */
	@ApiModelProperty(name="directorTime", value = "负责人签订时间")
    @Column(name = "director_time")
    private Date directorTime;
	
		/**
	 * 监护人
	 */
	@ApiModelProperty(name="guardian", value = "监护人")
    @Column(name = "guardian")
    private String guardian;
	
		/**
	 * 监护人签订时间
	 */
	@ApiModelProperty(name="guardianTime", value = "监护人签订时间")
    @Column(name = "guardian_time")
    private Date guardianTime;
	
		/**
	 * 合同签订状态
	 */
	@ApiModelProperty(name="isSignContract", value = "合同签订状态")
    @Column(name = "is_sign_contract")
    private String isSignContract;
	
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
