package com.vblessings.nhs.model.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class QueryFigurePO {

    private Date startTime;

    private Date endTime;

    @ApiModelProperty(name = "timeType",value = "条件日期类型 1，年 2.月")
    private String timeType;


}
