package com.nurse.healthy.model.po.businessVO;

import com.nurse.healthy.model.po.PageParam;
import lombok.Data;

import java.util.Date;

@Data
public class QueryCheckVO extends PageParam {
    private String name;

    private Date startTime;

    private Date EndTime;


}
