package com.vblessings.nhs.service;

import com.vblessings.nhs.model.po.TimeQueryPO;
import com.vblessings.nhs.model.po.business.*;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.business.BusNursingRecordQueryVO;
import com.vblessings.nhs.model.vo.business.BusVitalSignVO;
import com.vblessings.nhs.result.UserInfoToken;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface BusNursingRecordService {
    void addNursingRecord(BusNursingRecordPO busNursingRecordPO, UserInfoToken userInfo);

    PageVO<BusNursingRecordPO> pageNursingRecord(QueryNursingRecordPO queryNursingRecordPO);

    void updateNursingRecord(BusNursingRecordPO busNursingRecordPO, UserInfoToken userInfo);

    void delNursingRecord(String ids);

    void batchUpdateNursingRecord(List<BusNursingRecordPO> busNursingRecordPOS, UserInfoToken userInfo);

    BusVitalSignVO queryVitalSignRecord(QueryVitalSignPO queryVitalSignPO);

    List<BusNursingRecordQueryVO> batchQueryNursingRecord(QueryBatchVitalSignPO queryBatchVitalSignPO);

    List<BusNursingRecordPO> nursingRecordByTimePoint(QueryNursingRecordByTimePO queryNursingRecordByTimePO);

    void exportNursingRecord(String ids, HttpServletResponse response) throws IOException;

    List<BusNursingRecordQueryVO> batchQueryNursingRecordNoToken(TimeQueryPO timeQueryPO);
}
