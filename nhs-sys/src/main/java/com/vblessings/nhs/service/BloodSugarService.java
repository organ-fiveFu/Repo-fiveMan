package com.vblessings.nhs.service;

import com.vblessings.nhs.model.po.nurse.BloodSugarInsertPO;
import com.vblessings.nhs.model.po.nurse.BloodSugarQueryPO;
import com.vblessings.nhs.model.po.nurse.BloodSugarUpdatePO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.nurse.BloodSugarQueryVO;
import com.vblessings.nhs.result.UserInfoToken;

public interface BloodSugarService {
    boolean insertBloodSugar(BloodSugarInsertPO bloodSugarInsertPO, UserInfoToken userInfoToken);

    boolean updateBloodSugar(BloodSugarUpdatePO bloodSugarUpdatePO, UserInfoToken userInfoToken);

    PageVO<BloodSugarQueryVO> queryBloodSugar(BloodSugarQueryPO bloodSugarQueryPO);

    boolean deleteBloodSugar(Long id, UserInfoToken userInfoToken);
}
