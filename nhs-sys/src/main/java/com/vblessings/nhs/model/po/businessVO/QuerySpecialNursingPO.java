package com.vblessings.nhs.model.po.businessVO;

import com.vblessings.nhs.model.po.PageParam;
import lombok.Data;

@Data
public class QuerySpecialNursingPO extends PageParam {

    private String id;
    private String name;
}
