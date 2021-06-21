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
     * 耳疾
     */
    @ApiModelProperty(value = "耳疾", required = false)
    @Column(name = "ear")
    private String ear;

    /**
     * 嗅觉
     */
    @ApiModelProperty(value = "嗅觉", required = false)
    @Column(name = "smell")
    private String smell;

    /**
     * 嗅觉签字
     */
    @ApiModelProperty(value = "嗅觉签字", required = false)
    @Column(name = "smell_sign")
    private String smellSign;

    /**
     * 口吃
     */
    @ApiModelProperty(value = "口吃", required = false)
    @Column(name = "stuttering")
    private String stuttering;

    /**
     * 面部
     */
    @ApiModelProperty(value = "面部", required = false)
    @Column(name = "face")
    private String face;

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
