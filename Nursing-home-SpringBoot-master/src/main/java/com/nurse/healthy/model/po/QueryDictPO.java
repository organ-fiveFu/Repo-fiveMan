package com.nurse.healthy.model.po;

import lombok.Data;

@Data
public class QueryDictPO extends PageParam {
    private String TypeCode;

    private String search;
}
