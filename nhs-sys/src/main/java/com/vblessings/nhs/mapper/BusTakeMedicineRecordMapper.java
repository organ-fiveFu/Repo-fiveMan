package com.vblessings.nhs.mapper;

import com.vblessings.nhs.base.BaseRepository;
import com.vblessings.nhs.model.entity.business.BusTakeMedicineRecord;
import com.vblessings.nhs.model.po.TimeQueryPO;
import com.vblessings.nhs.model.po.business.QueryTakeMedicineRecord;
import com.vblessings.nhs.model.vo.business.TakeMedicineReportQueryVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusTakeMedicineRecordMapper extends BaseRepository<BusTakeMedicineRecord> {
    List<BusTakeMedicineRecord> selectByTime(QueryTakeMedicineRecord queryTakeMedicineRecord);

    void del(String[] id);

    List<TakeMedicineReportQueryVO> queryTakeMedicineNoToken(TimeQueryPO timeQueryPO);
}
