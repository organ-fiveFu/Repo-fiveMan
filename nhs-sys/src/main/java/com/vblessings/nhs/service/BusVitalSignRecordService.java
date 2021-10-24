package com.vblessings.nhs.service;

import com.vblessings.nhs.model.po.business.*;
import com.vblessings.nhs.model.vo.business.BusVitalSignRecordQueryVO;
import com.vblessings.nhs.model.vo.nurse.VitalSignRecordVO;
import com.vblessings.nhs.result.UserInfoToken;

import java.util.List;

public interface BusVitalSignRecordService {

    void addVitalSignRecord(BusVitalSignRecordPO busVitalSignRecordPO, UserInfoToken userInfo);

    void batchUpdateVitalSignRecord(List<BusVitalSignRecordPO> busVitalSignRecordPOS, UserInfoToken userInfo);

    VitalSignRecordVO queryVitalSignRecord(QueryVitalSignPO queryVitalSignPO);

    List<BusVitalSignRecordQueryVO> batchQueryVitalSignRecord(QueryBatchVitalSignPO queryBatchVitalSignPO);

    void delVitalSignRecord(String ids);

    List<BusVitalSignRecordPO> queryPatientVitalSignRecord(QueryVitalSignPagePO queryVitalSignPagePO);
}
