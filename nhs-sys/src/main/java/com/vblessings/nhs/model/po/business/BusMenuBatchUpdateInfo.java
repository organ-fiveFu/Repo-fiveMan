package com.vblessings.nhs.model.po.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "菜单-批量更新PO")
public class BusMenuBatchUpdateInfo {

    @ApiModelProperty(value = "主键", name = "id")
    private Long id;

    /**
     * 早餐——主食
     */
    @ApiModelProperty(value = "早餐——主食", required = false)
    private List<String> breakfastStapleFood;

    /**
     * 早餐——菜单
     */
    @ApiModelProperty(value = "早餐——菜单", required = false)
    private List<String> breakfastMenu;

    /**
     * 午餐——主食
     */
    @ApiModelProperty(value = "午餐——主食", required = false)
    private List<String> lunchStapleFood;

    /**
     * 午餐——菜单
     */
    @ApiModelProperty(value = "午餐——菜单", required = false)
    private List<String> lunchMenu;

    /**
     * 晚餐——主食
     */
    @ApiModelProperty(value = "晚餐——主食", required = false)
    private List<String> dinnerStapleFood;

    /**
     * 晚餐——菜单
     */
    @ApiModelProperty(value = "晚餐——菜单", required = false)
    private List<String> dinnerMeun;

    /**
     * 日期
     */
    @ApiModelProperty(value = "日期", required = false)
    private Date date;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", required = false)
    private Long creatorId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = false)
    private Date createTime;

}
