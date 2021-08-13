package com.vblessings.nhs.service;

import com.vblessings.nhs.model.po.page.PageQueryPO;
import com.vblessings.nhs.model.po.page.PatientQueryPO;
import com.vblessings.nhs.model.vo.page.BedPageQueryVO;
import com.vblessings.nhs.model.vo.page.PageQueryVO;
import com.vblessings.nhs.model.vo.page.PatientQueryVO;
import com.vblessings.nhs.result.UserInfoToken;

import java.util.List;

public interface PageService {
    BedPageQueryVO queryBedPage(PageQueryPO pageQueryPO);

    List<PageQueryVO> queryPageList(PageQueryPO pageQueryPO, UserInfoToken userInfoToken);

    List<PatientQueryVO> queryPatientList(PatientQueryPO patientQueryPO, UserInfoToken userInfoToken);
}
