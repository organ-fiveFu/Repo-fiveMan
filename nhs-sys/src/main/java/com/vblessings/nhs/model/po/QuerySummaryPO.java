package com.vblessings.nhs.model.po;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class QuerySummaryPO {
    @NotNull
    private Date startTime;

    private Date endTime;

}
