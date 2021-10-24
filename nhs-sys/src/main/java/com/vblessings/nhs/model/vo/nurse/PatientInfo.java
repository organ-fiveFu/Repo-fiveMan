package com.vblessings.nhs.model.vo.nurse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PatientInfo {
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String sex;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    private String age;

    /**
     * 科室
     */
    @ApiModelProperty(value = "科室")
    private String endemicName;

    /**
     * 床号
     */
    @ApiModelProperty(value = "床号")
    private String bedName;

    /**
     * 住院号
     */
    @ApiModelProperty(value = "住院号")
    private String businessNo;
}
