package com.vblessings.nhs.model.po;

import lombok.Data;

@Data
public class QueryDictPO extends PageParam {
    private String TypeCode;

    private String search;
}
