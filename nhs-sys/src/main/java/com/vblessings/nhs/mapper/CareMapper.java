package com.vblessings.nhs.mapper;

import com.vblessings.nhs.base.BaseRepository;
import com.vblessings.nhs.model.entity.sys.SysCarerInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareMapper extends BaseRepository<SysCarerInfo> {
    List<String> selectAllCode();

    void del(String[] ids);
}
