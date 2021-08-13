package com.vblessings.nhs.model.po.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@ApiModel(value = "护理记录插入入参")
public class BusNursingRecordInsertPO implements Serializable {
	
		/**
	 * 住院号
	 */
	@ApiModelProperty(value = "住院号")
    private String businessNo;
	
		/**
	 * 病人姓名
	 */
	@ApiModelProperty(value = "病人姓名")
    private String patientName;
	
		/**
	 * 是否洗头理发
	 */
	@ApiModelProperty(value = "是否洗头理发")
    private String isHaircut;
	
		/**
	 * 是否修剪指甲
	 */
	@ApiModelProperty(value = "是否修剪指甲")
    private String isManicure;
	
		/**
	 * 是否清洗便器
	 */
	@ApiModelProperty(value = "是否清洗便器")
    private String isCleanToilet;
	
		/**
	 * 是否晾晒衣服
	 */
	@ApiModelProperty(value = "是否晾晒衣服")
    private String isHangClothes;
	
		/**
	 * 是否打扫房间
	 */
	@ApiModelProperty(value = "是否打扫房间")
    private String isCleanRoom;
	
		/**
	 * 是否进餐送餐
	 */
	@ApiModelProperty(value = "是否进餐送餐")
    private String isMeals;
	
		/**
	 * 是否送开水
	 */
	@ApiModelProperty(value = "是否送开水")
    private String isWashGargle;
	
		/**
	 * 身心状况观察处理
	 */
	@ApiModelProperty(value = "身心状况观察处理")
    private String physicalAndMentalStatus;
	
		/**
	 * 其他
	 */
	@ApiModelProperty(value = "其他")
    private String other;
	
}
