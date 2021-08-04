package com.vblessings.nhs.service;

import com.vblessings.nhs.model.po.comprehensive.AdministrativeInsertPO;
import com.vblessings.nhs.model.po.comprehensive.AdministrativeQueryPO;
import com.vblessings.nhs.model.po.comprehensive.AdministrativeUpdatePO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.comprehensive.AdministrativeQueryVO;
import com.vblessings.nhs.result.UserInfoToken;

public interface AdministrativeService {
    boolean insertAdministrative(AdministrativeInsertPO administrativeInsertPO, UserInfoToken userInfoToken);

    boolean updateAdministrative(AdministrativeUpdatePO administrativeUpdatePO, UserInfoToken userInfoToken);

    PageVO<AdministrativeQueryVO> queryAdministrativeList(AdministrativeQueryPO administrativeQueryPO);

    boolean deleteAdministrative(Long id, UserInfoToken userInfoToken);
}
