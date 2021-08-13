package com.vblessings.nhs.mapper;

import com.vblessings.nhs.base.BaseRepository;
import com.vblessings.nhs.model.entity.business.BusHospitalRecord;
import com.vblessings.nhs.model.po.QueryFigurePO;
import com.vblessings.nhs.model.vo.QuerySummaryVO;
import com.vblessings.nhs.model.vo.TempData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface BusHospitalRecordMapper extends BaseRepository<BusHospitalRecord> {

    void out(@Param("businessNo") String businessNo,@Param("dischargeTime") String dischargeTime);

    List<TempData> queryBrokenLineByMonth(QueryFigurePO queryFigurePO);

    List<TempData> queryBrokenLineByMonth1(QueryFigurePO queryFigurePO);

    List<TempData> queryBrokenLineByYear(QueryFigurePO queryFigurePO);

    List<TempData> queryBrokenLineByYear1(QueryFigurePO queryFigurePO);

    List<TempData> queryCakeByMonth(QueryFigurePO queryFigurePO);

    List<TempData> queryCakeByMonth1(QueryFigurePO queryFigurePO);

    List<TempData> queryCakeByMonth2(QueryFigurePO queryFigurePO);

    List<TempData> queryCakeByYear(QueryFigurePO queryFigurePO);

    List<TempData> queryCakeByYear1(QueryFigurePO queryFigurePO);

    List<TempData> queryCakeByYear2(QueryFigurePO queryFigurePO);
}