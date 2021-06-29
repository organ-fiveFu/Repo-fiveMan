package com.nurse.healthy.model.entity.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "sys_employee_info")
public class SysEmployeeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "", required = false)
    @Id
    private Long id;

    /**
     * 员工编号
     */
    @ApiModelProperty(value = "员工编号", required = false)
    @Column(name = "employee_code")
    private String employeeCode;

    /**
     * 员工姓名
     */
    @ApiModelProperty(value = "员工姓名", required = false)
    @Column(name = "name")
    private String name;

    /**
     * 员工性别
     */
    @ApiModelProperty(value = "员工性别", required = false)
    @Column(name = "sex")
    private String sex;

    /**
     * 员工生日
     */
    @ApiModelProperty(value = "员工生日", required = false)
    @Column(name = "birthday")
    private String birthday;

    /**
     * 员工年纪
     */
    @ApiModelProperty(value = "员工年纪", required = false)
    @Column(name = "age")
    private Integer age;

    /**
     * 员工身份证号
     */
    @ApiModelProperty(value = "员工身份证号", required = false)
    @Column(name = "id_card")
    private String idCard;

    /**
     * 员工联系号码
     */
    @ApiModelProperty(value = "员工联系号码", required = false)
    @Column(name = "contact_number")
    private String contactNumber;

    /**
     * 员工联系地址
     */
    @ApiModelProperty(value = "员工联系地址", required = false)
    @Column(name = "address")
    private String address;

    /**
     * 员工工号密码
     */
    @ApiModelProperty(value = "员工工号密码", required = false)
    @Column(name = "password")
    private String password;

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
     * 查询时添加是否显示停用(1为使用 ，0为停用)
     */
    @ApiModelProperty(value = "查询时添加是否显示停用(0为使用 ，1为停用)", required = false)
    @Column(name = "use_flag")
    private Integer useFlag;


    @ApiModelProperty(value="是否为管理员 0:不是 1:是")
    @Column(name = "is_administrator")
    private Integer isAdministrator;


}
