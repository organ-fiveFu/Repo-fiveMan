package com.vblessings.nhs.service;

import com.vblessings.nhs.model.po.nurse.LeaveApplicationInsertPO;
import com.vblessings.nhs.model.po.nurse.LeaveApplicationQueryPO;
import com.vblessings.nhs.model.po.nurse.LeaveApplicationUpdatePO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.nurse.LeaveApplicationQueryVO;
import com.vblessings.nhs.result.UserInfoToken;

public interface LeaveApplicationService {
    boolean insertLeaveApplication(LeaveApplicationInsertPO leaveApplicationInsertPO, UserInfoToken userInfoToken);

    boolean updateLeaveApplication(LeaveApplicationUpdatePO leaveApplicationUpdatePO, UserInfoToken userInfoToken);

    PageVO<LeaveApplicationQueryVO> queryLeaveApplication(LeaveApplicationQueryPO leaveApplicationQueryPO);

    boolean deleteLeaveApplication(Long id, UserInfoToken userInfoToken);
}
