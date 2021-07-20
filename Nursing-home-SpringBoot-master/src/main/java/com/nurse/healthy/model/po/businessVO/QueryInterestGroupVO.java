package com.nurse.healthy.model.po.businessVO;

import com.nurse.healthy.model.po.PageParam;
import lombok.Data;

import java.util.Date;

@Data
public class QueryInterestGroupVO extends PageParam {

    private String search;

    private Date groupActivityDate;
}
