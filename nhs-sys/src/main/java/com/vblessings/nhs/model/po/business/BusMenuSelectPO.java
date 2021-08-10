package com.vblessings.nhs.model.po.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ApiModel(value = "菜单-查询入参")
public class BusMenuSelectPO {

    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不能为空")
    @ApiModelProperty(value = "开始时间", required = true)
    private Date startTime;

    /**
     * 结束时间
     */
    @NotNull(message = "结束时间不能为空")
    @ApiModelProperty(value = "结束时间", required = true)
    private Date endTime;
}
