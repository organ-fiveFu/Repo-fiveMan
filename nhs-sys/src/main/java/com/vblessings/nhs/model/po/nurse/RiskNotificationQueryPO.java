package com.vblessings.nhs.model.po.nurse;

import com.vblessings.nhs.model.po.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "风险告知书查询入参")
@Data
public class RiskNotificationQueryPO extends PageParam {

    @ApiModelProperty(value = "病人姓名", name = "name")
    private String name;

    @ApiModelProperty(value = "住院号", name = "businessNo")
    private String businessNo;
}
