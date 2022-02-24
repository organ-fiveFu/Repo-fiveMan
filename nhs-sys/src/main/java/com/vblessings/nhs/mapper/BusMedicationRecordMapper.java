package com.vblessings.nhs.mapper;

import com.vblessings.nhs.base.BaseRepository;
import com.vblessings.nhs.model.entity.business.BusMedicationRecord;
import com.vblessings.nhs.model.po.TimeQueryPO;
import com.vblessings.nhs.model.po.business.QueryMedicineRecordPO;
import com.vblessings.nhs.model.vo.business.MedicineRecordReportQueryVO;

import java.util.List;

public interface BusMedicationRecordMapper extends BaseRepository<BusMedicationRecord> {
    void del(String[] id);

    List<MedicineRecordReportQueryVO> queryMedicationRecordListGetListNoToken(TimeQueryPO timeQueryPO);

    List<BusMedicationRecord> pageMedicationRecord(QueryMedicineRecordPO queryMedicineRecordPO);
}
