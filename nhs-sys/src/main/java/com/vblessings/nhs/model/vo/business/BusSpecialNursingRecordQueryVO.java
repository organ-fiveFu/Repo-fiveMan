package com.vblessings.nhs.model.vo.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "特级护理记录批量查询出参")
public class BusSpecialNursingRecordQueryVO {

    /**
     *
     */
    @ApiModelProperty(value = "", required = false)
    private Long id;

    /**
     * 住院号
     */
    @ApiModelProperty(value = "住院号", required = false)
    private String businessNo;

    /**
     * 病人姓名
     */
    @ApiModelProperty(value = "病人姓名", required = false)
    private String patientName;

    /**
     * 过敏史
     */
    @ApiModelProperty(value = "过敏史", required = false)
    private String allergy;

    /**
     * 床号
     */
    @ApiModelProperty(value = "床号", required = false)
    private String bedName;

    /**
     * 房间号
     */
    @ApiModelProperty(value = "房间号", required = false)
    private String roomName;

    @ApiModelProperty(value = "楼层号", required = false)
    private String floorName;

    @ApiModelProperty(value = "楼宇号", required = false)
    private String buildingName;

    /**
     * 时间
     */
    @ApiModelProperty(value = "时间", required = false)
    private String nursingTime;

    /**
     * 是否晨间护理
     */
    @ApiModelProperty(value = "是否晨间护理", required = false)
    private String isMorningCare;

    /**
     * 是否压疮预防护理
     */
    @ApiModelProperty(value = "是否压疮预防护理", required = false)
    private String isPressureUlcersCare;

    /**
     * 是否晚间护理
     */
    @ApiModelProperty(value = "是否晚间护理", required = false)
    private String isEveningCare;

    /**
     * 出量
     */
    @ApiModelProperty(value = "出量", required = false)
    private String output;

    /**
     * 入量
     */
    @ApiModelProperty(value = "入量", required = false)
    private String input;

    /**
     * 精神状态和其他
     */
    @ApiModelProperty(value = "精神状态和其他", required = false)
    private String mentalState;

    /**
     * 责任人
     */
    @ApiModelProperty(value = "责任人", required = false)
    private String personInCharge;

    /**
     * 医院诊断
     */
    @ApiModelProperty(value = "医院诊断", required = false)
    private String hospitalDiagnosis;

    @ApiModelProperty(value = "拼接床位名称")
    private String spliceBedName;
}
