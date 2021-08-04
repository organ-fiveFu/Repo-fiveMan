package com.vblessings.nhs.controller.comprehensive;

import com.alibaba.fastjson.JSON;
import com.vblessings.nhs.annoation.CurrentUser;
import com.vblessings.nhs.model.po.comprehensive.AdministrativeInsertPO;
import com.vblessings.nhs.model.po.comprehensive.AdministrativeQueryPO;
import com.vblessings.nhs.model.po.comprehensive.AdministrativeUpdatePO;
import com.vblessings.nhs.model.result.ResultBody;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.comprehensive.AdministrativeQueryVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.AdministrativeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

@RestController
@RequestMapping("/administrative")
@Api(tags = "行政查房")
@Slf4j
public class AdministrativeController {

    @Resource
    private AdministrativeService administrativeService;

    @PostMapping("/insert")
    @ApiOperation(value = "新增行政查房")
    public ResultBody<Boolean> insertAdministrative(@RequestBody AdministrativeInsertPO administrativeInsertPO, @CurrentUser @ApiIgnore UserInfoToken userInfoToken){
        log.info("新增行政查房,入参administrativeInsertPO:{}", JSON.toJSONString(administrativeInsertPO));
        return ResultBody.newSuccessInstance(administrativeService.insertAdministrative(administrativeInsertPO, userInfoToken));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新行政查房")
    public ResultBody<Boolean> updateAdministrative(@RequestBody AdministrativeUpdatePO administrativeUpdatePO, @CurrentUser @ApiIgnore UserInfoToken userInfoToken){
        log.info("更新行政查房,入参administrativeUpdatePO:{}", JSON.toJSONString(administrativeUpdatePO));
        return ResultBody.newSuccessInstance(administrativeService.updateAdministrative(administrativeUpdatePO, userInfoToken));
    }

    @GetMapping("/query")
    @ApiOperation(value = "查询行政查房")
    public ResultBody<PageVO<AdministrativeQueryVO>> queryAdministrativeList(AdministrativeQueryPO administrativeQueryPO){
        log.info("查询行政查房,入参administrativeQueryPO:{}", JSON.toJSONString(administrativeQueryPO));
        return ResultBody.newSuccessInstance(administrativeService.queryAdministrativeList(administrativeQueryPO));
    }

    @GetMapping("/delete")
    @ApiOperation(value = "删除行政查房")
    public ResultBody<Boolean> deleteAdministrative(Long id, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("删除行政查房,id:" + id);
        return ResultBody.newSuccessInstance(administrativeService.deleteAdministrative(id, userInfoToken));
    }
}
