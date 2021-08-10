package com.vblessings.nhs.model.po.business;

import com.vblessings.nhs.model.po.PageParam;
import lombok.Data;

import java.util.Date;

@Data
public class QueryTakeMedicineRecord extends PageParam {
    private String name;

    private Date takeMedicineDate;
}
