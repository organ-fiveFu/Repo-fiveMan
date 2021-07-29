package com.vblessings.nhs.controller;

import com.vblessings.nhs.annoation.CurrentUser;
import com.vblessings.nhs.model.entity.sys.SysCarerInfo;
import com.vblessings.nhs.model.po.KeyWordPO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.CareService;
import com.vblessings.nhs.model.result.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

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
    public ResultBody<PageVO<SysCarerInfo>> select(@RequestBody KeyWordPO keyWordPO){
        return ResultBody.newSuccessInstance(careService.select(keyWordPO));
    }


    @ApiOperation("删除护工信息")
    @GetMapping("/del")
    public ResultBody del(String ids) {
        careService.del(ids);
        return ResultBody.newSuccessInstance();
    }

}
