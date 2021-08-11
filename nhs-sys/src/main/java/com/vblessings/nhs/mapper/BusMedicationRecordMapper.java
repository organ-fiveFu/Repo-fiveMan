package com.vblessings.nhs.mapper;

import com.vblessings.nhs.base.BaseRepository;
import com.vblessings.nhs.model.entity.business.BusInterestGroupRecord;
import com.vblessings.nhs.model.entity.business.BusMedicationRecord;

public interface BusMedicationRecordMapper extends BaseRepository<BusMedicationRecord> {
    void del(String[] id);
}
