package com.vblessings.nhs.model.vo.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "首页查询详情出参")
public class PageDetailQueryVO {

    @ApiModelProperty(value = "住院号")
    private String businessNo;

    @ApiModelProperty(value = "档案id")
    private Long archiveId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "性别名称")
    private String sexName;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "楼宇code")
    private String buildingCode;

    @ApiModelProperty(value = "楼宇name")
    private String buildingName;

    @ApiModelProperty(value = "楼层code")
    private String floorCode;

    @ApiModelProperty(value = "楼层name")
    private String floorName;

    @ApiModelProperty(value = "房间code")
    private String roomCode;

    @ApiModelProperty(value = "房间name")
    private String roomName;

    @ApiModelProperty(value = "床位code")
    private String bedCode;

    @ApiModelProperty(value = "床位name")
    private String bedName;

    @ApiModelProperty(value = "房间类型code")
    private String roomType;

    @ApiModelProperty(value = "房间类型name")
    private String roomTypeName;

    @ApiModelProperty(value = "护理级别code")
    private String nursingLevel;

    @ApiModelProperty(value = "护理级别name")
    private String nursingLevelName;

    @ApiModelProperty(value = "入院时间")
    private String admissionTime;

    @ApiModelProperty(value = "入院诊断")
    private String hospitalDiagnosis;

    @ApiModelProperty(value = "监护人联系电话")
    private String contactNumber;

    @ApiModelProperty(value = "过敏史")
    private String allergy;

    @ApiModelProperty(value = "费用到期标志(0:未到期)")
    private Integer feesDueStatue = 0;

    @ApiModelProperty(value = "费用到期时间")
    private String feesDueDate;

    @ApiModelProperty(value = "入住状态")
    private String status;
}
