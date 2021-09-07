package com.vblessings.nhs.model.vo.nurse;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
@ApiModel("特级护理excel导出实体类")
public class ExportSpecialNursingVO {

    /**
     * 时间
     */
    @ApiModelProperty(value = "时间", required = false)
    @ExcelProperty(value = "时间")
    @ColumnWidth(8)
    private String nursingTime;

    /**
     * 是否晨间护理
     */
    @ApiModelProperty(value = "是否晨间护理", required = false)
    @ExcelProperty(value = "是否晨间护理")
    @ColumnWidth(2)
    private String isMorningCare;

    /**
     * 是否压疮预防护理
     */
    @ApiModelProperty(value = "是否压疮预防护理", required = false)
    @ExcelProperty(value = "是否压疮预防护理")
    @ColumnWidth(2)
    private String isPressureUlcersCare;


    /**
     * 出入量
     */
    @ApiModelProperty(value = "出入量", required = false)
    @ExcelProperty(value = "出入量")
    @ColumnWidth(6)
    private String input;


    /**
     * 是否晚间护理
     */
    @ApiModelProperty(value = "是否晚间护理", required = false)
    @ExcelProperty(value = "是否晚间护理")
    @ColumnWidth(2)
    private String isEveningCare;

    /**
     * 精神状态和其他
     */
    @ApiModelProperty(value = "精神状态和其他", required = false)
    @ExcelProperty(value = "精神状态和其他")
    @ColumnWidth(4)
    private String mentalState;

    /**
     * 责任人
     */
    @ApiModelProperty(value = "责任人", required = false)
    @ExcelProperty(value = "责任人")
    @ColumnWidth(4)
    private String personInCharge;


}
