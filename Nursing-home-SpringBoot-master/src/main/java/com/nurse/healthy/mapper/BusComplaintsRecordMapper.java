package com.nurse.healthy.mapper;

import com.nurse.healthy.base.BaseRepository;
import com.nurse.healthy.model.entity.business.BusComplaintsRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BusComplaintsRecordMapper extends BaseRepository<BusComplaintsRecord> {

    void delComplaint(@Param("id") Long id);
}
