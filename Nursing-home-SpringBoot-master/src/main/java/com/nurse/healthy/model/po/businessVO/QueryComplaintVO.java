package com.nurse.healthy.model.po.businessVO;

import com.nurse.healthy.model.po.PageParam;
import lombok.Data;

@Data
public class QueryComplaintVO extends PageParam {
    private String search;
}
