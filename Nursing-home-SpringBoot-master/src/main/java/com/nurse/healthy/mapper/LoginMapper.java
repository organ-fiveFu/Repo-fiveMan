package com.nurse.healthy.mapper;

import com.nurse.healthy.base.BaseRepository;
import com.nurse.healthy.model.entity.sys.SysLogin;
import org.apache.ibatis.annotations.Param;

public interface LoginMapper extends BaseRepository<SysLogin> {
    void resetPassword(@Param("employeeCode") String employeeCode);
}
