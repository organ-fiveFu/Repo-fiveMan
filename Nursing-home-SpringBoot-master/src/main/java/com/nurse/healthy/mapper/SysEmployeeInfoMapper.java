package com.nurse.healthy.mapper;

import com.nurse.healthy.base.BaseRepository;
import com.nurse.healthy.model.entity.sys.SysEmployeeInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysEmployeeInfoMapper extends BaseRepository<SysEmployeeInfo> {
    List<String> selectAllCode();

    void del(String[] ids);

    void resetPassword(@Param("employeeCode") String employeeCode);
}
