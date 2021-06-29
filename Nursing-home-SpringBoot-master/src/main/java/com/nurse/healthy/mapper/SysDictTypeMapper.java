package com.nurse.healthy.mapper;

import com.nurse.healthy.base.BaseRepository;
import com.nurse.healthy.model.entity.sys.SysDictType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysDictTypeMapper extends BaseRepository<SysDictType> {
    List<SysDictType> selectAllCode();

    void delByTypeCode(@Param("TypeCode") String TypeCode);
}
