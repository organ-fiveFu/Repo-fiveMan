package com.nurse.healthy.controller;

import com.github.pagehelper.PageInfo;
import com.nurse.healthy.annoation.CurrentUser;
import com.nurse.healthy.model.entity.base.BasePatientInfo;
import com.nurse.healthy.model.entity.business.BusHospitalRecord;
import com.nurse.healthy.result.UserInfoToken;
import com.nurse.healthy.service.BusHospitalRecordService;
import com.nurse.healthy.vo.BaseResult;
import com.nurse.healthy.vo.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResultBody add(@RequestBody BusHospitalRecord busHospitalRecord,@ApiIgnore @CurrentUser UserInfoToken userInfo) {
        busHospitalRecordService.add(busHospitalRecord,userInfo);
        return ResultBody.newSuccessInstance();
    }

    @ApiOperation("入院登记搜索")
    @PostMapping("/select")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", dataType = "String"),
            @ApiImplicitParam(name = "businessNo", value = "住院号", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "Integer")
    })
    public ResultBody<PageInfo<BusHospitalRecord>> select(String name,String businessNo,int pageNum, int pageSize, @ApiIgnore @CurrentUser UserInfoToken userInfo) {
        return ResultBody.newSuccessInstance(busHospitalRecordService.select(name,businessNo,pageNum,pageSize,userInfo));
    }


}
