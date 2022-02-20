package com.vblessings.nhs.service;

import com.vblessings.nhs.model.entity.business.BusSpecialNursingRecord;
import com.vblessings.nhs.model.po.TimeQueryPO;
import com.vblessings.nhs.model.po.businessVO.QuerySpecialNursingPO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.business.BusSpecialNursingRecordQueryVO;
import com.vblessings.nhs.result.UserInfoToken;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface BusSpecialNursingRecordService {
    void addSpecialNursing(BusSpecialNursingRecord busSpecialNursingRecord, UserInfoToken userInfo);

    PageVO<BusSpecialNursingRecord> pageSpecialNursing(QuerySpecialNursingPO querySpecialNursingPO);

    void updateSpecialNursing(BusSpecialNursingRecord busSpecialNursingRecord, UserInfoToken userInfo);

    void delSpecialNursing(String  ids);

    void exportSpecialNursing(String ids, HttpServletResponse response) throws IOException;

    List<BusSpecialNursingRecordQueryVO> pageSpecialNursingNoToken(TimeQueryPO timeQueryPO);
}
