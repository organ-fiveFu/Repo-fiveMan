package com.vblessings.nhs.model.po.business;

import com.vblessings.nhs.model.po.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryMedicineRecordPO extends PageParam {

    private String businessNo;

    private String name;

    @ApiModelProperty(value = "楼宇code")
    private String buildingCode;

    @ApiModelProperty(value = "楼层code")
    private String floorCode;
}
