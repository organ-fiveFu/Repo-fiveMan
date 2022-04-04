package com.vblessings.nhs.model.po.businessVO;

import com.vblessings.nhs.model.po.PageParam;
import lombok.Data;

import java.util.Date;

@Data
public class QueryCheckVO extends PageParam {
    private String name;

    private String startTime;

    private String endTime;


}
