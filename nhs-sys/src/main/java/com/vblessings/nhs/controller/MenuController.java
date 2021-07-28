package com.vblessings.nhs.controller;

import com.vblessings.nhs.annoation.CurrentUser;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.MenuService;
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
import java.awt.*;

/**
 * 菜谱api
 */
@RestController
@RequestMapping("/menu")
@Api(tags = "菜谱")
@Slf4j
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     * 批量更新菜谱
     * @param sysCarerInfo
     * @param userInfo
     * @return
     */
    @ApiOperation("批量更新菜谱")
    @PostMapping("/batchUpdate")
    public ResultBody batchUpdate(@RequestBody List sysCarerInfo, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        log.info("修改内容： "+sysCarerInfo);
//        menuService.batchUpdate(sysCarerInfo,userInfo);
        return ResultBody.newSuccessInstance();
    }
}
