package com.nurse.healthy.service;

import com.nurse.healthy.model.entity.business.BusDonationRecord;
import com.nurse.healthy.model.po.businessVO.QueryDonationPO;
import com.nurse.healthy.model.vo.PageVO;
import com.nurse.healthy.result.UserInfoToken;

public interface BusDonationRecordService {

    void donation(BusDonationRecord busDonationRecord, UserInfoToken userInfo);


    PageVO<BusDonationRecord> pageDonation( QueryDonationPO queryDonationPO);


    void updateDonation(BusDonationRecord busDonationRecord,UserInfoToken userInfo);


    void delDonation(Long id);
}
