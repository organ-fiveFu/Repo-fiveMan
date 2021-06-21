package com.nurse.healthy.model.entity.business;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Table(name = "bus_exam_surgical_archive")
public class BusExamSurgicalArchive implements Serializable {
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
     * 身高
     */
    @ApiModelProperty(value = "身高", required = false)
    @Column(name = "height")
    private BigDecimal height;

    /**
     * 体重
     */
    @ApiModelProperty(value = "体重", required = false)
    @Column(name = "weight")
    private BigDecimal weight;

    /**
     * 体型
     */
    @ApiModelProperty(value = "体型", required = false)
    @Column(name = "shape_sign")
    private String shapeSign;

    /**
     * 皮肤
     */
    @ApiModelProperty(value = "皮肤", required = false)
    @Column(name = "skin")
    private String skin;

    /**
     * 四肢
     */
    @ApiModelProperty(value = "四肢", required = false)
    @Column(name = "limbs")
    private String limbs;

    /**
     * 淋巴
     */
    @ApiModelProperty(value = "淋巴", required = false)
    @Column(name = "lymphatic")
    private String lymphatic;

    /**
     * 关节
     */
    @ApiModelProperty(value = "关节", required = false)
    @Column(name = "joints")
    private String joints;

    /**
     * 脊柱
     */
    @ApiModelProperty(value = "脊柱", required = false)
    @Column(name = "spine")
    private String spine;

    /**
     * 甲状腺
     */
    @ApiModelProperty(value = "甲状腺", required = false)
    @Column(name = "thyroid")
    private String thyroid;

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

