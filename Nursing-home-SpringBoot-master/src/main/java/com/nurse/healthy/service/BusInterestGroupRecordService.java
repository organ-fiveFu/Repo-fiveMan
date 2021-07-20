package com.nurse.healthy.service;

import com.nurse.healthy.model.entity.business.BusInterestGroupRecord;
import com.nurse.healthy.model.po.businessVO.QueryInterestGroupVO;
import com.nurse.healthy.model.vo.PageVO;
import com.nurse.healthy.result.UserInfoToken;

public interface BusInterestGroupRecordService {
    void addInterestGroup(BusInterestGroupRecord busDonationRecord, UserInfoToken userInfo);

    PageVO<BusInterestGroupRecord> pageInterestGroup(QueryInterestGroupVO queryInterestGroupVO);

    void updateInterestGroup(BusInterestGroupRecord busInterestGroupRecord, UserInfoToken userInfo);

    void delInterestGroup(Long id);
}
