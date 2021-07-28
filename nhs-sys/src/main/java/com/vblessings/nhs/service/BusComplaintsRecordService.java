package com.vblessings.nhs.service;

import com.vblessings.nhs.model.entity.business.BusComplaintsRecord;
import com.vblessings.nhs.model.po.businessVO.QueryComplaintVO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.result.UserInfoToken;

public interface BusComplaintsRecordService {
    void addComplaint(BusComplaintsRecord busComplaintsRecord, UserInfoToken userInfo);

    PageVO<BusComplaintsRecord> pageComplaint(QueryComplaintVO queryComplaintVO);

    void updateComplaint(BusComplaintsRecord busComplaintsRecord, UserInfoToken userInfo);

    void delComplaint(Long id);
}
