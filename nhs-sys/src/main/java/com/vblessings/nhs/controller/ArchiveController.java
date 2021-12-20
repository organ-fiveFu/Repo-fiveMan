package com.vblessings.nhs.controller;

import com.github.pagehelper.PageInfo;
import com.vblessings.nhs.annoation.CurrentUser;
import com.vblessings.nhs.annoation.IgnoreUserToken;
import com.vblessings.nhs.model.entity.base.BasePatientInfo;
import com.vblessings.nhs.model.po.QueryBasePatientPO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BasePatientInfoService;
import com.vblessings.nhs.model.result.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * 老人基础档案api
 */
@RestController
@RequestMapping("/baseArchive")
@Api(tags = "老人基础档案")
@Slf4j
public class ArchiveController {

    @Resource
    private BasePatientInfoService basePatientInfoService;

    /**
     * 新增档案
     */
    @ApiOperation("新增档案")
    @PostMapping("/add")
    public ResultBody add(@RequestBody BasePatientInfo basePatientInfo, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        basePatientInfoService.add(basePatientInfo,userInfo);
        return ResultBody.newSuccessInstance();
    }

    /**
     * 更新档案
     */
    @ApiOperation("更新档案")
    @PostMapping("/update")
    public ResultBody update(@RequestBody BasePatientInfo basePatientInfo,@ApiIgnore @CurrentUser UserInfoToken userInfo){
        basePatientInfoService.update(basePatientInfo,userInfo);
        return ResultBody.newSuccessInstance();
    }

    /**
     * 查询老人基础档案
     */
    @IgnoreUserToken
    @ApiOperation("老人基本档案分页")
    @PostMapping("/baseArchivePage")
    public ResultBody<PageInfo<BasePatientInfo>> baseArchivePage(@RequestBody QueryBasePatientPO queryBasePatientPO){
       return ResultBody.newSuccessInstance(basePatientInfoService.selectPage(queryBasePatientPO));
    }

    /**
     * 删除档案
     */
    @ApiOperation("删除档案")
    @GetMapping("/del")
    public ResultBody del(String ids){
        basePatientInfoService.del(ids);
        return ResultBody.newSuccessInstance();
    }

    /**
     * 根据姓名模糊查档案
     */
    @ApiOperation("老人基本档案列表")
    @GetMapping("/baseArchiveList")
    public ResultBody<List<BasePatientInfo>> baseArchiveList(String name){
        return ResultBody.newSuccessInstance(basePatientInfoService.baseArchiveList(name));
    }

}
