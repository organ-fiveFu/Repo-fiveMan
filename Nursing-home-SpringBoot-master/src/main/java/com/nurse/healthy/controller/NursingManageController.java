package com.nurse.healthy.controller;

import com.nurse.healthy.annoation.CurrentUser;
import com.nurse.healthy.model.entity.business.BusDonationRecord;
import com.nurse.healthy.model.entity.business.BusSpecialNursingRecord;
import com.nurse.healthy.model.po.businessVO.QueryDonationPO;
import com.nurse.healthy.model.po.businessVO.QuerySpecialNursingPO;
import com.nurse.healthy.model.vo.PageVO;
import com.nurse.healthy.result.UserInfoToken;
import com.nurse.healthy.service.BusSpecialNursingRecordService;
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
     * 删除捐款记录
     */
    @ApiOperation("删除特级护理记录")
    @PostMapping("/delSpecialNursing")
    public ResultBody delSpecialNursing(Long id){
        busSpecialNursingRecordService.delSpecialNursing(id);
        return ResultBody.newSuccessInstance();
    }
}
