package com.vblessings.nhs.model.po.businessVO;

import com.vblessings.nhs.model.po.PageParam;
import lombok.Data;

@Data
public class QueryComplaintVO extends PageParam {
    private String search;
}
