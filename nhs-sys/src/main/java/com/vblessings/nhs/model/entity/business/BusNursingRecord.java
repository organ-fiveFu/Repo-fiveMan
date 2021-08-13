package com.vblessings.nhs.model.entity.business;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Copyright (c) 2017 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * BusNursingRecord
 *
 * @author zjy-auto-build
 * @since  2021-08-13 00:53:15
 */
@Data
@Table(name = "bus_nursing_record")
public class BusNursingRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	
		/**
	 * id
	 */
    @Id
    private Long id;
	
		/**
	 * 住院号
	 */
	@ApiModelProperty(value = "住院号")
    @Column(name = "business_no")
    private String businessNo;
	
		/**
	 * 病人姓名
	 */
	@ApiModelProperty(value = "病人姓名")
    @Column(name = "patient_name")
    private String patientName;
	
		/**
	 * 是否洗头理发
	 */
	@ApiModelProperty(value = "是否洗头理发")
    @Column(name = "is_haircut")
    private String isHaircut;
	
		/**
	 * 是否修剪指甲
	 */
	@ApiModelProperty(value = "是否修剪指甲")
    @Column(name = "is_manicure")
    private String isManicure;
	
		/**
	 * 是否清洗便器
	 */
	@ApiModelProperty(value = "是否清洗便器")
    @Column(name = "is_clean_toilet")
    private String isCleanToilet;
	
		/**
	 * 是否晾晒衣服
	 */
	@ApiModelProperty(value = "是否晾晒衣服")
    @Column(name = "is_hang_clothes")
    private String isHangClothes;
	
		/**
	 * 是否打扫房间
	 */
	@ApiModelProperty(value = "是否打扫房间")
    @Column(name = "is_clean_room")
    private String isCleanRoom;
	
		/**
	 * 是否进餐送餐
	 */
	@ApiModelProperty(value = "是否进餐送餐")
    @Column(name = "is_meals")
    private String isMeals;
	
		/**
	 * 是否送开水
	 */
	@ApiModelProperty(value = "是否送开水")
    @Column(name = "is_wash_gargle")
    private String isWashGargle;
	
		/**
	 * 身心状况观察处理
	 */
	@ApiModelProperty(value = "身心状况观察处理")
    @Column(name = "physical_and_mental_status")
    private String physicalAndMentalStatus;
	
		/**
	 * 其他
	 */
	@ApiModelProperty(value = "其他")
    @Column(name = "other")
    private String other;

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
	private Integer isDel;
}
