package com.vblessings.nhs.controller;

import com.alibaba.fastjson.JSON;
import com.vblessings.nhs.annoation.CurrentUser;
import com.vblessings.nhs.annoation.IgnoreUserToken;
import com.vblessings.nhs.model.po.business.BusMenuBatchUpdatePO;
import com.vblessings.nhs.model.po.business.BusMenuSelectPO;
import com.vblessings.nhs.model.vo.business.BusMenuSelectVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.MenuService;
import com.vblessings.nhs.model.result.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

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
     * @param busMenuBatchUpdatePO      菜谱入参
     * @param userInfo                  token
     */
    @ApiOperation("批量更新菜谱")
    @PostMapping("/batchUpdate")
    public ResultBody batchUpdate(@RequestBody BusMenuBatchUpdatePO busMenuBatchUpdatePO, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        log.info("批量更新菜谱PO： " + JSON.toJSONString(busMenuBatchUpdatePO));
        menuService.batchUpdate(busMenuBatchUpdatePO,userInfo);
        return ResultBody.newSuccessInstance();
    }

    /**
     * 查询菜谱
     * @param busMenuSelectPO           查询菜谱入参
     * @param userInfo                  token
     */
    @ApiOperation("查询菜谱")
    @GetMapping("/select")
    public ResultBody<List<BusMenuSelectVO>> selectMenuInfo(BusMenuSelectPO busMenuSelectPO, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        log.info("查询菜谱PO： " + JSON.toJSONString(busMenuSelectPO));
        return ResultBody.newSuccessInstance(menuService.selectMenuInfo(busMenuSelectPO,userInfo));
    }

    /**
     * 查询菜谱-no-token
     * @param busMenuSelectPO           查询菜谱入参
     */
    @IgnoreUserToken
    @ApiOperation("查询菜谱-no-token")
    @GetMapping("/select/no-token")
    public ResultBody<List<BusMenuSelectVO>> selectMenuInfoNoToken(BusMenuSelectPO busMenuSelectPO){
        log.info("查询菜谱PO： " + JSON.toJSONString(busMenuSelectPO));
        return ResultBody.newSuccessInstance(menuService.selectMenuInfo(busMenuSelectPO, null));
    }
}
