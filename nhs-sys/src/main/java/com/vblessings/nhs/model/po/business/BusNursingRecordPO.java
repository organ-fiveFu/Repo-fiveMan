package com.vblessings.nhs.model.po.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel(value = "护理记录插入入参")
public class BusNursingRecordPO implements Serializable {

	@ApiModelProperty(value = "主键")
	private Long id;

		/**
	 * 住院号
	 */
	@ApiModelProperty(value = "住院号")
    private String businessNo;
	
		/**
	 * 病人姓名
	 */
	@ApiModelProperty(value = "病人姓名")
    private String name;
	
		/**
	 * 是否洗头理发
	 */
	@ApiModelProperty(value = "是否洗头理发")
    private Boolean isHaircut;
	
		/**
	 * 是否修剪指甲
	 */
	@ApiModelProperty(value = "是否修剪指甲")
    private Boolean isManicure;
	
		/**
	 * 是否清洗便器
	 */
	@ApiModelProperty(value = "是否清洗便器")
    private Boolean isCleanToilet;
	
		/**
	 * 是否晾晒衣服
	 */
	@ApiModelProperty(value = "是否晾晒衣服")
    private Boolean isHangClothes;
	
		/**
	 * 是否打扫房间
	 */
	@ApiModelProperty(value = "是否打扫房间")
    private Boolean isCleanRoom;
	
		/**
	 * 是否进餐送餐
	 */
	@ApiModelProperty(value = "是否进餐送餐")
    private Boolean isMeals;
	
		/**
	 * 是否送开水
	 */
	@ApiModelProperty(value = "是否送开水")
    private Boolean isWashGargle;
	
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

	/**
	 * 记录日期
	 */
	@ApiModelProperty(value = "记录日期")
	private String recordTime;

	/**
	 * 记录时间点
	 */
	@ApiModelProperty(value = "记录时间点")
	private String timePoint;

	/**
	 * 体温
	 */
	@ApiModelProperty(value = "体温")
	private BigDecimal temperature;

	/**
	 * 脉搏
	 */
	@ApiModelProperty(value = "脉搏")
	private Integer pulse;

	/**
	 * 呼吸
	 */
	@ApiModelProperty(value = "呼吸")
	private Integer breathing;

	/**
	 * 血压低值
	 */
	@ApiModelProperty(value = "血压低值")
	private Integer lowBloodPressure;

	/**
	 * 血压高值
	 */
	@ApiModelProperty(value = "血压高值")
	private Integer highBloodPressure;

	/**
	 * 入量
	 */
	@ApiModelProperty(value = "入量")
	private BigDecimal intake;

	/**
	 * 出量
	 */
	@ApiModelProperty(value = "出量")
	private BigDecimal output;

	/**
	 * 尿量
	 */
	@ApiModelProperty(value = "尿量")
	private BigDecimal urine;

	/**
	 * 体重
	 */
	@ApiModelProperty(value = "体重")
	private BigDecimal weight;

	/**
	 * 血氧饱和度
	 */
	@ApiModelProperty(value = "血氧饱和度")
	private Integer bloodOxygen;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private String createTime;

	/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人")
	private Long creatorId;
	
}
