package com.vblessings.nhs.model.vo.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "入院查询出参")
public class BusHospitalRecordVO {

    @ApiModelProperty(value = "", required = false)
    private Long id;

    @ApiModelProperty(value = "老人档案id", required = false)
    private Long archiveId;

    @ApiModelProperty(value = "住院号", required = false)
    private String businessNo;

    @ApiModelProperty(value = "出院时间", required = false)
    private String dischargeTime;

    @ApiModelProperty(value = "入院时间", required = false)
    private String admissionTime;

    @ApiModelProperty(value = "状态", required = false)
    private String status;

    @ApiModelProperty(value = "创建人", required = false)
    private Long creatorId;

    @ApiModelProperty(value = "更新人", required = false)
    private Long updaterId;

    @ApiModelProperty(value = "创建时间", required = false)
    private String createTime;

    @ApiModelProperty(value = "更新时间", required = false)
    private String updateTime;

    @ApiModelProperty(value = "是否启用", required = false)
    private Integer isDel;

    @ApiModelProperty(value = "护理级别", required = false)
    private String nursingLevel;

    @ApiModelProperty(value = "入院原因", required = false)
    private String admissionReason;

    @ApiModelProperty(value = "诊断", required = false)
    private String hospitalDiagnosis;

    @ApiModelProperty(value = "是否签署风险告知书", required = false)
    private String isSignNotification;

    @ApiModelProperty(value = "老人姓名", required = false)
    private String name;

    @ApiModelProperty(value = "老人性别", required = false)
    private String sex;

    @ApiModelProperty(value = "老人年龄", required = false)
    private Integer age;

    @ApiModelProperty(value = "老人身份证号码", required = false)
    private String idCard;

    @ApiModelProperty(value = "老人联系地址", required = false)
    private String contactAddress;

    @ApiModelProperty(value = "监护人姓名", required = false)
    private String relationName;

    @ApiModelProperty(value = "监护人联系方式", required = false)
    private String contactNumber;

    @ApiModelProperty(value = "亲属关系", required = false)
    private String relation;

    @ApiModelProperty(value = "床位编号", required = false)
    private String bedCode;

    @ApiModelProperty(value = "楼层编号", required = false)
    private String floorCode;

    @ApiModelProperty(value = "过敏史", required = false)
    private String allergy;

    @ApiModelProperty(value = "既往史", required = false)
    private  String previousHistory;

    @ApiModelProperty(value = "费用过期时间", required = false)
    private String feesDueDate;

    @ApiModelProperty(value = "费用到期状态0：没到期", required = false)
    private Integer feesDueStatue;

    @ApiModelProperty(value = "楼宇编号", required = false)
    private String buildingCode;

    @ApiModelProperty(value = "房间编号", required = false)
    private String roomCode;

    @ApiModelProperty(value = "病人来源（1.社会 2.医院）", required = false)
    private String peopleFrom;

    @ApiModelProperty(value = "病人去向（1.社会 2.医院）", required = false)
    private String peopleTo;

    @ApiModelProperty(value = "楼宇名称", required = false)
    private String buildingName;

    @ApiModelProperty(value = "房间名称", required = false)
    private String roomName;

    @ApiModelProperty(value = "床位名称", required = false)
    private String bedName;

    @ApiModelProperty(value = "楼层名称", required = false)
    private String floorName;

    @ApiModelProperty(value = "监护人身份证号码")
    private String guardianIdCard;
}
