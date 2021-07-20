package com.nurse.healthy.service;

import com.github.pagehelper.PageInfo;
import com.nurse.healthy.model.entity.base.BasePatientInfo;
import com.nurse.healthy.model.po.QueryBasePatientPO;
import com.nurse.healthy.result.UserInfoToken;

public interface BasePatientInfoService {

    /**
     * 基本信息分页
     * @return
     */
    PageInfo<BasePatientInfo> selectPage(QueryBasePatientPO queryBasePatientPO);

    /**
     * 新增档案
     * @param basePatientInfo
     */
    void add(BasePatientInfo basePatientInfo, UserInfoToken userInfo);

    /**
     * 根据主键更新档案
     * @param basePatientInfo
     * @param userInfo
     */
    void update(BasePatientInfo basePatientInfo, UserInfoToken userInfo);

    /**
     * 删除档案
     * @param id
     * @param userInfo
     */
    void del(Long id, UserInfoToken userInfo);
}
