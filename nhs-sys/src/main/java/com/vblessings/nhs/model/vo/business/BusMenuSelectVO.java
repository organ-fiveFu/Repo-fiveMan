package com.vblessings.nhs.model.vo.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "菜单-查询VO")
public class BusMenuSelectVO {

    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 早餐——主食
     */
    @ApiModelProperty(value = "早餐——主食")
    private List<String> breakfastStapleFood;

    /**
     * 早餐——菜单
     */
    @ApiModelProperty(value = "早餐——菜单")
    private List<String> breakfastMenu;

    /**
     * 午餐——主食
     */
    @ApiModelProperty(value = "午餐——主食")
    private List<String> lunchStapleFood;

    /**
     * 午餐——菜单
     */
    @ApiModelProperty(value = "午餐——菜单")
    private List<String> lunchMenu;

    /**
     * 晚餐——主食
     */
    @ApiModelProperty(value = "晚餐——主食")
    private List<String> dinnerStapleFood;

    /**
     * 晚餐——菜单
     */
    @ApiModelProperty(value = "晚餐——菜单")
    private List<String> dinnerMeun;

    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    private String date;

    /**
     * 星期
     */
    @ApiModelProperty(value = "星期")
    private String dayOfWeek;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Long creatorId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
