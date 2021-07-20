package com.nurse.healthy.mapper;

import com.nurse.healthy.base.BaseRepository;
import com.nurse.healthy.model.entity.business.BusInterestGroupRecord;
import com.nurse.healthy.model.po.businessVO.QueryInterestGroupVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusInterestGroupRecordMapper extends BaseRepository<BusInterestGroupRecord> {
    List<BusInterestGroupRecord> selectByTime(QueryInterestGroupVO queryInterestGroupVO);

    void delInterestGroup(@Param("id") Long id);
}
