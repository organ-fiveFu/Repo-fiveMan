package com.vblessings.nhs.model.entity.business;

import com.vblessings.nhs.model.typehandler.ListStringHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "bus_exam_archive")
public class BusExamArchive implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "", required = false)
    @Id
    private Long id;

    /**
     * 住院号
     */
    @ApiModelProperty(value = "住院号", required = false)
    @Column(name = "business_no")
    private String businessNo;

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
     * 老人联系方式
     */
    @ApiModelProperty(value = "老人联系方式", required = false)
    @Column(name = "contact_number")
    private String contactNumber;

    /**
     * 受教育程度
     */
    @ApiModelProperty(value = "受教育程度", required = false)
    @Column(name = "education")
    private String education;

    /**
     * 既往病史编码
     */
    @ApiModelProperty(value = "既往病史编码", required = false)
    @Column(name = "medical_history_code")
    @ColumnType(typeHandler = ListStringHandler.class)
    private List<String> medicalHistoryCode;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = false)
    @Column(name = "remark")
    private String remark;

    /**
     * 体格检查
     */
    @ApiModelProperty(value = "体格检查", required = false)
    @Column(name = "physical_check")
    private String physicalCheck;

    /**
     * 主检医生签字
     */
    @ApiModelProperty(value = "主检医生签字", required = false)
    @Column(name = "main_doctor_sign")
    private String mainDoctorSign;

    /**
     * 签字时间
     */
    @ApiModelProperty(value = "签字时间", required = false)
    @Column(name = "sign_time")
    private Date signTime;

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

    @ApiModelProperty(value = "老人档案id", required = false)
    @Column(name = "archive_id")
    private Long archiveId;

    @ApiModelProperty(value = "既往病史名称", required = false)
    @Column(name = "medical_history_name")
    @ColumnType(typeHandler = ListStringHandler.class)
    private List<String> medicalHistoryName;

}
