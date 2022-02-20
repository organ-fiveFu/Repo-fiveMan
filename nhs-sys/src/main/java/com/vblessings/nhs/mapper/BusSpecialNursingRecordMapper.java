package com.vblessings.nhs.mapper;

import com.vblessings.nhs.base.BaseRepository;
import com.vblessings.nhs.model.entity.business.BusSpecialNursingRecord;
import com.vblessings.nhs.model.po.TimeQueryPO;
import com.vblessings.nhs.model.vo.business.BusSpecialNursingRecordQueryVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusSpecialNursingRecordMapper extends BaseRepository<BusSpecialNursingRecord> {

    void del(String[] id);

    List<BusSpecialNursingRecordQueryVO> pageSpecialNursingNoToken(TimeQueryPO timeQueryPO);
}
