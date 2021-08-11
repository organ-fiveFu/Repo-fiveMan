package com.vblessings.nhs.service;

import com.vblessings.nhs.model.entity.business.BusSpecialNursingRecord;
import com.vblessings.nhs.model.po.businessVO.QuerySpecialNursingPO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.result.UserInfoToken;

public interface BusSpecialNursingRecordService {
    void addSpecialNursing(BusSpecialNursingRecord busSpecialNursingRecord, UserInfoToken userInfo);

    PageVO<BusSpecialNursingRecord> pageSpecialNursing(QuerySpecialNursingPO querySpecialNursingPO);

    void updateSpecialNursing(BusSpecialNursingRecord busSpecialNursingRecord, UserInfoToken userInfo);

    void delSpecialNursing(String  ids);
}
