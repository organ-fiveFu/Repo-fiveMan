package com.vblessings.nhs.model.po.business;

import com.vblessings.nhs.model.po.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryNursingRecordPO extends PageParam {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "住院号")
    private String businessNo;

    @ApiModelProperty(value = "姓名")
    private String name;
}
