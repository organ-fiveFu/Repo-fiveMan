package com.vblessings.nhs.model.po.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel(value = "菜单-批量更新PO")
public class BusMenuBatchUpdatePO {

    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不能为空")
    @ApiModelProperty(value = "开始时间", required = true)
    private String startTime;

    /**
     * 结束时间
     */
    @NotNull(message = "结束时间不能为空")
    @ApiModelProperty(value = "结束时间", required = true)
    private String endTime;

    /**
     * 菜谱列表
     */
    @ApiModelProperty(value = "菜谱列表")
    private List<BusMenuBatchUpdateInfo> busMenuBatchUpdateInfos;

}
