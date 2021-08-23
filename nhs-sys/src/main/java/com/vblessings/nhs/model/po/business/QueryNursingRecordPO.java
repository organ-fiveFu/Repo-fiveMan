package com.vblessings.nhs.model.po.business;

import com.vblessings.nhs.model.po.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "查询护理记录入参")
public class QueryNursingRecordPO extends PageParam {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "住院号")
    private String businessNo;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "姓名")
    private Date startTime;

    @ApiModelProperty(value = "姓名")
    private Date endTime;
}
