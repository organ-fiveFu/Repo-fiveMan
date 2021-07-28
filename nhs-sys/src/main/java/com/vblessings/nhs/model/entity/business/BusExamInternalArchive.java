package com.vblessings.nhs.model.entity.business;

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
     * 营养状况编码
     */
    @ApiModelProperty(value = "营养状况编码", required = false)
    @Column(name = "nutriture_code")
    private String nutritureCode;

    @ApiModelProperty(value = "营养状况名称", required = false)
    @Column(name = "nutriture_name")
    private String nutritureName;

    /**
     * 心血管编码
     */
    @ApiModelProperty(value = "心血管编码", required = false)
    @Column(name = "cardiovascular_code")
    private String cardiovascularCode;

    @ApiModelProperty(value = "心血管名称", required = false)
    @Column(name = "cardiovascular_name")
    private String cardiovascularName;

    /**
     * 神经编码
     */
    @ApiModelProperty(value = "神经编码", required = false)
    @Column(name = "nerve_code")
    private String nerveCode;

    @ApiModelProperty(value = "神经名称", required = false)
    @Column(name = "nerve_name")
    private String nerveName;

    /**
     * 肝编码
     */
    @ApiModelProperty(value = "肝编码", required = false)
    @Column(name = "liver_code")
    private String liverCode;

    @ApiModelProperty(value = "肝名称", required = false)
    @Column(name = "liver_name")
    private String liverName;

    /**
     * 肺编码
     */
    @ApiModelProperty(value = "肺编码", required = false)
    @Column(name = "lung_code")
    private String lungCode;

    @ApiModelProperty(value = "肺名称", required = false)
    @Column(name = "lung_name")
    private String lungName;

    /**
     * 脾编码
     */
    @ApiModelProperty(value = "脾编码", required = false)
    @Column(name = "spleen_code")
    private String spleenCode;

    @ApiModelProperty(value = "脾名称", required = false)
    @Column(name = "spleen_name")
    private String spleenName;

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
