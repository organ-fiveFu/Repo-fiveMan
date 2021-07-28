package com.vblessings.nhs.service;

import com.vblessings.nhs.model.entity.business.BusInterestGroupRecord;
import com.vblessings.nhs.model.po.businessVO.QueryInterestGroupVO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.result.UserInfoToken;

public interface BusInterestGroupRecordService {
    void addInterestGroup(BusInterestGroupRecord busDonationRecord, UserInfoToken userInfo);

    PageVO<BusInterestGroupRecord> pageInterestGroup(QueryInterestGroupVO queryInterestGroupVO);

    void updateInterestGroup(BusInterestGroupRecord busInterestGroupRecord, UserInfoToken userInfo);

    void delInterestGroup(Long id);
}
