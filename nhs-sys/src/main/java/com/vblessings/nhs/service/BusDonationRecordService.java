package com.vblessings.nhs.service;

import com.vblessings.nhs.model.entity.business.BusDonationRecord;
import com.vblessings.nhs.model.po.businessVO.QueryDonationPO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.business.BusDonationRecordVO;
import com.vblessings.nhs.result.UserInfoToken;

public interface BusDonationRecordService {

    void donation(BusDonationRecord busDonationRecord, UserInfoToken userInfo);


    PageVO<BusDonationRecordVO> pageDonation(QueryDonationPO queryDonationPO);


    void updateDonation(BusDonationRecord busDonationRecord,UserInfoToken userInfo);


    void delDonation(String ids);
}
