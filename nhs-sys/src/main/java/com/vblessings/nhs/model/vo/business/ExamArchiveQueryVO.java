package com.vblessings.nhs.model.vo.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@ApiModel(value = "住院档案信息查询出参")
public class ExamArchiveQueryVO {

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
    private List<String> medicalHistoryCode;

    @ApiModelProperty(value = "既往病史名称")
    private List<String> medicalHistoryName;

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

    /**
     * 体检报告名称
     */
    @ApiModelProperty(value = "体检报告名称")
    private String examArchiveName;

    /**
     * 体检报告地址
     */
    @ApiModelProperty(value = "体检报告地址")
    private String examArchiveUrl;

//    /**
//     * 基础体检档案
//     */
//    @ApiModelProperty(value = "基础档案")
//    private BusExamArchiveQueryVO busExamArchiveQueryVO;
//
//    /**
//     * 体检档案——五官科
//     */
//    @ApiModelProperty(value = "体检档案——五官科")
//    private BusExamEntArchiveQueryVO busExamEntArchiveQueryVO;
//
//    /**
//     * 体检档案——眼科
//     */
//    @ApiModelProperty(value = "体检档案——眼科")
//    private BusExamEyesArchiveQueryVO busExamEyesArchiveQueryVO;
//
//    /**
//     * 体检档案——内科
//     */
//    @ApiModelProperty(value = "体检档案——内科")
//    private BusExamInternalArchiveQueryVO busExamInternalArchiveQueryVO;
//
//    /**
//     * 体检档案——外科
//     */
//    @ApiModelProperty(value = "体检档案——外科")
//    private BusExamSurgicalArchiveQueryVO busExamSurgicalArchiveQueryVO;
//
//
//    /**
//     * 体检档案——其他检查
//     */
//    @ApiModelProperty(value = "体检档案——其他检查")
//    private BusExamOtherArchiveQueryVO busExamOtherArchiveQueryVO;
}
