package com.nurse.healthy.model.entity.business;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "bus_exam_internal_archive")
public class BusExamInternalArchive implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "", required = false)
    @Id
    private Long id;

    /**
     * 体检档案id
     */
    @ApiModelProperty(value = "体检档案id", required = false)
    @Column(name = "exam_id")
    private Long examId;

    /**
     * 心率
     */
    @ApiModelProperty(value = "心率", required = false)
    @Column(name = "heart_rate")
    private Integer heartRate;

    /**
     * 血压高
     */
    @ApiModelProperty(value = "血压高", required = false)
    @Column(name = "blood_pressure_high")
    private Integer bloodPressureHigh;

    /**
     * 血压低
     */
    @ApiModelProperty(value = "血压低", required = false)
    @Column(name = "blood_pressure_low")
    private Integer bloodPressureLow;

    /**
     * 心脏签字
     */
    @ApiModelProperty(value = "心脏签字", required = false)
    @Column(name = "heart_sign")
    private String heartSign;

    /**
     * 营养状况
     */
    @ApiModelProperty(value = "营养状况", required = false)
    @Column(name = "nutriture")
    private String nutriture;

    /**
     * 心血管
     */
    @ApiModelProperty(value = "心血管", required = false)
    @Column(name = "cardiovascular")
    private String cardiovascular;

    /**
     * 神经
     */
    @ApiModelProperty(value = "神经", required = false)
    @Column(name = "nerve")
    private String nerve;

    /**
     * 肝
     */
    @ApiModelProperty(value = "肝", required = false)
    @Column(name = "liver")
    private String liver;

    /**
     * 肺
     */
    @ApiModelProperty(value = "肺", required = false)
    @Column(name = "lung")
    private String lung;

    /**
     *  脾
     */
    @ApiModelProperty(value = " 脾", required = false)
    @Column(name = "spleen")
    private String spleen;

    /**
     * 其他
     */
    @ApiModelProperty(value = "其他", required = false)
    @Column(name = "other")
    private String other;

    /**
     * 签字
     */
    @ApiModelProperty(value = "签字", required = false)
    @Column(name = "signature")
    private String signature;

    /**
     * 医生建议
     */
    @ApiModelProperty(value = "医生建议", required = false)
    @Column(name = "suggestion")
    private String suggestion;

}
