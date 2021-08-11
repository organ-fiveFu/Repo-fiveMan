package com.vblessings.nhs.controller;

import com.vblessings.nhs.annoation.CurrentUser;
import com.vblessings.nhs.model.entity.business.BusSpecialNursingRecord;
import com.vblessings.nhs.model.po.businessVO.QuerySpecialNursingPO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusSpecialNursingRecordService;
import com.vblessings.nhs.model.result.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

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
}
