package com.nurse.healthy.model.entity.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Data
public class SysEmployeeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Long id;

    @ApiModelProperty(value="工号")
    private String employeeCode;

    @ApiModelProperty(value="员工姓名")
    private String name;

    @ApiModelProperty(value="员工性别")
    private String sex;

    @ApiModelProperty(value="员工年龄")
    private Integer age;

    @ApiModelProperty(value="员工身份证号码")
    private String idCard;

    @ApiModelProperty(value="员工联系号码")
    private String contactNumber;

    @ApiModelProperty(value="员工联系地址")
    private String address;

    @ApiModelProperty(value="创建人")
    private BigInteger creatorId;

    @ApiModelProperty(value="更新人")
    private BigInteger updaterId;

    @ApiModelProperty(value="创建时间")
    private Date createTime;

    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    @ApiModelProperty(value="是否启用")
    private Integer isDel;

    @ApiModelProperty(value="密码")
    private String password;
}
