package com.nurse.healthy.mapper;

import com.nurse.healthy.base.BaseRepository;
import com.nurse.healthy.model.entity.business.BusCarerCheck;
import com.nurse.healthy.model.po.businessVO.QueryCheckVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusCarerCheckMapper extends BaseRepository<BusCarerCheck> {
    List<BusCarerCheck> selectByTime(QueryCheckVO queryCheckVO);
}
