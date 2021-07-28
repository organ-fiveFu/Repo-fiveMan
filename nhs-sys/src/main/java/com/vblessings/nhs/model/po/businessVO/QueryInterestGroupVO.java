package com.vblessings.nhs.model.po.businessVO;

import com.vblessings.nhs.model.po.PageParam;
import lombok.Data;

import java.util.Date;

@Data
public class QueryInterestGroupVO extends PageParam {

    private String search;

    private Date groupActivityDate;
}
