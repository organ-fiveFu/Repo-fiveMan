package com.vblessings.nhs.mapper;

import com.vblessings.nhs.base.BaseRepository;
import com.vblessings.nhs.model.entity.business.BusComplaintsRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BusComplaintsRecordMapper extends BaseRepository<BusComplaintsRecord> {

    void delComplaint(String[] id);
}
