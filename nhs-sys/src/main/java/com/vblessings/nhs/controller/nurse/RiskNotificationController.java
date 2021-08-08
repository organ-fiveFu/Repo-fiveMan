package com.vblessings.nhs.controller.nurse;

import com.alibaba.fastjson.JSON;
import com.vblessings.nhs.annoation.CurrentUser;
import com.vblessings.nhs.model.po.nurse.RiskNotificationInsertPO;
import com.vblessings.nhs.model.po.nurse.RiskNotificationQueryPO;
import com.vblessings.nhs.model.po.nurse.RiskNotificationUpdatePO;
import com.vblessings.nhs.model.result.ResultBody;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.nurse.RiskNotificationQueryVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.RiskNotificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

@RestController
@RequestMapping("/risk-notification")
@Api(tags = "风险告知书")
@Slf4j
public class RiskNotificationController {

    @Resource
    private RiskNotificationService riskNotificationService;

    @PostMapping("/insert")
    @ApiOperation(value = "新增风险告知书")
    public ResultBody<Boolean> insertRiskNotification(@RequestBody RiskNotificationInsertPO riskNotificationInsertPO, @CurrentUser @ApiIgnore UserInfoToken userInfoToken){
        log.info("新增风险告知书,入参riskNotificationInsertPO:{}", JSON.toJSONString(riskNotificationInsertPO));
        return ResultBody.newSuccessInstance(riskNotificationService.insertRiskNotification(riskNotificationInsertPO, userInfoToken));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新风险告知书")
    public ResultBody<Boolean> updateRiskNotification(@RequestBody RiskNotificationUpdatePO riskNotificationUpdatePO, @CurrentUser @ApiIgnore UserInfoToken userInfoToken){
        log.info("更新风险告知书,入参riskNotificationUpdatePO:{}", JSON.toJSONString(riskNotificationUpdatePO));
        return ResultBody.newSuccessInstance(riskNotificationService.updateRiskNotification(riskNotificationUpdatePO, userInfoToken));
    }

    @GetMapping("/query")
    @ApiOperation(value = "根据住院号去查询风险告知书")
    public ResultBody<RiskNotificationQueryVO> queryRiskNotificationByBusinessNo(String businessNo){
        log.info("根据住院号去查询风险告知书,入参businessNo:" + businessNo);
        return ResultBody.newSuccessInstance(riskNotificationService.queryRiskNotificationByBusinessNo(businessNo));
    }

    @PostMapping("/query/page")
    @ApiOperation(value = "分页查询风险告知书")
    public ResultBody<PageVO<RiskNotificationQueryVO>> queryRiskNotification(@RequestBody RiskNotificationQueryPO riskNotificationQueryPO){
        log.info("分页查询风险告知书,入参riskNotificationQueryPO:{}", JSON.toJSONString(riskNotificationQueryPO));
        return ResultBody.newSuccessInstance(riskNotificationService.queryRiskNotification(riskNotificationQueryPO));
    }
}
