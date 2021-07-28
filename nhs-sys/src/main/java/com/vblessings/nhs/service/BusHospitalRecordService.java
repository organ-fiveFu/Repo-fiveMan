package com.vblessings.nhs.service;

import com.github.pagehelper.PageInfo;
import com.vblessings.nhs.model.entity.business.BusHospitalRecord;
import com.vblessings.nhs.result.UserInfoToken;

public interface BusHospitalRecordService {

    void add(BusHospitalRecord busHospitalRecord, UserInfoToken userInfo);

    PageInfo<BusHospitalRecord> select(String name, String businessNo,int pageNum, int pageSize, UserInfoToken userInfo);
}
