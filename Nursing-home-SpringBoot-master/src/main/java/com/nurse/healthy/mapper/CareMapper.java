package com.nurse.healthy.mapper;

import com.nurse.healthy.base.BaseRepository;
import com.nurse.healthy.model.entity.business.BusExamSurgicalArchive;
import com.nurse.healthy.model.entity.sys.SysCarerInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareMapper extends BaseRepository<SysCarerInfo> {
    List<String> selectAllCode();

    void del(String[] ids);
}
