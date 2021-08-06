package com.vblessings.nhs.service;

import com.vblessings.nhs.model.po.business.ExamArchiveInsertPO;
import com.vblessings.nhs.model.po.business.ExamArchiveUpdatePO;
import com.vblessings.nhs.model.vo.business.ExamArchiveQueryVO;
import com.vblessings.nhs.result.UserInfoToken;

public interface BusExamArchiveService {
    /**
     * 保存体检档案
     * @param examArchiveInsertPO 入参
     * @param userInfo token
     */
    boolean save(ExamArchiveInsertPO examArchiveInsertPO, UserInfoToken userInfo);

    /**
     * 查询体检档案
     * @author linxiazhu
     * @date 15:54 2021/7/6
     * @param businessNo   老人档案id
     * @return  com.nurse.healthy.model.vo.business.ExamArchiveQueryVO
     */
    ExamArchiveQueryVO select(String businessNo);

    /**
     * 更新体检档案
     * @author linxiazhu
     * @date 15:30 2021/7/6
     * @param examArchiveUpdatePO  入参
     * @param userInfoToken   token
     * @return  boolean
     */
    boolean update(ExamArchiveUpdatePO examArchiveUpdatePO, UserInfoToken userInfoToken);

    /**
     * 删除体检档案
     * @author linxiazhu
     * @date 16:26 2021/7/8
     * @param businessNo  老人档案id
     * @param userInfoToken   token
     * @return  boolean
     */
    boolean delete(String businessNo, UserInfoToken userInfoToken);
}
