package com.nurse.healthy.controller;


import com.nurse.healthy.annoation.CurrentUser;
import com.nurse.healthy.model.entity.business.BusComplaintsRecord;
import com.nurse.healthy.model.entity.business.BusDonationRecord;
import com.nurse.healthy.model.entity.business.BusInterestGroupRecord;
import com.nurse.healthy.model.entity.business.BusSatisfactionMeasurement;
import com.nurse.healthy.model.po.businessVO.QueryComplaintVO;
import com.nurse.healthy.model.po.businessVO.QueryDonationPO;
import com.nurse.healthy.model.po.businessVO.QueryInterestGroupVO;
import com.nurse.healthy.model.vo.PageVO;
import com.nurse.healthy.result.UserInfoToken;
import com.nurse.healthy.service.BusComplaintsRecordService;
import com.nurse.healthy.service.BusDonationRecordService;
import com.nurse.healthy.service.BusInterestGroupRecordService;
import com.nurse.healthy.service.BusSatisfactionMeasurementService;
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
 * 综合模块
 */
@RestController
@RequestMapping("/comprehensive")
@Api(tags = "综合模块")
@Slf4j
public class ComprehensiveController {

    @Resource
    private BusSatisfactionMeasurementService busSatisfactionMeasurementService;

    @Resource
    private BusDonationRecordService busDonationRecordService;

    @Resource
    private BusInterestGroupRecordService busInterestGroupRecordService;

    @Resource
    private BusComplaintsRecordService busComplaintsRecordService;


    /**
     * 满意度测评
     */
    @ApiOperation("满意度测评")
    @PostMapping("/add")
    public ResultBody add(@RequestBody BusSatisfactionMeasurement busSatisfactionMeasurement, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        return ResultBody.newSuccessInstance(busSatisfactionMeasurementService.add(busSatisfactionMeasurement,userInfo));
    }

    /**
     * 新增捐款记录
     */
    @ApiOperation("新增捐款记录")
    @PostMapping("/donation")
    public ResultBody donation(@RequestBody BusDonationRecord busDonationRecord, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        busDonationRecordService.donation(busDonationRecord,userInfo);
        return ResultBody.newSuccessInstance();
    }

    /**
     * 查询捐款记录
     */
    @ApiOperation("查询捐款记录")
    @PostMapping("/pageDonation")
    public ResultBody<PageVO<BusDonationRecord>> pageDonation(@RequestBody QueryDonationPO queryDonationPO){
        return ResultBody.newSuccessInstance(busDonationRecordService.pageDonation(queryDonationPO));
    }


    /**
     * 更新捐款记录
     */
    @ApiOperation("更新捐款记录")
    @PostMapping("/updateDonation")
    public ResultBody updateDonation(@RequestBody BusDonationRecord busDonationRecord, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        busDonationRecordService.updateDonation(busDonationRecord,userInfo);
        return ResultBody.newSuccessInstance();
    }

    /**
     * 删除捐款记录
     */
    @ApiOperation("删除捐款记录")
    @PostMapping("/delDonation")
    public ResultBody delDonation(Long id){
        busDonationRecordService.delDonation(id);
        return ResultBody.newSuccessInstance();
    }

    /**
     * 新增兴趣小组记录
     */
    @ApiOperation("新增兴趣小组记录")
    @PostMapping("/addInterestGroup")
    public ResultBody addInterestGroup(@RequestBody BusInterestGroupRecord busDonationRecord, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        busInterestGroupRecordService.addInterestGroup(busDonationRecord,userInfo);
        return ResultBody.newSuccessInstance();
    }

    /**
     * 查询兴趣小组记录
     */
    @ApiOperation("查询兴趣小组记录")
    @PostMapping("/pageInterestGroup")
    public ResultBody<PageVO<BusInterestGroupRecord>> pageInterestGroup(@RequestBody QueryInterestGroupVO queryInterestGroupVO){
        return ResultBody.newSuccessInstance(busInterestGroupRecordService.pageInterestGroup(queryInterestGroupVO));
    }

    /**
     * 更新兴趣小组记录
     */
    @ApiOperation("更新兴趣小组记录")
    @PostMapping("/updateInterestGroup")
    public ResultBody updateInterestGroup(@RequestBody BusInterestGroupRecord busInterestGroupRecord, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        busInterestGroupRecordService.updateInterestGroup(busInterestGroupRecord,userInfo);
        return ResultBody.newSuccessInstance();
    }

    /**
     * 删除兴趣小组记录
     */
    @ApiOperation("删除兴趣小组记录")
    @PostMapping("/delInterestGroup")
    public ResultBody delInterestGroup(Long id){
        busInterestGroupRecordService.delInterestGroup(id);
        return ResultBody.newSuccessInstance();
    }


    /**
     * 新增投诉记录
     */
    @ApiOperation("新增投诉记录")
    @PostMapping("/addComplaint")
    public ResultBody addComplaint(@RequestBody BusComplaintsRecord busComplaintsRecord, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        busComplaintsRecordService.addComplaint(busComplaintsRecord,userInfo);
        return ResultBody.newSuccessInstance();
    }

    /**
     * 查询投诉记录（根据主题）
     *
     */
    @ApiOperation("查询投诉记录")
    @PostMapping("/pageComplaint")
    public ResultBody<PageVO<BusComplaintsRecord>> pageComplaint(@RequestBody QueryComplaintVO queryComplaintVO){
        return ResultBody.newSuccessInstance(busComplaintsRecordService.pageComplaint(queryComplaintVO));
    }

    /**
     * 更新兴趣小组记录
     */
    @ApiOperation("更新投诉记录")
    @PostMapping("/updateComplaint")
    public ResultBody updateComplaint(@RequestBody BusComplaintsRecord busComplaintsRecord, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        busComplaintsRecordService.updateComplaint(busComplaintsRecord,userInfo);
        return ResultBody.newSuccessInstance();
    }

    /**
     * 删除兴趣小组记录
     */
    @ApiOperation("删除投诉记录")
    @PostMapping("/delComplaint")
    public ResultBody delComplaint(Long id){
        busComplaintsRecordService.delComplaint(id);
        return ResultBody.newSuccessInstance();
    }
}
