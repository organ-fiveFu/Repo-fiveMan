package com.vblessings.nhs.mapper;

import com.vblessings.nhs.base.BaseRepository;
import com.vblessings.nhs.model.entity.business.BusSatisfactionMeasurement;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BusSatisfactionMeasurementMapper extends BaseRepository<BusSatisfactionMeasurement> {
    List<BusSatisfactionMeasurement> selectByTime(@Param("phone") String phone);
}
