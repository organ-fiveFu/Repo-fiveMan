package com.vblessings.nhs.service;

import com.vblessings.nhs.model.entity.business.BusMedicationRecord;
import com.vblessings.nhs.model.po.TimeQueryPO;
import com.vblessings.nhs.model.po.business.QueryMedicineRecordPO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.business.MedicineRecordReportQueryVO;
import com.vblessings.nhs.result.UserInfoToken;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface BusMedicationRecordService {
    void add(BusMedicationRecord busMedicationRecord, UserInfoToken userInfo);

    void update(BusMedicationRecord busMedicationRecord, UserInfoToken userInfo);

    PageVO<BusMedicationRecord> pageMedicationRecord(QueryMedicineRecordPO queryMedicineRecordPO);

    void delMedicationRecord(String ids);

    void exportMedicationRecord(String ids, HttpServletResponse response) throws IOException;

    List<MedicineRecordReportQueryVO> queryMedicationRecordListGetListNoToken(TimeQueryPO timeQueryPO);
}
