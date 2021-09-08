package com.vblessings.nhs.model.po.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "住院档案信息新增入参")
public class ExamArchiveInsertPO {



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

    @ApiModelProperty(value = "既往病史编码集合", name = "medicalHistoryCode")
    private List<String> medicalHistoryCode;

    @ApiModelProperty(value = "既往病史名称集合", name = "medicalHistoryName")
    private List<String> medicalHistoryName;

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

    /**
     * 体检报告
     */
    @ApiModelProperty(value = "体检报告")
    private MultipartFile examArchiveFile;

//    /**
//     * 基础体检档案
//     */
//    @ApiModelProperty(value = "基础档案", name = "busExamArchiveInsertPO")
//    private BusExamArchiveInsertPO busExamArchiveInsertPO;
//
//    /**
//     * 体检档案——五官科
//     */
//    @ApiModelProperty(value = "体检档案——五官科", name = "busExamEntArchiveInsertPO")
//    private BusExamEntArchiveInsertPO busExamEntArchiveInsertPO;
//
//    /**
//     * 体检档案——眼科
//     */
//    @ApiModelProperty(value = "体检档案——眼科", name = "busExamEyesArchiveInsertPO")
//    private BusExamEyesArchiveInsertPO busExamEyesArchiveInsertPO;
//
//    /**
//     * 体检档案——内科
//     */
//    @ApiModelProperty(value = "体检档案——内科", name = "busExamInternalArchiveInsertPO")
//    private BusExamInternalArchiveInsertPO busExamInternalArchiveInsertPO;
//
//    /**
//     * 体检档案——外科
//     */
//    @ApiModelProperty(value = "体检档案——外科", name = "busExamSurgicalArchiveInsertPO")
//    private BusExamSurgicalArchiveInsertPO busExamSurgicalArchiveInsertPO;
//
//
//    /**
//     * 体检档案——其他检查
//     */
//    @ApiModelProperty(value = "体检档案——其他检查", name = "busExamOtherArchiveInsertPO")
//    private BusExamOtherArchiveInsertPO busExamOtherArchiveInsertPO;
}
