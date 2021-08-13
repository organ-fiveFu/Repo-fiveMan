package com.vblessings.nhs.model.po.business;

import com.vblessings.nhs.model.po.PageParam;
import lombok.Data;

@Data
public class QueryNursingRecordPO extends PageParam {
    private String name;
}
