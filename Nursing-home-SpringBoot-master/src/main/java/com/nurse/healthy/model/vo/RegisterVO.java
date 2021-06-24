package com.nurse.healthy.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class RegisterVO {

    @NonNull
    @ApiModelProperty(value="账号")
    private String accountNumber;

    @NonNull
    @ApiModelProperty(value="密码")
    private String password;

}
