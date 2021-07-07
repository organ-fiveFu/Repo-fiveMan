package com.nurse.healthy.model.vo.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "住院档案信息查询出参")
public class ExamArchiveQueryVO {

    /**
     * 基础体检档案
     */
    @ApiModelProperty(value = "基础档案")
    private BusExamArchiveQueryVO busExamArchiveQueryVO;

    /**
     * 体检档案——五官科
     */
    @ApiModelProperty(value = "体检档案——五官科")
    private BusExamEntArchiveQueryVO busExamEntArchiveQueryVO;

    /**
     * 体检档案——眼科
     */
    @ApiModelProperty(value = "体检档案——眼科")
    private BusExamEyesArchiveQueryVO busExamEyesArchiveQueryVO;

    /**
     * 体检档案——内科
     */
    @ApiModelProperty(value = "体检档案——内科")
    private BusExamInternalArchiveQueryVO busExamInternalArchiveQueryVO;

    /**
     * 体检档案——外科
     */
    @ApiModelProperty(value = "体检档案——外科")
    private BusExamSurgicalArchiveQueryVO busExamSurgicalArchiveQueryVO;


    /**
     * 体检档案——其他检查
     */
    @ApiModelProperty(value = "体检档案——其他检查")
    private BusExamOtherArchiveQueryVO busExamOtherArchiveQueryVO;
}
