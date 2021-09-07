package com.vblessings.nhs.model.vo.nurse;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "血糖记录导出出参")
public class BloodSugarExcelVO {

    @ApiModelProperty(value = "采样日期", required = false)
    @ExcelProperty(value = "采样日期")
    private String samplingTime;

    @ApiModelProperty(value = "采样状态", required = false)
    @ExcelProperty(value = "采样状态")
    private String samplingStatusName;

    @ApiModelProperty(value = "血糖值", required = false)
    @ExcelProperty(value = "血糖值")
    private String bloodGlucoseValue;

    @ApiModelProperty(value = "单位", required = false)
    @ExcelProperty(value = "单位")
    private String company = "mmol";
}
