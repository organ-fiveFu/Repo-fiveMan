package com.vblessings.nhs.service;

import com.vblessings.nhs.model.entity.business.BusTakeMedicineRecord;
import com.vblessings.nhs.model.po.TimeQueryPO;
import com.vblessings.nhs.model.po.business.QueryTakeMedicineRecord;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.business.MedicineRecordReportQueryVO;
import com.vblessings.nhs.model.vo.medicine.TakeMedicineQueryVO;
import com.vblessings.nhs.model.vo.business.TakeMedicineReportQueryVO;
import com.vblessings.nhs.result.UserInfoToken;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface BusTakeMedicineRecordService {
    void add(BusTakeMedicineRecord busTakeMedicineRecord, UserInfoToken userInfo);

    void update(BusTakeMedicineRecord busTakeMedicineRecord, UserInfoToken userInfo);

    PageVO<BusTakeMedicineRecord> pageTakeMedicine(QueryTakeMedicineRecord queryTakeMedicineRecord);

    void delTakeMedicine(String ids);

    void exportTakeMedicine(String  ids, HttpServletResponse response) throws IOException;

    void exportDispensing(String ids, HttpServletResponse response) throws IOException;

    List<TakeMedicineReportQueryVO> queryTakeMedicineNoToken(TimeQueryPO timeQueryPO);

    List<TakeMedicineReportQueryVO> queryTakeMedicationListGetListNoToken(TimeQueryPO timeQueryPO);

}
