package com.vblessings.nhs.model.po.business;

import com.vblessings.nhs.model.po.PageParam;
import lombok.Data;

@Data
public class QueryMedicineRecordPO extends PageParam {

    private String businessNo;

    private String name;
}
