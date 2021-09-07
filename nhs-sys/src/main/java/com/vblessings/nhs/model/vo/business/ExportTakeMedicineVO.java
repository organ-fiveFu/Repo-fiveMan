package com.vblessings.nhs.model.vo.business;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel("带药记录（or 代配药）excel导出实体类")
public class ExportTakeMedicineVO {


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
     * 计量
     */
    @ApiModelProperty(value = "计量", required = false)
    @ExcelProperty(value = "measure")
    private BigDecimal measure;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量", required = false)
    @ExcelProperty(value = "acount")
    private BigDecimal acount;

    /**
     * 过期时间
     */
    @ApiModelProperty(value = "过期时间", required = false)
    @ExcelProperty(value = "expiry_date")
    private String expiryDate;


    /**
     * 家属签名
     */
    @ApiModelProperty(value = "家属签名", required = false)
    @ExcelProperty(value = "family_sign")
    private String familySign;


    /**
     * 验收人签名
     */
    @ApiModelProperty(value = "护士签名", required = false)
    @ExcelProperty(value = "nursing_sign")
    private String nursingSign;



}
