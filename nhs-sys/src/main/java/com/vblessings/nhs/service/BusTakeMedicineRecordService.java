package com.vblessings.nhs.service;

import com.vblessings.nhs.model.entity.business.BusTakeMedicineRecord;
import com.vblessings.nhs.model.po.business.QueryTakeMedicineRecord;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.result.UserInfoToken;

public interface BusTakeMedicineRecordService {
    void add(BusTakeMedicineRecord busTakeMedicineRecord, UserInfoToken userInfo);

    void update(BusTakeMedicineRecord busTakeMedicineRecord, UserInfoToken userInfo);

    PageVO<BusTakeMedicineRecord> pageTakeMedicine(QueryTakeMedicineRecord queryTakeMedicineRecord);

    void delTakeMedicine(String ids);
}
