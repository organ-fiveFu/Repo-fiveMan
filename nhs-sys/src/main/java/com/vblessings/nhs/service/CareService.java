package com.vblessings.nhs.service;

import com.vblessings.nhs.model.entity.sys.SysCarerInfo;
import com.vblessings.nhs.model.po.KeyWordPO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.result.UserInfoToken;

public interface CareService {
    /**
     * 新增护工
     * @param sysCarerInfo
     * @param userInfo
     */
    void add(SysCarerInfo sysCarerInfo, UserInfoToken userInfo);

    /**
     * 更新护工
     * @param sysCarerInfo
     * @param userInfo
     */
    void update(SysCarerInfo sysCarerInfo, UserInfoToken userInfo);

    /**
     * 查询护工信息
     * @param keyWordPO
     * @return
     */
    PageVO<SysCarerInfo> select(KeyWordPO keyWordPO);

    /**
     * 删除护工信息
     * @param ids
     */
    void del(String ids);
}
