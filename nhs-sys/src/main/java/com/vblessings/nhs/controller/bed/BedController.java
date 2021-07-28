package com.vblessings.nhs.controller.bed;


import cn.hutool.core.lang.tree.Tree;
import com.vblessings.nhs.annoation.CurrentUser;
import com.vblessings.nhs.model.po.bed.*;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.bed.*;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BedService;
import com.vblessings.nhs.model.result.BaseResult;
import com.vblessings.nhs.model.result.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/bed")
@Api(tags = "床位信息")
@Slf4j
public class BedController {

    @Resource
    private BedService bedService;

    /**
     * 新增楼宇信息
     * @author linxiazhu
     * @date 17:06 2021/6/24
     * @param sysBuildingInfoInsertPO  入参
     * @param userInfoToken   token
     * @return  com.nurse.healthy.vo.ResultBody<java.lang.Boolean>
     */
    @ApiOperation(value = "新增楼宇信息")
    @PostMapping("/building/insert")
    public BaseResult<Boolean> insertSysBuildingInfo(@RequestBody SysBuildingInfoInsertPO sysBuildingInfoInsertPO, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("新增楼宇信息,入参sysBuildingInfoInsertPO:" + sysBuildingInfoInsertPO);
        return BaseResult.success(bedService.insertSysBuildingInfo(sysBuildingInfoInsertPO, userInfoToken));
    }

    /**
     * 更新楼宇信息
     * @author linxiazhu
     * @date 16:37 2021/6/25
     * @param sysBuildingInfoUpdatePO  入参
     * @param userInfoToken   token
     * @return  com.nurse.healthy.vo.ResultBody<java.lang.Boolean>
     */
    @ApiOperation(value = "更新楼宇信息")
    @PostMapping("/building/update")
    public ResultBody<Boolean> updateSysBuildingInfo(@RequestBody SysBuildingInfoUpdatePO sysBuildingInfoUpdatePO, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("更新楼宇信息,入参sysBuildingInfoUpdatePO:" + sysBuildingInfoUpdatePO);
        return ResultBody.newSuccessInstance(bedService.updateSysBuildingInfo(sysBuildingInfoUpdatePO, userInfoToken));
    }

    /**
     * 查询楼宇信息
     * @author linxiazhu
     * @date 17:28 2021/6/25
     * @param sysBuildingInfoQueryPO  入参
     * @param userInfoToken   token
     * @return  com.nurse.healthy.vo.ResultBody<com.nurse.healthy.model.vo.PageVO<com.nurse.healthy.model.vo.bed.SysBuildingInfoQueryVO>>
     */
    @ApiOperation(value = "查询楼宇信息")
    @GetMapping("/building/query")
    public ResultBody<PageVO<SysBuildingInfoQueryVO>> querySysBuildingInfoList(SysBuildingInfoQueryPO sysBuildingInfoQueryPO, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("查询楼宇信息,入参sysBuildingInfoQueryPO:" + sysBuildingInfoQueryPO);
        return ResultBody.newSuccessInstance(bedService.querySysBuildingInfoList(sysBuildingInfoQueryPO, userInfoToken));
    }

    /**
     * 删除楼宇信息
     * @author linxiazhu
     * @date 13:40 2021/6/27
     * @param id  id
     * @param userInfoToken   token
     * @return  com.nurse.healthy.vo.ResultBody<java.lang.Boolean>
     */
    @ApiOperation(value = "删除楼宇信息")
    @GetMapping("/building/delete")
    public ResultBody<Boolean> deleteSysBuildingInfo(Long id, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("删除楼宇信息,id:" + id);
        return ResultBody.newSuccessInstance(bedService.deleteSysBuildingInfo(id, userInfoToken));
    }

    /**
     * 获取楼宇下拉列表
     * @author linxiazhu
     * @date 9:29 2021/6/29
     * @param userInfoToken   token
     * @return  com.nurse.healthy.vo.ResultBody<java.util.List<com.nurse.healthy.model.vo.bed.SysBuildingInfoQueryVO>>
     */
    @GetMapping("/building/get-list")
    @ApiOperation(value = "获取楼宇下拉列表")
    public ResultBody<List<SysBuildingInfoQueryVO>> querySysBuildingInfoGetList(@ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("获取楼宇下拉列表");
        return ResultBody.newSuccessInstance(bedService.querySysBuildingInfoGetList(userInfoToken));
    }

    /**
     * 新增楼层信息
     * @author linxiazhu
     * @date 13:58 2021/6/27
     * @param sysFloorInfoInsertPO  入参
     * @param userInfoToken   token
     * @return  com.nurse.healthy.vo.ResultBody<java.lang.Boolean>
     */
    @ApiOperation(value = "新增楼层信息")
    @PostMapping("/floor/insert")
    public ResultBody<Boolean> insertSysFloorInfo(@RequestBody SysFloorInfoInsertPO sysFloorInfoInsertPO, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("新增楼层信息,入参sysFloorInfoInsertPO:" + sysFloorInfoInsertPO);
        return ResultBody.newSuccessInstance(bedService.insertSysFloorInfo(sysFloorInfoInsertPO, userInfoToken));
    }

    /**
     * 更新楼层信息
     * @author linxiazhu
     * @date 14:08 2021/6/27
     * @param sysFloorInfoUpdatePO  入参
     * @param userInfoToken   token
     * @return  com.nurse.healthy.vo.ResultBody<java.lang.Boolean>
     */
    @ApiOperation(value = "更新楼层信息")
    @PostMapping("/floor/update")
    public ResultBody<Boolean> updateSysFloorInfo(@RequestBody SysFloorInfoUpdatePO sysFloorInfoUpdatePO, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("更新楼宇信息,入参sysFloorInfoUpdatePO:" + sysFloorInfoUpdatePO);
        return ResultBody.newSuccessInstance(bedService.updateSysFloorInfo(sysFloorInfoUpdatePO, userInfoToken));
    }

    /**
     * 查询楼层信息
     * @author linxiazhu
     * @date 14:24 2021/6/27
     * @param sysFloorInfoQueryPO  入参
     * @param userInfoToken   token
     * @return  com.nurse.healthy.vo.ResultBody<com.nurse.healthy.model.vo.PageVO<com.nurse.healthy.model.vo.bed.SysFloorInfoQueryVO>>
     */
    @ApiOperation(value = "查询楼层信息")
    @PostMapping("/floor/query")
    public ResultBody<PageVO<SysFloorInfoQueryVO>> querySysFloorInfoList(SysFloorInfoQueryPO sysFloorInfoQueryPO, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("查询楼层信息,入参sysFloorInfoQueryPO:" + sysFloorInfoQueryPO);
        return ResultBody.newSuccessInstance(bedService.querySysFloorInfoList(sysFloorInfoQueryPO, userInfoToken));
    }

    /**
     * 删除楼层信息
     * @author linxiazhu
     * @date 14:39 2021/6/27
     * @param id  id
     * @param userInfoToken   token
     * @return  com.nurse.healthy.vo.ResultBody<java.lang.Boolean>
     */
    @ApiOperation(value = "删除楼层信息")
    @GetMapping("/floor/delete")
    public ResultBody<Boolean> deleteSysFloorInfo(Long id, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("删除楼层信息,id:" + id);
        return ResultBody.newSuccessInstance(bedService.deleteSysFloorInfo(id, userInfoToken));
    }

    /**
     * 获取楼层下拉列表
     * @author linxiazhu
     * @date 9:35 2021/6/29
     * @param buildingCode  楼宇code
     * @param userInfoToken   token
     * @return  com.nurse.healthy.vo.ResultBody<java.util.List<com.nurse.healthy.model.vo.bed.SysFloorInfoQueryVO>>
     */
    @GetMapping("/floor/get-list")
    @ApiOperation(value = "获取楼层下拉列表")
    public ResultBody<List<SysFloorInfoQueryVO>> querySysFloorInfoGetList(String buildingCode, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("获取楼层下拉列表");
        return ResultBody.newSuccessInstance(bedService.querySysFloorInfoGetList(buildingCode, userInfoToken));
    }

    /**
     * 新增房间信息
     * @author linxiazhu
     * @date 10:19 2021/6/28
     * @param sysRoomInfoInsertPO  入参
     * @param userInfoToken   token
     * @return  com.nurse.healthy.vo.ResultBody<java.lang.Boolean>
     */
    @ApiOperation(value = "新增房间信息")
    @PostMapping("/room/insert")
    public ResultBody<Boolean> insertSysRoomInfo(@RequestBody SysRoomInfoInsertPO sysRoomInfoInsertPO, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("新增房间信息,入参sysRoomInfoInsertPO:" + sysRoomInfoInsertPO);
        return ResultBody.newSuccessInstance(bedService.insertSysRoomInfo(sysRoomInfoInsertPO, userInfoToken));
    }

    /**
     * 更新房间信息
     * @author linxiazhu
     * @date 11:23 2021/6/28
     * @param sysRoomInfoUpdatePO  入参
     * @param userInfoToken   token
     * @return  com.nurse.healthy.vo.ResultBody<java.lang.Boolean>
     */
    @ApiOperation(value = "更新房间信息")
    @PostMapping("/room/update")
    public ResultBody<Boolean> updateSysRoomInfo(@RequestBody SysRoomInfoUpdatePO sysRoomInfoUpdatePO, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("更新房间信息,入参sysRoomInfoUpdatePO:" + sysRoomInfoUpdatePO);
        return ResultBody.newSuccessInstance(bedService.updateSysRoomInfo(sysRoomInfoUpdatePO, userInfoToken));
    }

    /**
     * 查询房间信息
     * @author linxiazhu
     * @date 13:47 2021/6/28
     * @param sysRoomInfoQueryPO  入参
     * @param userInfoToken   token
     * @return  com.nurse.healthy.vo.ResultBody<com.nurse.healthy.model.vo.PageVO<com.nurse.healthy.model.vo.bed.SysRoomInfoQueryVO>>
     */
    @ApiOperation(value = "查询房间信息")
    @GetMapping("/room/query")
    public ResultBody<PageVO<SysRoomInfoQueryVO>> querySysRoomInfoList(SysRoomInfoQueryPO sysRoomInfoQueryPO, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("查询房间信息,入参sysRoomInfoQueryPO:" + sysRoomInfoQueryPO);
        return ResultBody.newSuccessInstance(bedService.querySysRoomInfoList(sysRoomInfoQueryPO, userInfoToken));
    }

    /**
     * 删除房间信息
     * @author linxiazhu
     * @date 16:16 2021/6/28
     * @param id  id
     * @param userInfoToken   token
     * @return  com.nurse.healthy.vo.ResultBody<java.lang.Boolean>
     */
    @ApiOperation(value = "删除房间信息")
    @GetMapping("/room/delete")
    public ResultBody<Boolean> deleteSysRoomInfo(Long id, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("删除房间信息,id:" + id);
        return ResultBody.newSuccessInstance(bedService.deleteSysRoomInfo(id, userInfoToken));
    }

    /**
     * 获取房间下拉列表
     * @author linxiazhu
     * @date 9:42 2021/6/29
     * @param sysRoomInfoGetListPO  入参
     * @param userInfoToken   token
     * @return  com.nurse.healthy.vo.ResultBody<java.util.List<com.nurse.healthy.model.vo.bed.SysRoomInfoGetListVO>>
     */
    @GetMapping("/room/get-list")
    @ApiOperation(value = "获取房间下拉列表")
    public ResultBody<List<SysRoomInfoGetListVO>> querySysRoomInfoGetList(SysRoomInfoGetListPO sysRoomInfoGetListPO, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("获取房间下拉列表");
        return ResultBody.newSuccessInstance(bedService.querySysRoomInfoGetList(sysRoomInfoGetListPO, userInfoToken));
    }

    /**
     * 新增床位信息
     * @author linxiazhu
     * @date 16:49 2021/6/28
     * @param sysBedInfoInsertPO  入参
     * @param userInfoToken   token
     * @return  com.nurse.healthy.vo.ResultBody<java.lang.Boolean>
     */
    @ApiOperation(value = "新增床位信息")
    @PostMapping("/bed/insert")
    public ResultBody<Boolean> insertSysBedInfo(@RequestBody SysBedInfoInsertPO sysBedInfoInsertPO, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("新增床位信息,入参sysBedInfoInsertPO:" + sysBedInfoInsertPO);
        return ResultBody.newSuccessInstance(bedService.insertSysBedInfo(sysBedInfoInsertPO, userInfoToken));
    }

    /**
     * 更新床位信息
     * @author linxiazhu
     * @date 17:01 2021/6/28
     * @param sysBedInfoUpdatePO  入参
     * @param userInfoToken   token
     * @return  com.nurse.healthy.vo.ResultBody<java.lang.Boolean>
     */
    @ApiOperation(value = "更新床位信息")
    @PostMapping("/bed/update")
    public ResultBody<Boolean> updateSysBedInfo(@RequestBody SysBedInfoUpdatePO sysBedInfoUpdatePO, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("更新床位信息,入参sysBedInfoUpdatePO:" + sysBedInfoUpdatePO);
        return ResultBody.newSuccessInstance(bedService.updateSysBedInfo(sysBedInfoUpdatePO, userInfoToken));
    }

    /**
     * 查询床位信息
     * @author linxiazhu
     * @date 17:10 2021/6/28
     * @param sysBedInfoQueryPO  入参
     * @param userInfoToken   token
     * @return  com.nurse.healthy.vo.ResultBody<com.nurse.healthy.model.vo.PageVO<com.nurse.healthy.model.vo.bed.SysBedInfoQueryVO>>
     */
    @ApiOperation(value = "查询床位信息")
    @GetMapping("/bed/query")
    public ResultBody<PageVO<SysBedInfoQueryVO>> querySysBedInfoList(SysBedInfoQueryPO sysBedInfoQueryPO, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("查询床位信息,入参sysBedInfoQueryPO:" + sysBedInfoQueryPO);
        return ResultBody.newSuccessInstance(bedService.querySysBedInfoList(sysBedInfoQueryPO, userInfoToken));
    }

    /**
     * 删除床位信息
     * @author linxiazhu
     * @date 17:24 2021/6/28
     * @param id  id
     * @param userInfoToken   token
     * @return  com.nurse.healthy.vo.ResultBody<java.lang.Boolean>
     */
    @ApiOperation(value = "删除床位信息")
    @GetMapping("/bed/delete")
    public ResultBody<Boolean> deleteSysBedInfo(Long id, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("删除床位信息,id:" + id);
        return ResultBody.newSuccessInstance(bedService.deleteSysBedInfo(id, userInfoToken));
    }

    /**
     * 床位tree
     * @author linxiazhu
     * @date 13:28 2021/6/29
     * @param userInfoToken   token
     * @return  com.nurse.healthy.vo.ResultBody<java.util.List<cn.hutool.core.lang.tree.Tree<java.lang.String>>>
     */
    @GetMapping("/tree")
    @ApiOperation(value = "床位tree")
    public ResultBody<List<Tree<String>>> queryBedTree(@ApiIgnore @CurrentUser UserInfoToken userInfoToken) {
        log.info("床位tree");
        return ResultBody.newSuccessInstance(bedService.queryBedTree(userInfoToken));
    }
}
