package com.vblessings.nhs.service;


import com.vblessings.nhs.model.entity.business.BusCarerCheck;
import com.vblessings.nhs.model.po.businessVO.QueryCheckVO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.result.UserInfoToken;

public interface BusCarerCheckService {

    void addCheck(BusCarerCheck busCarerCheck, UserInfoToken userInfo);

    PageVO<BusCarerCheck> pageCheck(QueryCheckVO queryCheckVO);
}
