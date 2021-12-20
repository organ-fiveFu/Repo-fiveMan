package com.vblessings.nhs.model.entity.business;

import com.vblessings.nhs.model.typehandler.ListStringHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "bus_take_medicine_record")
public class BusTakeMedicineRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "", required = false)
    @Id
    private Long id;

    /**
     * 病区
     */
    @ApiModelProperty(value = "病区", required = false)
    @Column(name = "ward")
    private String ward;

    /**
     * 床号
     */
    @ApiModelProperty(value = "床号", required = false)
    @Column(name = "bed_code")
    private String bedCode;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", required = false)
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
     * 医院诊断
     */
    @ApiModelProperty(value = "医院诊断", required = false)
    @Column(name = "hospital_diagnosis")
    private String hospitalDiagnosis;

    /**
     * 带药日期
     */
    @ApiModelProperty(value = "带药日期", required = false)
    @Column(name = "take_medicine_date")
    private Date takeMedicineDate;

    /**
     * 药品名称
     */
    @ApiModelProperty(value = "药品名称", required = false)
    @Column(name = "drug_name")
    private String drugName;

    /**
     * 计量
     */
    @ApiModelProperty(value = "计量", required = false)
    @Column(name = "measure")
    private String measure;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量", required = false)
    @Column(name = "acount")
    private BigDecimal acount;

    /**
     * 过期时间
     */
    @ApiModelProperty(value = "过期时间", required = false)
    @Column(name = "expiry_date")
    private Date expiryDate;

    /**
     * 家属签名
     */
    @ApiModelProperty(value = "家属签名", required = false)
    @Column(name = "family_sign")
    private String familySign;


    /**
     * 护士签名
     */
    @ApiModelProperty(value = "护士签名", required = false)
    @Column(name = "nursing_sign")
    private String nursingSign;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = false)
    @Column(name = "remark")
    private String remark;

    /**
     * 用法
     */
    @ApiModelProperty(value = "用法", required = false)
    @Column(name = "use_way")
    private String useWay;

    /**
     * 用量
     */
    @ApiModelProperty(value = "用量", required = false)
    @Column(name = "dosage")
    private String dosage;

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
     * 是否自带药
     */
    @ApiModelProperty(value = "是否自带药", required = false)
    @Column(name = "is_taken")
    private Integer isTaken;

    @ApiModelProperty(value = "住院号", required = false)
    @Column(name = "business_no")
    private String businessNo;

}
