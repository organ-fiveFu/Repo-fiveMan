package com.nurse.healthy.vo;

/**
 * 自定义返回参数
 */
public enum ResultInfoEnum {
    /**
     * Success
     */
    SUCCESS(200, "提交成功"),
    QUERY_ERROR(201,"必要参数缺少"),
    COMMON_ERROR(202,"系统未知错误");

    /**
     * Response code
     */
    private final Integer code;

    /**
     * Response message
     */
    private final String msg;

    ResultInfoEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

