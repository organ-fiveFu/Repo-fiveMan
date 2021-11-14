package com.vblessings.nhs.enums;

public enum SexEnum {
    MALE("1", "男"),
    FEMALE("2", "女"),
    ;

    private String code;
    private String value;

    SexEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static String getValueFromCode(String code) {
        for (SexEnum sex : SexEnum.values()) {
            if (sex.getCode().equals(code)) {
                return sex.getValue();
            }
        }
        return code;
    }
}
