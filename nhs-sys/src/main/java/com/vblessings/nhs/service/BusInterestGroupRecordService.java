package com.vblessings.nhs.service;

import com.vblessings.nhs.model.entity.business.BusInterestGroupRecord;
import com.vblessings.nhs.model.po.BusInterestGroupRecordPO;
import com.vblessings.nhs.model.po.businessVO.QueryInterestGroupVO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.business.BusInterestGroupRecordVO;
import com.vblessings.nhs.result.UserInfoToken;

import java.util.List;

public interface BusInterestGroupRecordService {
    void addInterestGroup(BusInterestGroupRecord busInterestGroupRecord, UserInfoToken userInfo);

    PageVO<BusInterestGroupRecordVO> pageInterestGroup(QueryInterestGroupVO queryInterestGroupVO);

    void updateInterestGroup(BusInterestGroupRecord busInterestGroupRecord, UserInfoToken userInfo);

    void delInterestGroup(String ids);

    List<BusInterestGroupRecordVO> queryInterestGroupNoToken(QueryInterestGroupVO queryInterestGroupVO);
}
