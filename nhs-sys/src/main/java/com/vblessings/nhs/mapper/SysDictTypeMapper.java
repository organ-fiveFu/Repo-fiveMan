package com.vblessings.nhs.mapper;

import com.vblessings.nhs.base.BaseRepository;
import com.vblessings.nhs.model.entity.sys.SysDictType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysDictTypeMapper extends BaseRepository<SysDictType> {
    List<SysDictType> selectAllCode();

    void delByTypeCode(@Param("TypeCode") String TypeCode);
}
