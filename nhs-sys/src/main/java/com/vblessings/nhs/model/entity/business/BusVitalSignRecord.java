package com.vblessings.nhs.model.entity.business;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 * Copyright (c) 2017 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * BusVitalSignRecord
 *
 * @author zjy-auto-build
 * @since  2021-08-14 02:09:19
 */
@Data
@Table(name = "bus_vital_sign_record")
public class BusVitalSignRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	
		/**
	 * 
	 */
	@ApiModelProperty(value = "")
    @Id
    private Long id;
	
		/**
	 * 老人档案id
	 */
	@ApiModelProperty(value = "老人档案id")
    @Column(name = "archive_id")
    private Long archiveId;

	/**
	 * 病人姓名
	 */
	@ApiModelProperty(value = "病人姓名")
	@Column(name = "name")
	private String name;

		/**
	 * 住院号
	 */
	@ApiModelProperty(value = "住院号")
    @Column(name = "business_no")
    private String businessNo;
	
		/**
	 * 记录时间
	 */
	@ApiModelProperty(value = "记录时间")
    @Column(name = "record_time")
    private Date recordTime;

	/**
	 * 记录时间节点
	 */
	@ApiModelProperty(value = "记录时间节点")
	@Column(name = "time_point")
	private String timePoint;
	
		/**
	 * 体温
	 */
	@ApiModelProperty(value = "体温")
    @Column(name = "temperature")
    private BigDecimal temperature;
	
		/**
	 * 脉搏
	 */
	@ApiModelProperty(value = "脉搏")
    @Column(name = "pulse")
    private Integer pulse;
	
		/**
	 * 呼吸
	 */
	@ApiModelProperty(value = "呼吸")
    @Column(name = "breathing")
    private Integer breathing;
	
		/**
	 * 血压低值
	 */
	@ApiModelProperty(value = "血压低值")
    @Column(name = "low_blood_pressure")
    private Integer lowBloodPressure;
	
		/**
	 * 血压高值
	 */
	@ApiModelProperty(value = "血压高值")
    @Column(name = "high_blood_pressure")
    private Integer highBloodPressure;
	
		/**
	 * 入量
	 */
	@ApiModelProperty(value = "入量")
    @Column(name = "intake")
    private BigDecimal intake;
	
		/**
	 * 出量
	 */
	@ApiModelProperty(value = "出量")
    @Column(name = "output")
    private BigDecimal output;
	
		/**
	 * 尿量
	 */
	@ApiModelProperty(value = "尿量")
    @Column(name = "urine")
    private BigDecimal urine;

	/**
	 * 排便
	 */
	@ApiModelProperty(value = "排便")
	@Column(name = "defecate")
	private Integer defecate;
	
		/**
	 * 体重
	 */
	@ApiModelProperty(value = "体重")
    @Column(name = "weight")
    private BigDecimal weight;
	
		/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人")
    @Column(name = "creator_id")
    private Long creatorId;
	
		/**
	 * 更新人
	 */
	@ApiModelProperty(value = "更新人")
    @Column(name = "updater_id")
    private Long updaterId;
	
		/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
    @Column(name = "create_time")
    private Date createTime;
	
		/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
    @Column(name = "update_time")
    private Date updateTime;
	
		/**
	 * 是否启用
	 */
	@ApiModelProperty(value = "是否启用")
    @Column(name = "is_del")
    private Integer isDel = 0;

	/**
	 * 血氧饱和度
	 */
	@ApiModelProperty(value = "血氧饱和度")
	@Column(name = "blood_oxygen")
	private Integer bloodOxygen;
	
}
