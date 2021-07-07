package com.nurse.healthy.model.po.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "住院档案信息新增入参")
public class ExamArchiveInsertPO {

    /**
     * 基础体检档案
     */
    @ApiModelProperty(value = "基础档案", name = "busExamArchiveInsertPO")
    private BusExamArchiveInsertPO busExamArchiveInsertPO;

    /**
     * 体检档案——五官科
     */
    @ApiModelProperty(value = "体检档案——五官科", name = "busExamEntArchiveInsertPO")
    private BusExamEntArchiveInsertPO busExamEntArchiveInsertPO;

    /**
     * 体检档案——眼科
     */
    @ApiModelProperty(value = "体检档案——眼科", name = "busExamEyesArchiveInsertPO")
    private BusExamEyesArchiveInsertPO busExamEyesArchiveInsertPO;

    /**
     * 体检档案——内科
     */
    @ApiModelProperty(value = "体检档案——内科", name = "busExamInternalArchiveInsertPO")
    private BusExamInternalArchiveInsertPO busExamInternalArchiveInsertPO;

    /**
     * 体检档案——外科
     */
    @ApiModelProperty(value = "体检档案——外科", name = "busExamSurgicalArchiveInsertPO")
    private BusExamSurgicalArchiveInsertPO busExamSurgicalArchiveInsertPO;


    /**
     * 体检档案——其他检查
     */
    @ApiModelProperty(value = "体检档案——其他检查", name = "busExamOtherArchiveInsertPO")
    private BusExamOtherArchiveInsertPO busExamOtherArchiveInsertPO;
}
