package com.vblessings.nhs.service;

import com.vblessings.nhs.model.po.nurse.ContractInsertPO;
import com.vblessings.nhs.model.po.nurse.ContractUpdatePO;
import com.vblessings.nhs.model.po.nurse.RiskNotificationQueryPO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.nurse.ContractQueryVO;
import com.vblessings.nhs.result.UserInfoToken;

public interface ContractService {
    boolean insertContract(ContractInsertPO contractInsertPO, UserInfoToken userInfoToken);

    boolean updateContract(ContractUpdatePO contractUpdatePO, UserInfoToken userInfoToken);

    ContractQueryVO queryContractByBusinessNo(String businessNo);

    PageVO<ContractQueryVO> queryContract(RiskNotificationQueryPO riskNotificationQueryPO);
}
