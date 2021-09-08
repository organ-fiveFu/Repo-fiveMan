package com.vblessings.nhs.model.vo.business;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ExportMedicationRecordVO {

    /**
     * 用药日期
     */
    @ApiModelProperty(value = "用药日期", required = false)
    @Column(name = "medication_date")
    private String medicationDate;

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
    private BigDecimal measure;

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
}
