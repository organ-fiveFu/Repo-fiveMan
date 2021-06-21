package com.nurse.healthy.model.entity.business;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "bus_exam_other_archive")
public class BusExamOtherArchive implements Serializable {
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
     * 血常规
     */
    @ApiModelProperty(value = "血常规", required = false)
    @Column(name = "routine_blood")
    private String routineBlood;

    /**
     * 尿常规
     */
    @ApiModelProperty(value = "尿常规", required = false)
    @Column(name = "routine_urine")
    private String routineUrine;

    /**
     * 肝肾功能
     */
    @ApiModelProperty(value = "肝肾功能", required = false)
    @Column(name = "alt")
    private String alt;

    /**
     * 胸透
     */
    @ApiModelProperty(value = "胸透", required = false)
    @Column(name = "chest_perspective")
    private String chestPerspective;

    /**
     * 胸透签字
     */
    @ApiModelProperty(value = "胸透签字", required = false)
    @Column(name = "chest_perspective_sign")
    private String chestPerspectiveSign;

    /**
     * 体格检查
     */
    @ApiModelProperty(value = "体格检查", required = false)
    @Column(name = "physical_check")
    private String physicalCheck;

}

