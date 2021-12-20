package com.vblessings.nhs.service;

import com.github.pagehelper.PageInfo;
import com.vblessings.nhs.model.entity.base.BasePatientInfo;
import com.vblessings.nhs.model.po.QueryBasePatientPO;
import com.vblessings.nhs.result.UserInfoToken;

import java.util.List;

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
     * @param ids
     * @param
     */
    void del(String ids);

    List<BasePatientInfo> baseArchiveList(String name);

    PageInfo<BasePatientInfo> baseArchivePageOut(QueryBasePatientPO queryBasePatientPO);
}
