package com.vblessings.nhs.result;

import java.io.Serializable;

/**
 * 通用返回對象
 */
public class ResultBody<T> implements Serializable {
    /** Response code */
    private final Integer code;
    /** Response message */
    private final String msg;
    /** Response data */
    private final T data;
    /** Empty array */
    private static final int[] EMPTY_ARRAY = new int[0];

    /**
     * Constructs a success response with no detail response data.
     */
    private ResultBody() {
        this.code = ResultInfoEnum.SUCCESS.getCode();
        this.msg = ResultInfoEnum.SUCCESS.getMsg();
        this.data = null;
    }

    /**
     * Constructs a success response with response data.
     *
     * @param resultData response data
     */
    private ResultBody(T resultData) {
        this.code = ResultInfoEnum.SUCCESS.getCode();
        this.msg = ResultInfoEnum.SUCCESS.getMsg();
        this.data = resultData;
    }

    /**
     * Constructs an error response with result info enum.
     *
     * @param errorCode
     * @param errorMsg
     * @param data
     */
    private ResultBody(Integer errorCode, String errorMsg, T data) {
        this.code = errorCode;
        this.msg = errorMsg;
        this.data = data;
    }

    /**
     * Constructs an error response with error code and error message.
     *
     * @param errorCode error code
     * @param errorMsg  error msg
     */
    private ResultBody(Integer errorCode, String errorMsg) {
        this.code = errorCode;
        this.msg = errorMsg;
        this.data = null;
    }



    /**
     * Static factory to new a success response with no detail response data.
     *
     * @return success response body
     */
    public static ResultBody newSuccessInstance() {
        return new ResultBody();
    }

    /**
     * Static factory to new a success response with response data.
     *
     * @param resultData response data
     * @return success response body
     */
    public static<T> ResultBody<T> newSuccessInstance(T resultData) {
        return new ResultBody<>(resultData);
    }

    /**
     * Static factory to new an error response with result info enum.
     *
     * @param errorCode
     * @param errorMsg
     * @param data
     * @return error response body
     */
    public static<T> ResultBody<T> newErrorInstance(Integer errorCode, String errorMsg, T data) {
        return new ResultBody<T>(errorCode, errorMsg, data);
    }

    public static<T> ResultBody<T> newErrorInstance(){
        return new ResultBody<>(100,"error");
    }

    /**
     * Static factory to new an error response with error code and error message.
     *
     * @param errorCode error code
     * @param errorMsg  error msg
     * @return error response body
     */
    public static<T> ResultBody<T> newErrorInstance(Integer errorCode, String errorMsg) {
        return new ResultBody<>(errorCode, errorMsg);
    }
    public static<T> ResultBody<T> newSuccessInstance(Integer errorCode, String errorMsg) {
        return new ResultBody<>(errorCode, errorMsg);
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ResultBody{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

}