package com.vblessings.nhs.service;

import com.vblessings.nhs.model.po.business.*;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.business.BusVitalSignRecordQueryVO;
import com.vblessings.nhs.model.vo.business.BusVitalSignRecordVO;
import com.vblessings.nhs.model.vo.business.BusVitalSignVO;
import com.vblessings.nhs.result.UserInfoToken;

import java.util.List;

public interface BusVitalSignRecordService {

    void addVitalSignRecord(BusVitalSignRecordPO busVitalSignRecordPO, UserInfoToken userInfo);

    void batchUpdateVitalSignRecord(List<BusVitalSignRecordPO> busVitalSignRecordPOS, UserInfoToken userInfo);

    BusVitalSignVO queryVitalSignRecord(QueryVitalSignPO queryVitalSignPO);

    List<BusVitalSignRecordQueryVO> batchQueryVitalSignRecord(QueryBatchVitalSignPO queryBatchVitalSignPO);

    void delVitalSignRecord(String ids);

    PageVO<BusVitalSignRecordPO> pageVitalSignRecord(QueryVitalSignPagePO queryVitalSignPagePO);
}
