package com.vblessings.nhs.controller;

import com.github.pagehelper.PageInfo;
import com.vblessings.nhs.annoation.CurrentUser;
import com.vblessings.nhs.model.entity.business.BusHospitalRecord;
import com.vblessings.nhs.model.po.business.BusHospitalRecordPO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusHospitalRecordService;
import com.vblessings.nhs.model.result.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * 入院登记api
 */
@RestController
@RequestMapping("/hospitalRegist")
@Api(tags = "入院登记")
@Slf4j
public class HospitalRegistController {

    @Resource
    private BusHospitalRecordService busHospitalRecordService;

    @ApiOperation("入院登记新增")
    @PostMapping("/add")
    public ResultBody add(@RequestBody BusHospitalRecord busHospitalRecord, @ApiIgnore @CurrentUser UserInfoToken userInfo) {
        busHospitalRecordService.add(busHospitalRecord,userInfo);
        return ResultBody.newSuccessInstance();
    }

    @ApiOperation("入院登记搜索")
    @PostMapping("/select")
    public ResultBody<PageInfo<BusHospitalRecord>> select(@RequestBody BusHospitalRecordPO busHospitalRecordPO, @ApiIgnore @CurrentUser UserInfoToken userInfo) {
        return ResultBody.newSuccessInstance(busHospitalRecordService.select(busHospitalRecordPO,userInfo));
    }

    @ApiOperation("入院登记跟新")
    @PostMapping("/update")
    public ResultBody update(@RequestBody BusHospitalRecord busHospitalRecord, @ApiIgnore @CurrentUser UserInfoToken userInfo) {
        busHospitalRecordService.update(busHospitalRecord,userInfo);
        return ResultBody.newSuccessInstance();
    }



    @ApiOperation("出院")
    @GetMapping("/out")
    public ResultBody out(String businessNo){
        busHospitalRecordService.out(businessNo);
        return ResultBody.newSuccessInstance();
    }
}
