package com.vblessings.nhs.model.entity.business;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "bus_medication_record")
public class BusMedicationRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "", required = false)
    @Id
    private Long id;

    /**
     * 用药日期
     */
    @ApiModelProperty(value = "用药日期", required = false)
    @Column(name = "medication_date")
    private Date medicationDate;

    /**
     * 床号
     */
    @ApiModelProperty(value = "床号", required = false)
    @Column(name = "bed_code")
    private String bedCode;

    /**
     * 用药人姓名
     */
    @ApiModelProperty(value = "用药人姓名", required = false)
    @Column(name = "name")
    private String name;

    /**
     * 药品规格
     */
    @ApiModelProperty(value = "药品规格", required = false)
    @Column(name = "drug_specification")
    private String drugSpecification;

    /**
     * 计量
     */
    @ApiModelProperty(value = "计量", required = false)
    @Column(name = "measure")
    private String measure;

    /**
     * 频次
     */
    @ApiModelProperty(value = "频次", required = false)
    @Column(name = "frequency")
    private String frequency;

    /**
     * 用药时间
     */
    @ApiModelProperty(value = "用药时间", required = false)
    @Column(name = "medication_time")
    private String medicationTime;

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

    @ApiModelProperty(value = "住院号", required = false)
    @Column(name = "business_no")
    private String businessNo;
    /**
     * 病区
     */
    @ApiModelProperty(value = "病区", required = false)
    @Column(name = "ward")
    private String ward;

    /**
     * 护士签名
     */
    @ApiModelProperty(value = "护士签名", required = false)
    @Column(name = "nurse_sign")
    private String nurseSign;



}

