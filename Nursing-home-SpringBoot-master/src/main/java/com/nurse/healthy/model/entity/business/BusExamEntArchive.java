package com.nurse.healthy.model.entity.business;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Table(name = "bus_exam_ent_archive")
public class BusExamEntArchive implements Serializable {
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
     * 听力左
     */
    @ApiModelProperty(value = "听力左", required = false)
    @Column(name = "hearing_left")
    private BigDecimal hearingLeft;

    /**
     * 听力右
     */
    @ApiModelProperty(value = "听力右", required = false)
    @Column(name = "hearing_right")
    private BigDecimal hearingRight;

    /**
     * 听力签字
     */
    @ApiModelProperty(value = "听力签字", required = false)
    @Column(name = "hearing_sign")
    private String hearingSign;

    /**
     * 耳疾编码
     */
    @ApiModelProperty(value = "耳疾编码", required = false)
    @Column(name = "ear_code")
    private String earCode;

    @ApiModelProperty(value = "耳疾名称", required = false)
    @Column(name = "ear_name")
    private String earName;

    /**
     * 嗅觉编码
     */
    @ApiModelProperty(value = "嗅觉编码", required = false)
    @Column(name = "smell_code")
    private String smellCode;

    @ApiModelProperty(value = "嗅觉名称", required = false)
    @Column(name = "smell_name")
    private String smellName;

    /**
     * 嗅觉签字
     */
    @ApiModelProperty(value = "嗅觉签字", required = false)
    @Column(name = "smell_sign")
    private String smellSign;

    /**
     * 口吃
     */
    @ApiModelProperty(value = "口吃编码", required = false)
    @Column(name = "stuttering_code")
    private String stutteringCode;

    @ApiModelProperty(value = "口吃名称", required = false)
    @Column(name = "stuttering_name")
    private String stutteringName;

    /**
     * 面部
     */
    @ApiModelProperty(value = "面部编码", required = false)
    @Column(name = "face_code")
    private String faceCode;

    @ApiModelProperty(value = "面部名称", required = false)
    @Column(name = "face_name")
    private String faceName;

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
