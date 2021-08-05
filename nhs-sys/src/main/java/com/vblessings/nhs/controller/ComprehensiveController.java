package com.vblessings.nhs.controller;


import com.vblessings.nhs.annoation.CurrentUser;
import com.vblessings.nhs.model.entity.business.*;
import com.vblessings.nhs.model.po.BusInterestGroupRecordPO;
import com.vblessings.nhs.model.po.businessVO.QueryCheckVO;
import com.vblessings.nhs.model.po.businessVO.QueryComplaintVO;
import com.vblessings.nhs.model.po.businessVO.QueryDonationPO;
import com.vblessings.nhs.model.po.businessVO.QueryInterestGroupVO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.business.BusCarerCheckVO;
import com.vblessings.nhs.model.vo.business.BusComplaintsRecordVO;
import com.vblessings.nhs.model.vo.business.BusDonationRecordVO;
import com.vblessings.nhs.model.vo.business.BusInterestGroupRecordVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.*;
import com.vblessings.nhs.model.result.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
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

    @Resource
    private BusCarerCheckService busCarerCheckService;


    /**
     * 满意度测评
     */
    @ApiOperation("满意度测评")
    @PostMapping("/add")
    public ResultBody add(@RequestBody BusSatisfactionMeasurement busSatisfactionMeasurement, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        busSatisfactionMeasurementService.add(busSatisfactionMeasurement,userInfo);
        return ResultBody.newSuccessInstance();
    }
    /**
     *
     */
    @ApiOperation("查询满意度测评")
    @GetMapping("/selectMeasurement")
    public ResultBody<BusSatisfactionMeasurement> selectMeasurement(String phone){
        return ResultBody.newSuccessInstance(busSatisfactionMeasurementService.selectMeasurement(phone));
    }

    /**
     * 新增捐款记录
     */
    @ApiOperation("新增捐款记录")
    @PostMapping("/donation")
    public ResultBody donation(@RequestBody BusDonationRecord busDonationRecord, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        busDonationRecordService.donation(busDonationRecord,userInfo);
        return ResultBody.newSuccessInstance("新增成功");
    }

    /**
     * 查询捐款记录
     */
    @ApiOperation("查询捐款记录")
    @PostMapping("/pageDonation")
    public ResultBody<PageVO<BusDonationRecordVO>> pageDonation(@RequestBody QueryDonationPO queryDonationPO){
        return ResultBody.newSuccessInstance(busDonationRecordService.pageDonation(queryDonationPO));
    }


    /**
     * 更新捐款记录
     */
    @ApiOperation("更新捐款记录")
    @PostMapping("/updateDonation")
    public ResultBody updateDonation(@RequestBody BusDonationRecord busDonationRecord, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        busDonationRecordService.updateDonation(busDonationRecord,userInfo);
        return ResultBody.newSuccessInstance("更新成功");
    }

    /**
     * 删除捐款记录
     */
    @ApiOperation("删除捐款记录")
    @GetMapping("/delDonation")
    public ResultBody delDonation(String ids){
        busDonationRecordService.delDonation(ids);
        return ResultBody.newSuccessInstance("删除成功");
    }

    /**
     * 新增兴趣小组记录
     */
    @ApiOperation("新增兴趣小组记录")
    @PostMapping("/addInterestGroup")
    public ResultBody addInterestGroup(@RequestBody BusInterestGroupRecord busInterestGroupRecord, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        busInterestGroupRecordService.addInterestGroup(busInterestGroupRecord,userInfo);
        return ResultBody.newSuccessInstance("新增成功");
    }

    /**
     * 查询兴趣小组记录
     */
    @ApiOperation("查询兴趣小组记录")
    @PostMapping("/pageInterestGroup")
    public ResultBody<PageVO<BusInterestGroupRecordVO>> pageInterestGroup(@RequestBody QueryInterestGroupVO queryInterestGroupVO){
        return ResultBody.newSuccessInstance(busInterestGroupRecordService.pageInterestGroup(queryInterestGroupVO));
    }

    /**
     * 更新兴趣小组记录
     */
    @ApiOperation("更新兴趣小组记录")
    @PostMapping("/updateInterestGroup")
    public ResultBody updateInterestGroup(@RequestBody BusInterestGroupRecord busInterestGroupRecord, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        busInterestGroupRecordService.updateInterestGroup(busInterestGroupRecord,userInfo);
        return ResultBody.newSuccessInstance("更新成功");
    }

    /**
     * 删除兴趣小组记录
     */
    @ApiOperation("删除兴趣小组记录")
    @GetMapping("/delInterestGroup")
    public ResultBody delInterestGroup(String ids){
        busInterestGroupRecordService.delInterestGroup(ids);
        return ResultBody.newSuccessInstance("删除成功");
    }


    /**
     * 新增投诉记录
     */
    @ApiOperation("新增投诉记录")
    @PostMapping("/addComplaint")
    public ResultBody addComplaint(@RequestBody BusComplaintsRecord busComplaintsRecord, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        busComplaintsRecordService.addComplaint(busComplaintsRecord,userInfo);
        return ResultBody.newSuccessInstance("新增成功");
    }

    /**
     * 查询投诉记录（根据主题）
     *
     */
    @ApiOperation("查询投诉记录")
    @PostMapping("/pageComplaint")
    public ResultBody<PageVO<BusComplaintsRecordVO>> pageComplaint(@RequestBody QueryComplaintVO queryComplaintVO){
        return ResultBody.newSuccessInstance(busComplaintsRecordService.pageComplaint(queryComplaintVO));
    }

    /**
     * 更新兴趣小组记录
     */
    @ApiOperation("更新投诉记录")
    @PostMapping("/updateComplaint")
    public ResultBody updateComplaint(@RequestBody BusComplaintsRecord busComplaintsRecord, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        busComplaintsRecordService.updateComplaint(busComplaintsRecord,userInfo);
        return ResultBody.newSuccessInstance("更新成功");
    }

    /**
     * 删除兴趣小组记录
     */
    @ApiOperation("删除投诉记录")
    @GetMapping("/delComplaint")
    public ResultBody delComplaint(String ids){
        busComplaintsRecordService.delComplaint(ids);
        return ResultBody.newSuccessInstance("删除成功");
    }

    /**
     * 新增护工考核
     */
    @ApiOperation("新增护工考核")
    @PostMapping("/addCheck")
    public ResultBody addCheck(@RequestBody BusCarerCheck busCarerCheck, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        busCarerCheckService.addCheck(busCarerCheck,userInfo);
        return ResultBody.newSuccessInstance("新增成功");
    }

    /**
     * 查询护工考核（根据姓名时间）
     *
     */
    @ApiOperation("查询护工考核（根据姓名时间）")
    @PostMapping("/pageCheck")
    public ResultBody<PageVO<BusCarerCheckVO>> pageCheck(@RequestBody QueryCheckVO queryCheckVO){
        return ResultBody.newSuccessInstance(busCarerCheckService.pageCheck(queryCheckVO));
    }

    /**
     * 更新护工考核记录
     */
    @ApiOperation("更新护工考核记录")
    @PostMapping("/updateCheck")
    public ResultBody updateCheck(@RequestBody BusCarerCheck busCarerCheck, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        busCarerCheckService.updateCheck(busCarerCheck,userInfo);
        return ResultBody.newSuccessInstance("更新成功");
    }

    /**
     * 删除护工考核记录
     */
    @ApiOperation("删除护工考核记录")
    @GetMapping("/delCheck")
    public ResultBody delCheck(String ids){
        busCarerCheckService.delCheck(ids);
        return ResultBody.newSuccessInstance("删除成功");
    }

}
