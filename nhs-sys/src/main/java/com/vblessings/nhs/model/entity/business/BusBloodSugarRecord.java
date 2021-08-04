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
 * BusBloodSugarRecord
 *
 * @author linxiazhu-auto-build
 * @since  2021-08-02 09:24:02
 */
@Data
@Table(name = "bus_blood_sugar_record")
public class BusBloodSugarRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	
		/**
	 * 
	 */
	@ApiModelProperty(value = "", required = false)
    @Id
    private Long id;
	
		/**
	 * 住院号
	 */
	@ApiModelProperty(value = "住院号", required = false)
    @Column(name = "business_no")
    private String businessNo;
	
		/**
	 * 病人姓名
	 */
	@ApiModelProperty(value = "病人姓名", required = false)
    @Column(name = "patient_name")
    private String patientName;
	
		/**
	 * 床号
	 */
	@ApiModelProperty(value = "床号", required = false)
    @Column(name = "bed_name")
    private String bedName;
	
		/**
	 * 医院诊断
	 */
	@ApiModelProperty(value = "医院诊断", required = false)
    @Column(name = "hospital_diagnosis")
    private String hospitalDiagnosis;
	
		/**
	 * 日期
	 */
	@ApiModelProperty(value = "日期", required = false)
    @Column(name = "blood_sugar_record_date")
    private Date bloodSugarRecordDate;
	
		/**
	 * 采样状态
	 */
	@ApiModelProperty(value = "采样状态", required = false)
    @Column(name = "sampling_status")
    private String samplingStatus;
	
		/**
	 * 采样时间
	 */
	@ApiModelProperty(value = "采样时间", required = false)
    @Column(name = "sampling_time")
    private Date samplingTime;
	
		/**
	 * 采样签名
	 */
	@ApiModelProperty(value = "采样签名", required = false)
    @Column(name = "sampling_signature")
    private String samplingSignature;

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
	 * 血糖值
	 */
	@ApiModelProperty(value = "血糖值", required = false)
	@Column(name = "blood_glucose_value")
	private String bloodGlucoseValue;
	
}
