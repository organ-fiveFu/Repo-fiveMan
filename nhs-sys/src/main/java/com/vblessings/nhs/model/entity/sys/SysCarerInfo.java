package com.vblessings.nhs.model.entity.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "sys_carer_info")
public class SysCarerInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "", required = false)
    @Id
    private Long id;

    /**
     * 护工编号
     */
    @ApiModelProperty(value = "护工编号", required = false)
    @Column(name = "care_code")
    private String careCode;

    /**
     * 护工姓名
     */
    @ApiModelProperty(value = "护工姓名", required = false)
    @Column(name = "name")
    private String name;

    /**
     * 护工性别
     */
    @ApiModelProperty(value = "护工性别", required = false)
    @Column(name = "sex")
    private String sex;

    /**
     * 护工生日
     */
    @ApiModelProperty(value = "护工生日", required = false)
    @Column(name = "birthday")
    private String birthday;

    /**
     * 护工年纪
     */
    @ApiModelProperty(value = "护工年纪", required = false)
    @Column(name = "age")
    private Integer age;

    /**
     * 护工身份证号
     */
    @ApiModelProperty(value = "护工身份证号", required = false)
    @Column(name = "id_card")
    private String idCard;

    /**
     * 护工联系号码
     */
    @ApiModelProperty(value = "护工联系号码", required = false)
    @Column(name = "contact_number")
    private String contactNumber;

    /**
     * 护工联系地址
     */
    @ApiModelProperty(value = "护工联系地址", required = false)
    @Column(name = "address")
    private String address;

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

