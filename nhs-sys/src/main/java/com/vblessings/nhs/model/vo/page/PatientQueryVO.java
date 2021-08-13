package com.vblessings.nhs.model.vo.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "病人基础信息查询出参")
public class PatientQueryVO {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "住院号")
    private String businessNo;

    @ApiModelProperty(value = "档案id")
    private Long archiveId;

    @ApiModelProperty(value = "楼宇编号")
    private String buildingCode;

    @ApiModelProperty(value = "楼宇名称")
    private String buildingName;

    @ApiModelProperty(value = "楼层编号")
    private String floorCode;

    @ApiModelProperty(value = "楼层名称")
    private String floorName;

    @ApiModelProperty(value = "房间编号")
    private String roomCode;

    @ApiModelProperty(value = "房间名称")
    private String roomName;

    @ApiModelProperty(value = "床位编号")
    private String bedCode;

    @ApiModelProperty(value = "床位名称")
    private String bedName;

    @ApiModelProperty(value = "楼宇+楼层+房间+床位名称")
    private String totalName;

    @ApiModelProperty(value = "老人身份证")
    private String idCard;

    @ApiModelProperty(value = "老人联系方式")
    private String contactNumber;

    @ApiModelProperty(value = "老人联系地址")
    private String contactAddress;

    @ApiModelProperty(value = "监护人姓名")
    private String guardianName;

    @ApiModelProperty(value = "监护人性别")
    private String guardianSex;

    @ApiModelProperty(value = "监护人身份证号")
    private String guardianIdCard;

    @ApiModelProperty(value = "亲属关系")
    private String relation;
}
