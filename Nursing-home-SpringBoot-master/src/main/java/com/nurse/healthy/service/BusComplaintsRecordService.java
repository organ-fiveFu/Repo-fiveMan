package com.nurse.healthy.service;

import com.nurse.healthy.model.entity.business.BusComplaintsRecord;
import com.nurse.healthy.model.po.businessVO.QueryComplaintVO;
import com.nurse.healthy.model.vo.PageVO;
import com.nurse.healthy.result.UserInfoToken;

public interface BusComplaintsRecordService {
    void addComplaint(BusComplaintsRecord busComplaintsRecord, UserInfoToken userInfo);

    PageVO<BusComplaintsRecord> pageComplaint(QueryComplaintVO queryComplaintVO);

    void updateComplaint(BusComplaintsRecord busComplaintsRecord, UserInfoToken userInfo);

    void delComplaint(Long id);
}
