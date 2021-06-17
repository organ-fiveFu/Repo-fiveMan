package com.nurse.healthy.controller;

import com.github.pagehelper.PageInfo;
import com.nurse.healthy.annoation.CurrentUser;
import com.nurse.healthy.model.entity.base.BasePatientInfo;
import com.nurse.healthy.result.UserInfoToken;
import com.nurse.healthy.service.BasePatientInfoService;
import com.nurse.healthy.vo.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * 老人档案api
 */
@RestController
@RequestMapping("/archive")
@Api(tags = "老人档案")
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
    @ApiOperation("老人基本档案分页")
    @PostMapping("/baseArchive")
    public ResultBody<PageInfo<BasePatientInfo>> baseArchivePage(){
       return ResultBody.newSuccessInstance(basePatientInfoService.selectPage());
    }

    /**
     * 删除档案
     */
    @ApiOperation("删除档案")
    @PostMapping("/del")
    public ResultBody del(Long id,@ApiIgnore @CurrentUser UserInfoToken userInfo ){
        basePatientInfoService.del(id,userInfo);
        return ResultBody.newSuccessInstance();
    }


}
