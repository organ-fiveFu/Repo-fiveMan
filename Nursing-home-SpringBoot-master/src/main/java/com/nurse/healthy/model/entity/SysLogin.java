package com.nurse.healthy.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
@Builder
public class SysLogin {

    @Id
    @Column(name = "id")
    private Long id;

    @ApiModelProperty(value="账号")
    private String accountNumber;

    @ApiModelProperty(value="密码")
    private String password;


}
