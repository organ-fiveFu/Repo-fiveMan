package com.vblessings.nhs.model.entity.business;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "bus_hospital_record")
public class BusHospitalRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "", required = false)
    @Id
    private Long id;

    /**
     * 老人档案id
     */
    @ApiModelProperty(value = "老人档案id", required = false)
    @Column(name = "aichive_id")
    private Long aichiveId;

    /**
     * 住院号
     */
    @ApiModelProperty(value = "住院号", required = false)
    @Column(name = "business_no")
    private String businessNo;

    /**
     * 出院时间
     */
    @ApiModelProperty(value = "出院时间", required = false)
    @Column(name = "discharge_time")
    private Date dischargeTime;

    /**
     * 入院时间
     */
    @ApiModelProperty(value = "入院时间", required = false)
    @Column(name = "admission_time")
    private Date admissionTime;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", required = false)
    @Column(name = "status")
    private String status;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", required = false)
    @Column(name = "creator_id")
    private Long creatorId;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人", required = false)
    @Column(name = "updater_id")
    private Long updaterId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = false)
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", required = false)
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用", required = false)
    @Column(name = "is_del")
    private Integer isDel;

    /**
     * 护理级别
     */
    @ApiModelProperty(value = "护理级别", required = false)
    @Column(name = "nursing_level")
    private String nursingLevel;

    /**
     * 入院原因
     */
    @ApiModelProperty(value = "入院原因", required = false)
    @Column(name = "admission_reason")
    private String admissionReason;

    /**
     * 诊断
     */
    @ApiModelProperty(value = "诊断", required = false)
    @Column(name = "hospital_diagnosis")
    private String hospitalDiagnosis;

    /**
     * 是否签署风险告知书
     */
    @ApiModelProperty(value = "是否签署风险告知书", required = false)
    @Column(name = "is_sign_notification")
    private String isSignNotification;

    /**
     * 老人姓名
     */
    @ApiModelProperty(value = "老人姓名", required = false)
    @Column(name = "name")
    private String name;

    /**
     * 老人性别
     */
    @ApiModelProperty(value = "老人性别", required = false)
    @Column(name = "sex")
    private String sex;

    /**
     * 老人年龄
     */
    @ApiModelProperty(value = "老人年龄", required = false)
    @Column(name = "age")
    private Integer age;

    /**
     * 老人身份证号码
     */
    @ApiModelProperty(value = "老人身份证号码", required = false)
    @Column(name = "id_card")
    private String idCard;

    /**
     * 老人联系地址
     */
    @ApiModelProperty(value = "老人联系地址", required = false)
    @Column(name = "contact_address")
    private String contactAddress;

    /**
     * 监护人姓名
     */
    @ApiModelProperty(value = "监护人姓名", required = false)
    @Column(name = "relation_name")
    private String relationName;

    /**
     * 监护人联系方式
     */
    @ApiModelProperty(value = "监护人联系方式", required = false)
    @Column(name = "contact_number")
    private String contactNumber;

    /**
     * 亲属关系
     */
    @ApiModelProperty(value = "亲属关系", required = false)
    @Column(name = "relation")
    private String relation;

    /**
     * 床位编号
     */
    @ApiModelProperty(value = "床位编号", required = false)
    @Column(name = "bed_code")
    private String bedCode;

    /**
     * 楼层编号
     */
    @ApiModelProperty(value = "楼层编号", required = false)
    @Column(name = "floor_code")
    private String floorCode;

    /**
     * 过敏史
     */
    @ApiModelProperty(value = "过敏史", required = false)
    @Column(name = "allergy")
    private String allergy;

    /**
     * 既往史
     */
    @ApiModelProperty(value = "既往史", required = false)
    @Column(name = "previous_history")
    private String previousHistory;

    @ApiModelProperty(value = "费用过期时间", required = false)
    @Column(name = "fees_due_date")
    private Date feesDueDate;

    @ApiModelProperty(value = "费用到期状态0：没到期", required = false)
    @Column(name = "fees_due_statue")
    private Integer feesDueStatue;

}

