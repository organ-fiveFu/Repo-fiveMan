package com.vblessings.nhs.service;

import com.vblessings.nhs.model.po.TimeQueryPO;
import com.vblessings.nhs.model.po.comprehensive.ChangeShiftInsertPO;
import com.vblessings.nhs.model.po.comprehensive.ChangeShiftQueryPO;
import com.vblessings.nhs.model.po.comprehensive.ChangeShiftUpdatePO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.comprehensive.ChangeShiftQueryVO;
import com.vblessings.nhs.result.UserInfoToken;

import java.util.List;

public interface ChangeShiftService {
    boolean insertChangeShift(ChangeShiftInsertPO changeShiftInsertPO, UserInfoToken userInfoToken);

    boolean updateChangeShift(ChangeShiftUpdatePO changeShiftUpdatePO, UserInfoToken userInfoToken);

    PageVO<ChangeShiftQueryVO> queryChangeShiftList(ChangeShiftQueryPO changeShiftQueryPO);

    boolean deleteChangeShift(Long id, UserInfoToken userInfoToken);

    List<ChangeShiftQueryVO> queryChangeShiftNoToken(TimeQueryPO timeQueryPO);
}
