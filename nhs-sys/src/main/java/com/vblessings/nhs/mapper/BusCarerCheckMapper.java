package com.vblessings.nhs.mapper;

import com.vblessings.nhs.base.BaseRepository;
import com.vblessings.nhs.model.entity.business.BusCarerCheck;
import com.vblessings.nhs.model.po.businessVO.QueryCheckVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusCarerCheckMapper extends BaseRepository<BusCarerCheck> {
    List<BusCarerCheck> selectByTime(QueryCheckVO queryCheckVO);

    void delCheck(String[] id);
}
