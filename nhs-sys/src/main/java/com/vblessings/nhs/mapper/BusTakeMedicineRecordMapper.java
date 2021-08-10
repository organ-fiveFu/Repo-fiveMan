package com.vblessings.nhs.mapper;

import com.vblessings.nhs.base.BaseRepository;
import com.vblessings.nhs.model.entity.business.BusTakeMedicineRecord;
import com.vblessings.nhs.model.po.business.QueryTakeMedicineRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusTakeMedicineRecordMapper extends BaseRepository<BusTakeMedicineRecord> {
    List<BusTakeMedicineRecord> selectByTime(QueryTakeMedicineRecord queryTakeMedicineRecord);

    void del(String[] id);
}
