package com.nurse.healthy.model.entity.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "base_patient_info")
public class BasePatientInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "", required = false)
    @Id
    private Long id;

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
     * 老人生日
     */
    @ApiModelProperty(value = "老人生日", required = false)
    @Column(name = "birthday")
    private String birthday;

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
     * 老人联系方式
     */
    @ApiModelProperty(value = "老人联系方式", required = false)
    @Column(name = "contact_number")
    private String contactNumber;

    /**
     * 老人联系地址
     */
    @ApiModelProperty(value = "老人联系地址", required = false)
    @Column(name = "contact_address")
    private String contactAddress;

    /**
     * 受教育程度
     */
    @ApiModelProperty(value = "受教育程度", required = false)
    @Column(name = "education")
    private String education;

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

}

