package com.vblessings.nhs.service;


import com.vblessings.nhs.model.po.business.BusMenuBatchUpdatePO;
import com.vblessings.nhs.model.po.business.BusMenuSelectPO;
import com.vblessings.nhs.model.vo.business.BusMenuSelectVO;
import com.vblessings.nhs.result.UserInfoToken;

import java.util.List;


public interface MenuService {

    /**
     * 批量更新菜谱
     * @param busMenuBatchUpdatePO      菜谱入参
     * @param userInfo                  token
     */
    void batchUpdate(BusMenuBatchUpdatePO busMenuBatchUpdatePO, UserInfoToken userInfo);

    /**
     * 查询菜谱
     * @param busMenuSelectPO           查询菜谱入参
     * @param userInfo                  token
     */
    List<BusMenuSelectVO> selectMenuInfo(BusMenuSelectPO busMenuSelectPO, UserInfoToken userInfo);
}
