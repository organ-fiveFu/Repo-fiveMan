package com.vblessings.nhs.controller.nurse;

import com.alibaba.fastjson.JSON;
import com.vblessings.nhs.annoation.CurrentUser;
import com.vblessings.nhs.model.po.nurse.ContractInsertPO;
import com.vblessings.nhs.model.po.nurse.ContractUpdatePO;
import com.vblessings.nhs.model.po.nurse.RiskNotificationQueryPO;
import com.vblessings.nhs.model.result.ResultBody;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.nurse.ContractQueryVO;
import com.vblessings.nhs.model.vo.nurse.RiskNotificationQueryVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.ContractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

@RestController
@RequestMapping("/contract")
@Api(tags = "合同")
@Slf4j
public class ContractController {

    @Resource
    private ContractService contractService;

    @PostMapping("/insert")
    @ApiOperation(value = "新增合同信息")
    public ResultBody<Boolean> insertContract(@RequestBody ContractInsertPO contractInsertPO, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("新增合同信息,入参contractInsertPO:{}", JSON.toJSONString(contractInsertPO));
        return ResultBody.newSuccessInstance(contractService.insertContract(contractInsertPO, userInfoToken));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新合同信息")
    public ResultBody<Boolean> updateContract(@RequestBody ContractUpdatePO contractUpdatePO, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("更新合同信息,入参contractUpdatePO:{}", JSON.toJSONString(contractUpdatePO));
        return ResultBody.newSuccessInstance(contractService.updateContract(contractUpdatePO, userInfoToken));
    }

    @GetMapping("/query")
    @ApiOperation(value = "根据住院号去查询合同")
    public ResultBody<ContractQueryVO> queryContractByBusinessNo(String businessNo){
        log.info("根据住院号去查询合同,入参businessNo:" + businessNo);
        return ResultBody.newSuccessInstance(contractService.queryContractByBusinessNo(businessNo));
    }

    @PostMapping("/query/page")
    @ApiOperation(value = "分页查询合同")
    public ResultBody<PageVO<ContractQueryVO>> queryContract(@RequestBody RiskNotificationQueryPO riskNotificationQueryPO){
        log.info("分页查询合同,入参riskNotificationQueryPO:{}", JSON.toJSONString(riskNotificationQueryPO));
        return ResultBody.newSuccessInstance(contractService.queryContract(riskNotificationQueryPO));
    }
}
