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
     * 血常规编码
     */
    @ApiModelProperty(value = "血常规编码", required = false)
    @Column(name = "routine_blood_code")
    private String routineBloodCode;

    @ApiModelProperty(value = "血常规名称", required = false)
    @Column(name = "routine_blood_name")
    private String routineBloodName;

    /**
     * 尿常规编码
     */
    @ApiModelProperty(value = "尿常规编码", required = false)
    @Column(name = "routine_urine_code")
    private String routineUrineCode;

    @ApiModelProperty(value = "尿常规名称", required = false)
    @Column(name = "routine_urine_name")
    private String routineUrineName;

    /**
     * 肝肾功能编码
     */
    @ApiModelProperty(value = "肝肾功能编码", required = false)
    @Column(name = "alt_code")
    private String altCode;

    @ApiModelProperty(value = "肝肾功能名称", required = false)
    @Column(name = "alt_name")
    private String altName;

    /**
     * 胸透编码
     */
    @ApiModelProperty(value = "胸透编码", required = false)
    @Column(name = "chest_perspective_code")
    private String chestPerspectiveCode;

    @ApiModelProperty(value = "胸透名称", required = false)
    @Column(name = "chest_perspective_name")
    private String chestPerspectiveName;

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

