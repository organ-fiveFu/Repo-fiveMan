package com.vblessings.nhs.service;

import com.vblessings.nhs.model.po.business.*;
import com.vblessings.nhs.model.vo.business.BusVitalSignRecordVO;
import com.vblessings.nhs.result.UserInfoToken;

import java.util.List;

public interface BusVitalSignRecordService {

    void addVitalSignRecord(BusVitalSignRecordPO busVitalSignRecordPO, UserInfoToken userInfo);

    void batchUpdateVitalSignRecord(List<BusVitalSignRecordPO> busVitalSignRecordPOS, UserInfoToken userInfo);

    List<BusVitalSignRecordPO> queryVitalSignRecord(QueryVitalSignPO queryVitalSignPO);

    List<BusVitalSignRecordVO> batchQueryVitalSignRecord(QueryBatchVitalSignPO queryBatchVitalSignPO);

    void delVitalSignRecord(String ids);
}
