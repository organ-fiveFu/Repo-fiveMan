package com.nurse.healthy.model.po;

import com.nurse.healthy.model.entity.business.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ExamArchivePO {

    /**
     * 基础体检档案
     */
    @ApiModelProperty(value = "基础档案", required = false)
    private BusExamArchive busExamArchive;

    /**
     * 体检档案——五官科
     */
    @ApiModelProperty(value = "体检档案——五官科", required = false)
    private BusExamEntArchive busExamEntArchive;

    /**
     * 体检档案——眼科
     */
    @ApiModelProperty(value = "体检档案——眼科", required = false)
    private BusExamEyesArchive busExamEyesArchive;

    /**
     * 体检档案——内科
     */
    @ApiModelProperty(value = "体检档案——内科", required = false)
    private BusExamInternalArchive busExamInternalArchive;

    /**
     * 体检档案——外科
     */
    @ApiModelProperty(value = "体检档案——外科", required = false)
    private BusExamSurgicalArchive busExamSurgicalArchive;


    /**
     * 体检档案——其他检查
     */
    @ApiModelProperty(value = "体检档案——其他检查", required = false)
    private BusExamOtherArchive busExamOtherArchive;
}
