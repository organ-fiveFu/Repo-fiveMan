package com.nurse.healthy.service;

import com.github.pagehelper.PageInfo;
import com.nurse.healthy.model.entity.business.BusHospitalRecord;
import com.nurse.healthy.result.UserInfoToken;

public interface BusHospitalRecordService {

    void add(BusHospitalRecord busHospitalRecord, UserInfoToken userInfo);

    PageInfo<BusHospitalRecord> select(String name, String businessNo,int pageNum, int pageSize, UserInfoToken userInfo);
}
