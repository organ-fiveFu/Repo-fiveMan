package com.vblessings.nhs.mapper;

import com.vblessings.nhs.base.BaseRepository;
import com.vblessings.nhs.model.entity.business.BusSpecialNursingRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface BusSpecialNursingRecordMapper extends BaseRepository<BusSpecialNursingRecord> {

    void del(String[] id);
}
