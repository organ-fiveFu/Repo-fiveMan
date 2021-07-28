package com.vblessings.nhs.mapper;

import com.vblessings.nhs.base.BaseRepository;
import com.vblessings.nhs.model.entity.business.BusInterestGroupRecord;
import com.vblessings.nhs.model.po.businessVO.QueryInterestGroupVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusInterestGroupRecordMapper extends BaseRepository<BusInterestGroupRecord> {
    List<BusInterestGroupRecord> selectByTime(QueryInterestGroupVO queryInterestGroupVO);

    void delInterestGroup(@Param("id") Long id);
}
