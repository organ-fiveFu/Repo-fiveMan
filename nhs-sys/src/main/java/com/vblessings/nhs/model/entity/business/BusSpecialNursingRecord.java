package com.vblessings.nhs.model.entity.business;

import com.vblessings.nhs.model.typehandler.ListStringHandler;
import lombok.Data;
import sun.awt.SunHints;
import io.swagger.annotations.ApiModelProperty;
import tk.mybatis.mapper.annotation.ColumnType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Data
@Table(name = "bus_special_nursing_record")
public class BusSpecialNursingRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "", required = false)
    @Id
    private Long id;

    /**
     * 住院号
     */
    @ApiModelProperty(value = "住院号", required = false)
    @Column(name = "business_no")
    private String businessNo;

    /**
     * 病人姓名
     */
    @ApiModelProperty(value = "病人姓名", required = false)
    @Column(name = "patient_name")
    private String patientName;

    /**
     * 过敏史
     */
    @ApiModelProperty(value = "过敏史", required = false)
    @Column(name = "allergy")
    private String allergy;

    /**
     * 床号
     */
    @ApiModelProperty(value = "床号", required = false)
    @Column(name = "bed_name")
    private String bedName;

    /**
     * 房间号
     */
    @ApiModelProperty(value = "房间号", required = false)
    @Column(name = "room_name")
    private String roomName;

    /**
     * 时间
     */
    @ApiModelProperty(value = "时间", required = false)
    @Column(name = "nursing_time")
    private Date nursingTime;

    /**
     * 是否晨间护理
     */
    @ApiModelProperty(value = "是否晨间护理", required = false)
    @Column(name = "is_morning_care")
    private String isMorningCare;

    /**
     * 是否压疮预防护理
     */
    @ApiModelProperty(value = "是否压疮预防护理", required = false)
    @Column(name = "is_pressure_ulcers_care")
    private String isPressureUlcersCare;

    /**
     * 是否晚间护理
     */
    @ApiModelProperty(value = "是否晚间护理", required = false)
    @Column(name = "is_evening_care")
    private String isEveningCare;

    /**
     * 出量
     */
    @ApiModelProperty(value = "出量", required = false)
    @Column(name = "output")
    private String output;

    /**
     * 入量
     */
    @ApiModelProperty(value = "入量", required = false)
    @Column(name = "input")
    private String input;

    /**
     * 精神状态和其他
     */
    @ApiModelProperty(value = "精神状态和其他", required = false)
    @Column(name = "mental_state")
    private String mentalState;

    /**
     * 责任人
     */
    @ApiModelProperty(value = "责任人", required = false)
    @Column(name = "person_in_charge")
    private String personInCharge;

    /**
     * 医院诊断
     */
    @ApiModelProperty(value = "医院诊断", required = false)
    @Column(name = "hospital_diagnosis")
    @ColumnType(typeHandler = ListStringHandler.class)
    private List<String> hospitalDiagnosis;

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

}

