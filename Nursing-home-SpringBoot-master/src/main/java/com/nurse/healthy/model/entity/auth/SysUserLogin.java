package com.nurse.healthy.model.entity.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "sys_login")
public class SysUserLogin {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "", required = false)
    @Id
    private Long id;

    /**
     *
     */
    @ApiModelProperty(value = "", required = false)
    @Column(name = "employee_code")
    private String employeeCode;

    /**
     *
     */
    @ApiModelProperty(value = "", required = false)
    @Column(name = "password")
    private String password;
}
