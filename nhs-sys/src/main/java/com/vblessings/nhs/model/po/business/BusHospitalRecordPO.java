package com.vblessings.nhs.model.po.business;

import com.vblessings.nhs.model.po.PageParam;
import lombok.Data;

@Data
public class BusHospitalRecordPO extends PageParam {
    private String name;

    private String businessNo;

    private String buildingCode;

    private String roomCode;

    private String floorCode;

    private String bedCode;


    private Integer status;

}
