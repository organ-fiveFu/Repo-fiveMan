package com.nurse.healthy.service;

import com.nurse.healthy.model.vo.ExamArchivePO;
import com.nurse.healthy.result.UserInfoToken;
import com.nurse.healthy.vo.ResultBody;

import java.util.List;

public interface BusExamArchiveService {
    /**
     * 保存体检档案
     * @param examArchivePO
     * @param userInfo
     */
    void save(ExamArchivePO examArchivePO, UserInfoToken userInfo);


    List<ExamArchivePO> select(String businessNo);
}
