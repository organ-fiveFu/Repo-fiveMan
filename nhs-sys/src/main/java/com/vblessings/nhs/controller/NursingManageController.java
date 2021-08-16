package com.vblessings.nhs.controller;

import com.alibaba.fastjson.JSON;
import com.vblessings.nhs.annoation.CurrentUser;
import com.vblessings.nhs.model.entity.business.BusNursingRecord;
import com.vblessings.nhs.model.entity.business.BusSpecialNursingRecord;
import com.vblessings.nhs.model.po.business.*;
import com.vblessings.nhs.model.po.businessVO.QuerySpecialNursingPO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.business.BusNursingRecordQueryVO;
import com.vblessings.nhs.model.vo.business.BusVitalSignRecordVO;
import com.vblessings.nhs.model.vo.business.BusVitalSignVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusNursingRecordService;
import com.vblessings.nhs.service.BusSpecialNursingRecordService;
import com.vblessings.nhs.model.result.ResultBody;
import com.vblessings.nhs.service.BusVitalSignRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * 验证
 */
@RestController
@RequestMapping("/nursingManage")
@Api(tags = "护理管理")
@Slf4j
public class NursingManageController {

    @Resource
    private BusSpecialNursingRecordService busSpecialNursingRecordService;

    @Resource
    private BusNursingRecordService busNursingRecordService;

    @Resource
    private BusVitalSignRecordService busVitalSignRecordService;

    /**
     * 新增特级护理记录
     */
    @ApiOperation("新增特级护理记录")
    @PostMapping("/addSpecialNursing")
    public ResultBody addSpecialNursing(@RequestBody BusSpecialNursingRecord busSpecialNursingRecord, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        busSpecialNursingRecordService.addSpecialNursing(busSpecialNursingRecord,userInfo);
        return ResultBody.newSuccessInstance();
    }

    /**
     * 查询特级护理记录
     */
    @ApiOperation("查询特级护理记录")
    @PostMapping("/pageSpecialNursing")
    public ResultBody<PageVO<BusSpecialNursingRecord>> pageSpecialNursing(@RequestBody QuerySpecialNursingPO querySpecialNursingPO){
        return ResultBody.newSuccessInstance(busSpecialNursingRecordService.pageSpecialNursing(querySpecialNursingPO));
    }

    /**
     * 更新特级护理记录
     */
    @ApiOperation("更新特级护理记录")
    @PostMapping("/updateSpecialNursing")
    public ResultBody updateSpecialNursing(@RequestBody BusSpecialNursingRecord busSpecialNursingRecord, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        busSpecialNursingRecordService.updateSpecialNursing(busSpecialNursingRecord,userInfo);
        return ResultBody.newSuccessInstance();
    }

    /**
     * 删除特级护理记录
     */
    @ApiOperation("删除特级护理记录")
    @GetMapping("/delSpecialNursing")
    public ResultBody delSpecialNursing(String  ids){
        busSpecialNursingRecordService.delSpecialNursing(ids);
        return ResultBody.newSuccessInstance();
    }

    /**
     * 新增护理记录
     */
    @ApiOperation("新增护理记录")
    @PostMapping("/addNursingRecord")
    public ResultBody<Boolean> addNursingRecord(@RequestBody BusNursingRecordPO busNursingRecordPO, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        busNursingRecordService.addNursingRecord(busNursingRecordPO, userInfo);
        return ResultBody.newSuccessInstance();
    }

    /**
     * 查询护理记录
     */
    @ApiOperation("查询护理记录")
    @PostMapping("/pageNursingRecord")
    public ResultBody<PageVO<BusNursingRecordPO>> pageNursingRecord(@RequestBody QueryNursingRecordPO queryNursingRecordPO){
        return ResultBody.newSuccessInstance(busNursingRecordService.pageNursingRecord(queryNursingRecordPO));
    }

    /**
     * 更新护理记录
     */
    @ApiOperation("更新护理记录")
    @PostMapping("/updateNursingRecord")
    public ResultBody<Boolean> updateNursingRecord(@RequestBody BusNursingRecordPO busNursingRecordPO, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        busNursingRecordService.updateNursingRecord(busNursingRecordPO, userInfo);
        return ResultBody.newSuccessInstance();
    }

    /**
     * 批量更新护理记录
     */
    @ApiOperation("批量更新护理记录")
    @PostMapping("/batchUpdateNursingRecord")
    public ResultBody<Boolean> batchUpdateNursingRecord(@RequestBody List<BusNursingRecordPO> busNursingRecordPOS, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        busNursingRecordService.batchUpdateNursingRecord(busNursingRecordPOS, userInfo);
        return ResultBody.newSuccessInstance();
    }

    /**
     * 删除护理记录
     */
    @ApiOperation("删除护理记录")
    @GetMapping("/delNursingRecord")
    public ResultBody<Boolean> delNursingRecord(String ids) {
        busNursingRecordService.delNursingRecord(ids);
        return ResultBody.newSuccessInstance();
    }

    /**
     * 新增三测单---作废，合入新增护理记录
     */
    @ApiOperation("新增三测单---作废，合入新增护理记录")
    @PostMapping("/addVitalSignRecord")
    public ResultBody<Boolean> addVitalSignRecord(@RequestBody BusVitalSignRecordPO busVitalSignRecordPO, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        busVitalSignRecordService.addVitalSignRecord(busVitalSignRecordPO, userInfo);
        return ResultBody.newSuccessInstance();
    }

    /**
     * 批量更新三测单---作废，合入批量更新护理记录
     */
    @ApiOperation("批量更新三测单---作废，合入批量更新护理记录")
    @PostMapping("/batchUpdateVitalSignRecord")
    public ResultBody<Boolean> batchUpdateVitalSignRecord(@RequestBody List<BusVitalSignRecordPO> busVitalSignRecordPOS, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        busVitalSignRecordService.batchUpdateVitalSignRecord(busVitalSignRecordPOS, userInfo);
        return ResultBody.newSuccessInstance();
    }

    /**
     * 查询三测单记录
     */
    @ApiOperation("查询三测单记录")
    @PostMapping("/queryVitalSignRecord")
    public ResultBody<List<BusVitalSignRecordPO>> queryVitalSignRecord(@RequestBody QueryVitalSignPO queryVitalSignPO){
//        return ResultBody.newSuccessInstance(busNursingRecordService.queryVitalSignRecord(queryVitalSignPO));
        log.info("queryVitalSignRecord, 入参： " + JSON.toJSONString(queryVitalSignPO));
        return ResultBody.newSuccessInstance(busVitalSignRecordService.queryVitalSignRecord(queryVitalSignPO));
    }

    /**
     * 批量插入——查询当前时间点三测单列表
     */
    @ApiOperation("批量插入——查询当前时间点三测单列表")
    @PostMapping("/batchQueryVitalSignRecord")
    public ResultBody<List<BusNursingRecordQueryVO>> batchQueryVitalSignRecord(@RequestBody QueryBatchVitalSignPO queryBatchVitalSignPO){
        return ResultBody.newSuccessInstance(busNursingRecordService.batchQueryVitalSignRecord(queryBatchVitalSignPO));
    }

    /**
     * 删除三测单记录---作废，合入删除护理记录
     */
    @ApiOperation("删除三测单记录---作废，合入删除护理记录")
    @GetMapping("/delVitalSignRecord")
    public ResultBody<Boolean> delVitalSignRecord(String ids) {
        busVitalSignRecordService.delVitalSignRecord(ids);
        return ResultBody.newSuccessInstance();
    }
}
