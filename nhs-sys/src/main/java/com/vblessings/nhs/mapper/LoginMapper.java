package com.vblessings.nhs.mapper;

import com.vblessings.nhs.base.BaseRepository;
import com.vblessings.nhs.model.entity.sys.SysLogin;
import org.apache.ibatis.annotations.Param;

public interface LoginMapper extends BaseRepository<SysLogin> {
    void resetPassword(@Param("employeeCode") String employeeCode);
}
