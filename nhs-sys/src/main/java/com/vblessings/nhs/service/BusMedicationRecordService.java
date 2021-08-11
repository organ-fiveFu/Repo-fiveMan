package com.vblessings.nhs.service;

import com.vblessings.nhs.model.entity.business.BusMedicationRecord;
import com.vblessings.nhs.model.po.business.QueryMedicineRecordPO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.result.UserInfoToken;

public interface BusMedicationRecordService {
    void add(BusMedicationRecord busMedicationRecord, UserInfoToken userInfo);

    void update(BusMedicationRecord busMedicationRecord, UserInfoToken userInfo);

    PageVO<BusMedicationRecord> pageMedicationRecord(QueryMedicineRecordPO queryMedicineRecordPO);

    void delMedicationRecord(String ids);
}
