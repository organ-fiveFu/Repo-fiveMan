package com.vblessings.nhs.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoToken implements Serializable {

    /**
     *鉴权token
     */
    private String token;


    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    /**
     * 工号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private String employeeCode;

    /**
     * 姓名
     */
    private String name;


}
