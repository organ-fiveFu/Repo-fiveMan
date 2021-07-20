package com.nurse.healthy.mapper;

import com.nurse.healthy.base.BaseRepository;
import com.nurse.healthy.model.entity.business.BusDonationRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BusDonationRecordMapper extends BaseRepository<BusDonationRecord> {
    void delDonation(@Param("id") Long id);
}
