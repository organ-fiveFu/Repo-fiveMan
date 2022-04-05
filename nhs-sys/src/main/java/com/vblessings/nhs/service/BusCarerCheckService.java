package com.vblessings.nhs.service;


import com.vblessings.nhs.model.entity.business.BusCarerCheck;
import com.vblessings.nhs.model.po.businessVO.QueryCheckVO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.business.BusCarerCheckVO;
import com.vblessings.nhs.result.UserInfoToken;

import java.util.List;

public interface BusCarerCheckService {

    void addCheck(BusCarerCheck busCarerCheck, UserInfoToken userInfo);

    PageVO<BusCarerCheckVO> pageCheck(QueryCheckVO queryCheckVO);

    void updateCheck(BusCarerCheck busCarerCheck, UserInfoToken userInfo);

    void delCheck(String ids);

    List<BusCarerCheckVO> pageCheckNoToken(QueryCheckVO queryCheckVO);
}
