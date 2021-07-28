package com.vblessings.nhs.model.vo.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "体检基础档案查询出参")
public class BusExamArchiveQueryVO {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "住院号")
    private String businessNo;

    @ApiModelProperty(value = "老人姓名")
    private String name;

    @ApiModelProperty(value = "老人性别")
    private String sex;

    @ApiModelProperty(value = "老人年龄")
    private Integer age;

    @ApiModelProperty(value = "老人联系方式")
    private String contactNumber;

    @ApiModelProperty(value = "受教育程度")
    private String education;

    @ApiModelProperty(value = "既往病史编码")
    private String medicalHistoryCode;

    @ApiModelProperty(value = "既往病史名称")
    private String medicalHistoryName;

    @ApiModelProperty(value = "既往病史编码集合")
    private List<String> medicalHistoryCodeList;

    @ApiModelProperty(value = "既往病史名称集合")
    private List<String> medicalHistoryNameList;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "体格检查")
    private String physicalCheck;

    @ApiModelProperty(value = "主检医生签字")
    private String mainDoctorSign;

    @ApiModelProperty(value = "签字时间")
    private String signTime;

    @ApiModelProperty(value = "老人档案id")
    private Long archiveId;
}
