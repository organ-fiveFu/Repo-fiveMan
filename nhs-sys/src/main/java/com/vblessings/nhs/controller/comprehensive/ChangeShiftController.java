package com.vblessings.nhs.controller.comprehensive;

import com.alibaba.fastjson.JSON;
import com.vblessings.nhs.annoation.CurrentUser;
import com.vblessings.nhs.model.po.comprehensive.ChangeShiftInsertPO;
import com.vblessings.nhs.model.po.comprehensive.ChangeShiftQueryPO;
import com.vblessings.nhs.model.po.comprehensive.ChangeShiftUpdatePO;
import com.vblessings.nhs.model.result.ResultBody;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.comprehensive.ChangeShiftQueryVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.ChangeShiftService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

@RestController
@RequestMapping("/change-shift")
@Api(tags = "交接班")
@Slf4j
public class ChangeShiftController {

    @Resource
    private ChangeShiftService changeShiftService;

    @PostMapping("/insert")
    @ApiOperation(value = "新增交接班")
    public ResultBody<Boolean> insertChangeShift(@RequestBody ChangeShiftInsertPO changeShiftInsertPO, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("新增交接班,入参changeShiftInsertPO:{}", JSON.toJSONString(changeShiftInsertPO));
        return ResultBody.newSuccessInstance(changeShiftService.insertChangeShift(changeShiftInsertPO, userInfoToken));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新交接班")
    public ResultBody<Boolean> updateChangeShift(@RequestBody ChangeShiftUpdatePO changeShiftUpdatePO, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("更新交接班,入参changeShiftUpdatePO:{}", JSON.toJSONString(changeShiftUpdatePO));
        return ResultBody.newSuccessInstance(changeShiftService.updateChangeShift(changeShiftUpdatePO, userInfoToken));
    }

    @GetMapping("/query")
    @ApiOperation(value = "查询交接班")
    public ResultBody<PageVO<ChangeShiftQueryVO>> queryChangeShiftList(ChangeShiftQueryPO changeShiftQueryPO){
        log.info("查询交接班,入参changeShiftQueryPO:{}", JSON.toJSONString(changeShiftQueryPO));
        return ResultBody.newSuccessInstance(changeShiftService.queryChangeShiftList(changeShiftQueryPO));
    }

    @GetMapping("/delete")
    @ApiOperation(value = "删除交接班")
    public ResultBody<Boolean> deleteChangeShift(Long id, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("删除交接班,id:" + id);
        return ResultBody.newSuccessInstance(changeShiftService.deleteChangeShift(id, userInfoToken));
    }
}
