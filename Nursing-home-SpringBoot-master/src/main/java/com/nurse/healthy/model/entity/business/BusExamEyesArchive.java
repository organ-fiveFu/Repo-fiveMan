package com.nurse.healthy.model.entity.business;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Table(name = "bus_exam_eyes_archive")
public class BusExamEyesArchive implements Serializable {
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
     * 裸眼视力左
     */
    @ApiModelProperty(value = "裸眼视力左", required = false)
    @Column(name = "ucva_left")
    private BigDecimal ucvaLeft;

    /**
     * 裸眼视力右
     */
    @ApiModelProperty(value = "裸眼视力右", required = false)
    @Column(name = "ucva_right")
    private BigDecimal ucvaRight;

    /**
     * 矫正视力左
     */
    @ApiModelProperty(value = "矫正视力左", required = false)
    @Column(name = "cva_left")
    private BigDecimal cvaLeft;

    /**
     * 矫正视力右
     */
    @ApiModelProperty(value = "矫正视力右", required = false)
    @Column(name = "cva_right")
    private BigDecimal cvaRight;

    /**
     * 视觉编码
     */
    @ApiModelProperty(value = "视觉编码", required = false)
    @Column(name = "color_vision_code")
    private String colorVisionCode;

    @ApiModelProperty(value = "视觉名称", required = false)
    @Column(name = "color_vision_name")
    private String colorVisionName;

    /**
     * 视觉签字
     */
    @ApiModelProperty(value = "视觉签字", required = false)
    @Column(name = "color_vision_sign")
    private String colorVisionSign;

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

    /**
     * 其他
     */
    @ApiModelProperty(value = "其他", required = false)
    @Column(name = "other")
    private String other;

}

