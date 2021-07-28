package com.vblessings.nhs.model.entity.business;

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
     * 体重签字
     */
    @ApiModelProperty(value = "体重签字", required = false)
    @Column(name = "shape_sign")
    private String shapeSign;

    /**
     * 皮肤编码
     */
    @ApiModelProperty(value = "皮肤编码", required = false)
    @Column(name = "skin_code")
    private String skinCode;

    @ApiModelProperty(value = "皮肤名称", required = false)
    @Column(name = "skin_name")
    private String skinName;

    /**
     * 四肢编码
     */
    @ApiModelProperty(value = "四肢编码", required = false)
    @Column(name = "limbs_code")
    private String limbsCode;

    @ApiModelProperty(value = "四肢名称", required = false)
    @Column(name = "limbs_name")
    private String limbsName;

    /**
     * 淋巴编码
     */
    @ApiModelProperty(value = "淋巴编码", required = false)
    @Column(name = "lymphatic_code")
    private String lymphaticCode;

    @ApiModelProperty(value = "淋巴名称", required = false)
    @Column(name = "lymphatic_name")
    private String lymphaticName;

    /**
     * 关节编码
     */
    @ApiModelProperty(value = "关节编码", required = false)
    @Column(name = "joints_code")
    private String jointsCode;

    @ApiModelProperty(value = "关节名称", required = false)
    @Column(name = "joints_name")
    private String jointsName;

    /**
     * 脊柱编码
     */
    @ApiModelProperty(value = "脊柱编码", required = false)
    @Column(name = "spine_code")
    private String spineCode;

    @ApiModelProperty(value = "脊柱名称", required = false)
    @Column(name = "spine_name")
    private String spineName;

    /**
     * 甲状腺编码
     */
    @ApiModelProperty(value = "甲状腺编码", required = false)
    @Column(name = "thyroid_code")
    private String thyroidCode;

    @ApiModelProperty(value = "甲状腺名称", required = false)
    @Column(name = "thyroid_name")
    private String thyroidName;

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

