package com.vblessings.nhs.enums;

public enum DictTypeEnum {
    SEX("0001", "性别"),
    ROOM_TYPE("0002", "房间类型"),
    ROOM_TOWARD("0003", "房间朝向"),
    CHECK_IN_STATUS("0004", "入住状态"),
    DEPARTMENT("0005", "科室"),
    BLOOD_SAMPLING_STATUS("0006", "血糖采样状态");

    private String code;
    private String value;

    DictTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
