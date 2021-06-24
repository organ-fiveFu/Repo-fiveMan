package com.nurse.healthy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>返回结果</p>
 *
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum implements BusinessExceptionAssert {

    OK(200, "成功"),
    INTERNAL_SERVER_ERROR(500, "系统异常"),
    BAD_REQUEST(400,"参数不合法"),
    UNAUTHORIZED(401,"无权限"),
    FORBIDDEN(403,"拒绝访问"),
    NOT_FOUND(404, "未找到资源"),
    METHOD_NOT_ALLOWED(405, "方法不支持"),
    ILLEGAL_ACCESS(406, "非法获取"),
    REPEAT_COMMIT(111, "重复提交"),

    TOKEN_NOT_FOUND(2001,"未获取到token"),

    TOKEN_FAILE_GET_INFO(2002,"token获取信息失败，该用户已登出或过期，请重新登录"),

    FTP_REFUSE_CONNECT(5001,"FTP服务器拒绝连接"),
    FILE_PATH_NOT_EXIST(5004,"文件路径不存在:{}"),
    FTP_LOGIN_FAIL(5002,"ftp登录失败"),
    FILE_CREATE_FAIL(5003,"目录创建失败:{}"),
    FILE_UPLOAD_FAIL(5005,"文件上传失败:{}"),
    FILE_DOWNLOAD_FAIL(5005,"文件下载失败:{}"),
    FILE_DELETE_FAIL(5005,"文件删除失败:{}"),
    FILE_INSERT_FAIL(5007,"新增操作失败:{}"),
    FILE_UPDATE_FAIL(5008,"修改操作失败:{}"),
    DATA_TRANSFER_ERROR(50009, "数据转换异常"),

    DATA_NOT_FOUND(6000, "数据不存在或已被删除."),
    ACCOUNT_NOT_FOUND(6001, "账号未注册."),
    ACCOUNT_BEEN_USED(6002, "此账号已被注册."),
    ACCOUNT_PWD_ERROR(6003, "账号或密码错误."),
    TENANT_NOT_FOUND(6004, "承租人信息不存在或已被停用."),
    FIELD_TOO_LARGE(6013, "字段太长,超出数据库字段的长度."),
    FILE_TOO_LARGE(6014, "文件大小超出10MB限制, 请压缩或降低文件质量."),
    TEMPLATE_PARSE_ERROR(6015, "模板解析错误."),
    TEMPLATE_DATA_NULL(6016, "模板参数为空."),
    EXPORT_EXCEL_ERROR(6017, "导出excel失败"),
    EXPORT_PDF_ERROR(6015, "导出pdf失败"),
    EXCEL_NO_DATA(6018, "表格无数据"),
    EXCEL_ANALYZE_FAIL(6019, "解析excel失败"),
    EXCEL_TYPE_ERROR(6020, "文件类型错误"),
    UPLOAD_EXCEL_TEMPLATE_ERROR(6021, "上传模板错误"),
    DATA_INTEGRITY_VIOLATION(6022, "数据库校验异常"),

    SIGN_ERROR(7001, "签名验证失败."),
    APP_KEY_ERROR(7002, "appKey错误."),
    SIGN_EXPIRED(7003, "请求已经失效."),
    APPROVAL_NUMBER(7004, "药品规格重复"),
    DELETED_AUDITED_NOTEXIST(7005, "该数据已删除或不存在"),
    ABNORMAL_DATA_VERIFICATION(7006, "数据校验异常"),
    NAME_ALREADY_EXISTS(7007, "名称已存在"),
    CODE_ALREADY_EXISTS(7008, "编码已存在"),
    DATA_NOTEXIST(7011, "该数据不存在"),
    DATA_DELETED(7012, "该数据已删除"),
    DATA_AUDITED(7013, "已审核的数据不能执行该操作"),

    WEBAPI_SALESYNC_ERROR(8001, "对外接口-同步销售数据失败"),

    PARAM_TO_BE_VERIFIED(9001, "参数待验证"),

    NO_DEFAULT_INVENTORY_ORG(10001, "未配置默认进销存数据源"),

    REFUND_APPLICATION_SUBMITTED(10086, "当前订单已提交过退费申请"),

//    发票专用前端轮训判断状态码，其他业务误用！！！！！！！！！！！！
    NO_GET_PAPER_INVOICE(20901,"未获取到纸质发票记录"),
    ;

    /**
     * 返回码
     */
    private final int code;
    /**
     * 返回消息
     */
    private final String message;
}
