package com.nurse.healthy.model.po.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "体检基础档案更新入参")
public class BusExamArchiveUpdatePO {

    @ApiModelProperty(value = "主键", name = "id")
    private Long id;

    @ApiModelProperty(value = "住院号", name = "businessNo")
    private String businessNo;

    @ApiModelProperty(value = "老人姓名", name = "name")
    private String name;

    @ApiModelProperty(value = "老人性别", name = "sex")
    private String sex;

    @ApiModelProperty(value = "老人年龄", name = "age")
    private Integer age;

    @ApiModelProperty(value = "老人联系方式", name = "contactNumber")
    private String contactNumber;

    @ApiModelProperty(value = "受教育程度", name = "education")
    private String education;

    @ApiModelProperty(value = "既往病史编码集合", name = "medicalHistoryCodeList")
    private List<String> medicalHistoryCodeList;

    @ApiModelProperty(value = "备注", name = "remark")
    private String remark;

    @ApiModelProperty(value = "体格检查", name = "physicalCheck")
    private String physicalCheck;

    @ApiModelProperty(value = "主检医生签字", name = "mainDoctorSign")
    private String mainDoctorSign;

    @ApiModelProperty(value = "签字时间", name = "signTime")
    private Date signTime;

    @ApiModelProperty(value = "老人档案id", name = "archiveId")
    private Long archiveId;

    @ApiModelProperty(value = "既往病史名称集合", name = "medicalHistoryNameList")
    private List<String> medicalHistoryNameList;
}
