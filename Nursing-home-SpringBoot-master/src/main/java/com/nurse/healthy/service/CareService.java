package com.nurse.healthy.service;

import com.nurse.healthy.model.entity.sys.SysCarerInfo;
import com.nurse.healthy.model.po.KeyWordPO;
import com.nurse.healthy.model.vo.PageVO;
import com.nurse.healthy.result.UserInfoToken;

import java.util.List;

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
