package com.vblessings.nhs.model.po.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "住院档案信息更新入参")
public class ExamArchiveUpdatePO {

    /**
     * 基础体检档案
     */
    @ApiModelProperty(value = "基础档案", name = "busExamArchiveUpdatePO")
    private BusExamArchiveUpdatePO busExamArchiveUpdatePO;

    /**
     * 体检档案——五官科
     */
    @ApiModelProperty(value = "体检档案——五官科", name = "busExamEntArchiveUpdatePO")
    private BusExamEntArchiveUpdatePO busExamEntArchiveUpdatePO;

    /**
     * 体检档案——眼科
     */
    @ApiModelProperty(value = "体检档案——眼科", name = "busExamEyesArchiveUpdatePO")
    private BusExamEyesArchiveUpdatePO busExamEyesArchiveUpdatePO;

    /**
     * 体检档案——内科
     */
    @ApiModelProperty(value = "体检档案——内科", name = "busExamInternalArchiveUpdatePO")
    private BusExamInternalArchiveUpdatePO busExamInternalArchiveUpdatePO;

    /**
     * 体检档案——外科
     */
    @ApiModelProperty(value = "体检档案——外科", name = "busExamSurgicalArchiveUpdatePO")
    private BusExamSurgicalArchiveUpdatePO busExamSurgicalArchiveUpdatePO;


    /**
     * 体检档案——其他检查
     */
    @ApiModelProperty(value = "体检档案——其他检查", name = "busExamOtherArchiveUpdatePO")
    private BusExamOtherArchiveUpdatePO busExamOtherArchiveUpdatePO;
}
