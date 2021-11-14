package com.vblessings.nhs.model.vo.nurse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VitalSignRecord {
    /**
     * 时间
     */
    @ApiModelProperty(value = "时间")
    private String dataTime = "";

    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    private String date = "";

    /**
     * 小时
     */
    @ApiModelProperty(value = "小时")
    private Integer hour;

    /**
     * 脉搏
     */
    @ApiModelProperty(value = "脉搏")
    private String mbValue = "";

    /**
     * ?
     */
    @ApiModelProperty(value = "?")
    private String phValue = "";

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private String type = "";

    /**
     * 类型值
     */
    @ApiModelProperty(value = "类型值")
    private String value = "";

    /**
     * ?
     */
    @ApiModelProperty(value = "?")
    private String xlValue = "";
}
