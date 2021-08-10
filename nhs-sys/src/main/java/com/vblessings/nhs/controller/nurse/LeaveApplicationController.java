package com.vblessings.nhs.controller.nurse;

import com.alibaba.fastjson.JSON;
import com.vblessings.nhs.annoation.CurrentUser;
import com.vblessings.nhs.model.po.nurse.LeaveApplicationInsertPO;
import com.vblessings.nhs.model.po.nurse.LeaveApplicationQueryPO;
import com.vblessings.nhs.model.po.nurse.LeaveApplicationUpdatePO;
import com.vblessings.nhs.model.result.ResultBody;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.nurse.LeaveApplicationQueryVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.LeaveApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

@RestController
@RequestMapping("/leave-application")
@Api(tags = "请假单")
@Slf4j
public class LeaveApplicationController {

    @Resource
    private LeaveApplicationService leaveApplicationService;

    @PostMapping("/insert")
    @ApiOperation(value = "新增请假单信息")
    public ResultBody<Boolean> insertLeaveApplication(@RequestBody LeaveApplicationInsertPO leaveApplicationInsertPO, @CurrentUser @ApiIgnore UserInfoToken userInfoToken){
        log.info("新增请假单信息,入参leaveApplicationInsertPO:{}", JSON.toJSONString(leaveApplicationInsertPO));
        return ResultBody.newSuccessInstance(leaveApplicationService.insertLeaveApplication(leaveApplicationInsertPO, userInfoToken));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新请假单信息")
    public ResultBody<Boolean> updateLeaveApplication(@RequestBody LeaveApplicationUpdatePO leaveApplicationUpdatePO, @CurrentUser @ApiIgnore UserInfoToken userInfoToken){
        log.info("更新请假单信息,入参leaveApplicationUpdatePO:{}", JSON.toJSONString(leaveApplicationUpdatePO));
        return ResultBody.newSuccessInstance(leaveApplicationService.updateLeaveApplication(leaveApplicationUpdatePO, userInfoToken));
    }

    @GetMapping("/query")
    @ApiOperation(value = "查询请假单信息")
    public ResultBody<PageVO<LeaveApplicationQueryVO>> queryLeaveApplication(LeaveApplicationQueryPO leaveApplicationQueryPO){
        log.info("查询请假单信息,入参leaveApplicationQueryPO:{}", JSON.toJSONString(leaveApplicationQueryPO));
        return ResultBody.newSuccessInstance(leaveApplicationService.queryLeaveApplication(leaveApplicationQueryPO));
    }

    @GetMapping("/delete")
    @ApiOperation(value = "删除请假单信息")
    public ResultBody<Boolean> deleteLeaveApplication(Long id, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("删除请假单信息,id:" + id);
        return ResultBody.newSuccessInstance(leaveApplicationService.deleteLeaveApplication(id, userInfoToken));
    }
}
