package com.nurse.healthy.mapper;

import com.nurse.healthy.base.BaseRepository;
import com.nurse.healthy.model.entity.sys.SysDictData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysDictDataMapper extends BaseRepository<SysDictData> {
    List<String> selectByTypeCode(String dictTypeCode);

    void updateIsDel(@Param("list") List<String> dictCodes);
}
