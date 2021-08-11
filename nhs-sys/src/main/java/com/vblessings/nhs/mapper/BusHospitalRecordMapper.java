package com.vblessings.nhs.mapper;

import com.vblessings.nhs.base.BaseRepository;
import com.vblessings.nhs.model.entity.business.BusHospitalRecord;
import com.vblessings.nhs.model.po.QueryFigurePO;
import com.vblessings.nhs.model.vo.QuerySummaryVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Map;

@Repository
public interface BusHospitalRecordMapper extends BaseRepository<BusHospitalRecord> {

    void out(@Param("businessNo") String businessNo,@Param("dischargeTime") String dischargeTime);

    Map<String, QuerySummaryVO> queryBrokenLineByMonth(QueryFigurePO queryFigurePO);
}