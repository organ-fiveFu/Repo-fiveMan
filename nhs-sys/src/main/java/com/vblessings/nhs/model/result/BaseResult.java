package com.vblessings.nhs.model.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.vblessings.nhs.exception.IResponseEnum;
import com.vblessings.nhs.exception.ResponseEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * REST API 返回结果
 * </p>
 *
 * @author gourd.hu
 * @since 2018-11-08
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResult<T> implements Serializable {

    @ApiModelProperty("响应码")
    private Integer status;

    @ApiModelProperty("是否成功")
    private Boolean success;

    @ApiModelProperty("响应消息")
    private String message;

    @ApiModelProperty("响应数据")
    private T data;

    @ApiModelProperty("响应时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    public static BaseResult<Boolean> success(){
        return success(null);
    }

    public static <T> BaseResult<T> success(T data){
        return fail(ResponseEnum.OK,data);
    }

    public static <T> BaseResult<T> success(String message, T data ){
        return fail(ResponseEnum.OK,message,data,null);
    }

    public static BaseResult<Boolean> fail() {
        return fail(ResponseEnum.INTERNAL_SERVER_ERROR);
    }

    public static <T> BaseResult<T> fail(IResponseEnum iResponseEnum){
        return fail(iResponseEnum,null);
    }

    public static <T> BaseResult<T> fail(IResponseEnum iResponseEnum, String message){
        return fail(iResponseEnum,message,null,null);
    }

    public static <T> BaseResult<T> fail(IResponseEnum iResponseEnum, T data){
        return fail(iResponseEnum,null,data,null);
    }

    public static <T> BaseResult<T> fail(IResponseEnum iResponseEnum, String message, T data){
        return fail(iResponseEnum,message,data,null);
    }

    public static <T> BaseResult<T> fail(IResponseEnum iResponseEnum, String message, T data, List<String> errors){
        boolean success = false;
        if (iResponseEnum.getCode() == HttpStatus.OK.value()){
            success = true;
        }
        String apiMessage = iResponseEnum.getMessage();
        if (StringUtils.isBlank(message)){
            message = apiMessage;
        }
        return (BaseResult<T>) BaseResult.builder()
                .status(iResponseEnum.getCode())
                .message(message)
                .data(data)
                .success(success)
                .time(LocalDateTime.now())
                .build();
    }







}