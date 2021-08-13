package com.vblessings.nhs.service;

import com.vblessings.nhs.model.entity.business.BusNursingRecord;
import com.vblessings.nhs.model.po.business.BusNursingRecordInsertPO;
import com.vblessings.nhs.model.po.business.QueryNursingRecordPO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.result.UserInfoToken;

public interface BusNursingRecordService {
    void addNursingRecord(BusNursingRecordInsertPO busNursingRecordInsertPO, UserInfoToken userInfo);

    PageVO<BusNursingRecord> pageNursingRecord(QueryNursingRecordPO queryNursingRecordPO);

    void updateNursingRecord(BusNursingRecord busNursingRecord, UserInfoToken userInfo);

    void delNursingRecord(String ids);
}
