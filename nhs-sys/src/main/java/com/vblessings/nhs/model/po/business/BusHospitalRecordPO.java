package com.vblessings.nhs.model.po.business;

import com.vblessings.nhs.model.po.PageParam;
import lombok.Data;

@Data
public class BusHospitalRecordPO extends PageParam {
    private String name;

    private String businessNo;

    private Integer statue;

}
