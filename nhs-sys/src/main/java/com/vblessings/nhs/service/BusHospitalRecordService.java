package com.vblessings.nhs.service;

import com.github.pagehelper.PageInfo;
import com.vblessings.nhs.model.entity.business.BusHospitalRecord;
import com.vblessings.nhs.model.po.QueryFigurePO;
import com.vblessings.nhs.model.po.QuerySummaryPO;
import com.vblessings.nhs.model.po.business.BusHospitalRecordPO;
import com.vblessings.nhs.model.vo.QuerySummaryVO;
import com.vblessings.nhs.model.vo.TempData;
import com.vblessings.nhs.result.UserInfoToken;

import java.util.List;
import java.util.Map;

public interface BusHospitalRecordService {

    void add(BusHospitalRecord busHospitalRecord, UserInfoToken userInfo);

    PageInfo<BusHospitalRecord> select(BusHospitalRecordPO busHospitalRecordPO, UserInfoToken userInfo);

    void out(String businessNo);

    void update(BusHospitalRecord busHospitalRecord, UserInfoToken userInfo);

    QuerySummaryVO selectTotalByTime(QuerySummaryPO querySummaryPO);

    Map<String, List<TempData>> queryBrokenLine(QueryFigurePO queryFigurePO);


    Map<String, List<TempData>> queryCake(QueryFigurePO queryFigurePO);
}
