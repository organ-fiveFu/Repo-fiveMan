package com.vblessings.nhs.mapper;

import com.vblessings.nhs.base.BaseRepository;
import com.vblessings.nhs.model.entity.business.BusDonationRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BusDonationRecordMapper extends BaseRepository<BusDonationRecord> {
    void delDonation(@Param("id") Long id);
}
