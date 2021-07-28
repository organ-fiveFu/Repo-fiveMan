package com.vblessings.nhs.model.po;

import lombok.Data;

@Data
public class QueryBasePatientPO {

    private String name;

    private Long archiveId;

    private Integer pageNum;

    private Integer pageSize;
}
