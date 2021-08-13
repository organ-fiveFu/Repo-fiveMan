package com.vblessings.nhs.mapper;

import com.vblessings.nhs.base.BaseRepository;
import com.vblessings.nhs.model.entity.base.BasePatientInfo;
import com.vblessings.nhs.model.po.page.PatientQueryPO;
import com.vblessings.nhs.model.vo.page.PatientQueryVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasePatientInfoMapper extends BaseRepository<BasePatientInfo> {
    void del(String[] id);

    List<BasePatientInfo> baseArchiveList(@Param("name") String name);

    List<BasePatientInfo> baseArchiveListCopy(String name);

    List<PatientQueryVO> queryPatientList(PatientQueryPO patientQueryPO);
}
