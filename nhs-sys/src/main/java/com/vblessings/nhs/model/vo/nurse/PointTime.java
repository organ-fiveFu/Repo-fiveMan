package com.vblessings.nhs.model.vo.nurse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class PointTime {

    /**
     * 脉搏
     */
    @ApiModelProperty(value = "脉搏")
    private List<VitalSignRecord> mb;

    /**
     * 温度
     */
    @ApiModelProperty(value = "温度")
    private List<VitalSignRecord> wd;
}