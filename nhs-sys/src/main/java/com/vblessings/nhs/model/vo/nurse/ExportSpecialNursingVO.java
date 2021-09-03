package com.vblessings.nhs.model.vo.nurse;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
@ApiModel("特级护理excel导出实体类")
public class ExportSpecialNursingVO {

    /**
     * 病人姓名
     */
    @ApiModelProperty(value = "病人姓名", required = false)
    @ExcelProperty(value = "patient_name")
    private String patientName;

    /**
     * 过敏史
     */
    @ApiModelProperty(value = "过敏史", required = false)
    @ExcelProperty(value = "allergy")
    private String allergy;

    /**
     * 床号
     */
    @ApiModelProperty(value = "床号", required = false)
    @ExcelProperty(value = "bed_name")
    private String bedName;

    /**
     * 房间号
     */
    @ApiModelProperty(value = "房间号", required = false)
    @ExcelProperty(value = "room_name")
    private String roomName;

    /**
     * 时间
     */
    @ApiModelProperty(value = "时间", required = false)
    @ExcelProperty(value = "nursing_time")
    private String nursingTime;

    /**
     * 是否晨间护理
     */
    @ApiModelProperty(value = "是否晨间护理", required = false)
    @ExcelProperty(value = "is_morning_care")
    private String isMorningCare;

    /**
     * 是否压疮预防护理
     */
    @ApiModelProperty(value = "是否压疮预防护理", required = false)
    @ExcelProperty(value = "is_pressure_ulcers_care")
    private String isPressureUlcersCare;

    /**
     * 是否晚间护理
     */
    @ApiModelProperty(value = "是否晚间护理", required = false)
    @ExcelProperty(value = "is_evening_care")
    private String isEveningCare;

    /**
     * 出量
     */
    @ApiModelProperty(value = "出量", required = false)
    @ExcelProperty(value = "output")
    private String output;

    /**
     * 入量
     */
    @ApiModelProperty(value = "入量", required = false)
    @ExcelProperty(value = "input")
    private String input;

    /**
     * 精神状态和其他
     */
    @ApiModelProperty(value = "精神状态和其他", required = false)
    @ExcelProperty(value = "mental_state")
    private String mentalState;

    /**
     * 责任人
     */
    @ApiModelProperty(value = "责任人", required = false)
    @ExcelProperty(value = "person_in_charge")
    private String personInCharge;

    /**
     * 医院诊断
     */
    @ApiModelProperty(value = "医院诊断", required = false)
    @ExcelProperty(value = "hospital_diagnosis")
    private String hospitalDiagnosis;

}
