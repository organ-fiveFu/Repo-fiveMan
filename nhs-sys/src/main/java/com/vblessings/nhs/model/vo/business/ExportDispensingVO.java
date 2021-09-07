package com.vblessings.nhs.model.vo.business;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;

@Data
public class ExportDispensingVO {
    /**
     * 带药日期
     */
    @ApiModelProperty(value = "带药日期", required = false)
    @ExcelProperty(value = "take_medicine_date")
    private String takeMedicineDate;

    /**
     * 药品名称
     */
    @ApiModelProperty(value = "药品名称", required = false)
    @ExcelProperty(value = "drug_name")
    private String drugName;



    /**
     * 用法
     */
    @ApiModelProperty(value = "用法", required = false)
    @Column(name = "use_way")
    private String useWay;

    /**
     * 用量
     */
    @ApiModelProperty(value = "用量", required = false)
    @Column(name = "dosage")
    private String dosage;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = false)
    @Column(name = "remark")
    private String remark;


}
