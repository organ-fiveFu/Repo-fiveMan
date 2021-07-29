package com.nurse.healthy.service;

import com.nurse.healthy.model.entity.business.BusCarerCheck;
import com.nurse.healthy.model.po.businessVO.QueryCheckVO;
import com.nurse.healthy.model.vo.PageVO;
import com.nurse.healthy.result.UserInfoToken;

public interface BusCarerCheckService {

    void addCheck(BusCarerCheck busCarerCheck, UserInfoToken userInfo);

    PageVO<BusCarerCheck> pageCheck(QueryCheckVO queryCheckVO);
}
