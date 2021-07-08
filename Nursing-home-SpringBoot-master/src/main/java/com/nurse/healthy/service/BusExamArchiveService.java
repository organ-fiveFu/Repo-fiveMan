package com.nurse.healthy.service;

import com.nurse.healthy.model.po.business.ExamArchiveInsertPO;
import com.nurse.healthy.model.po.business.ExamArchiveUpdatePO;
import com.nurse.healthy.model.vo.business.ExamArchiveQueryVO;
import com.nurse.healthy.result.UserInfoToken;

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
     * @param archiveId   老人档案id
     * @return  com.nurse.healthy.model.vo.business.ExamArchiveQueryVO
     */
    ExamArchiveQueryVO select(String archiveId);

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
     * @param archiveId  老人档案id
     * @param userInfoToken   token
     * @return  boolean
     */
    boolean delete(String archiveId, UserInfoToken userInfoToken);
}
