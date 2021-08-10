package com.vblessings.nhs.mapper;

import com.vblessings.nhs.base.BaseRepository;
import com.vblessings.nhs.model.entity.business.BusHospitalRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface BusHospitalRecordMapper extends BaseRepository<BusHospitalRecord> {

    void out(@Param("businessNo") String businessNo,@Param("dischargeTime") String dischargeTime);
}