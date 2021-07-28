package com.vblessings.nhs.controller;

import com.vblessings.nhs.annoation.CurrentUser;
import com.vblessings.nhs.model.entity.sys.SysCarerInfo;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.CareService;
import com.vblessings.nhs.model.result.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * 护工api
 */
@RestController
@RequestMapping("/care")
@Api(tags = "护工信息")
@Slf4j
public class CareController {
    @Resource
    private CareService careService;


    /**
     * 新增护工
     */
    @ApiOperation("新增护工")
    @PostMapping("/add")
    public ResultBody add(@RequestBody SysCarerInfo sysCarerInfo, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        careService.add(sysCarerInfo,userInfo);
        return ResultBody.newSuccessInstance();
    }

    /**
     * 更新护工信息
     * @param sysCarerInfo
     * @param userInfo
     * @return
     */
    @ApiOperation("修改护工信息")
    @PostMapping("/update")
    public ResultBody update(@RequestBody SysCarerInfo sysCarerInfo, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        log.info("修改内容： "+sysCarerInfo);
        careService.update(sysCarerInfo,userInfo);
        return ResultBody.newSuccessInstance();
    }

    @ApiOperation("查询护工信息")
    @PostMapping("/select")
    public ResultBody select(String careCode){
        return ResultBody.newSuccessInstance(careService.select(careCode));
    }


    @ApiOperation("删除护工信息")
    @PostMapping("/del")
    public ResultBody del(@RequestBody List<String> ids) {
        careService.del(ids);
        return ResultBody.newSuccessInstance();
    }

}
