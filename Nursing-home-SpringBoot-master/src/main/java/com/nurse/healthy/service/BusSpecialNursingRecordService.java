package com.nurse.healthy.service;

import com.nurse.healthy.model.entity.business.BusSpecialNursingRecord;
import com.nurse.healthy.model.po.businessVO.QuerySpecialNursingPO;
import com.nurse.healthy.model.vo.PageVO;
import com.nurse.healthy.result.UserInfoToken;

public interface BusSpecialNursingRecordService {
    void addSpecialNursing(BusSpecialNursingRecord busSpecialNursingRecord, UserInfoToken userInfo);

    PageVO<BusSpecialNursingRecord> pageSpecialNursing(QuerySpecialNursingPO querySpecialNursingPO);

    void updateSpecialNursing(BusSpecialNursingRecord busSpecialNursingRecord, UserInfoToken userInfo);

    void delSpecialNursing(Long id);
}
