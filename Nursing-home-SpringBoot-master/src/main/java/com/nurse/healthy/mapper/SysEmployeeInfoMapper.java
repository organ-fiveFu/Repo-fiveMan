package com.nurse.healthy.mapper;

import com.nurse.healthy.base.BaseRepository;
import com.nurse.healthy.model.entity.sys.SysEmployeeInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysEmployeeInfoMapper extends BaseRepository<SysEmployeeInfo> {
    List<String> selectAllCode();

    void del(@Param("list") List<String> ids);

    void resetPassword(SysEmployeeInfo sysEmployeeInfo);
}
