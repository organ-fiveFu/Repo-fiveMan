package com.vblessings.nhs.model.po.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "菜单-批量更新PO")
public class BusMenuBatchUpdatePO {

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private List<BusMenuBatchUpdateInfo> busMenuBatchUpdateInfos;

}
