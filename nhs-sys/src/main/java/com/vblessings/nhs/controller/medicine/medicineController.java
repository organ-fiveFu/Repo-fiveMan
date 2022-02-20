package com.vblessings.nhs.controller.medicine;

import com.vblessings.nhs.annoation.CurrentUser;
import com.vblessings.nhs.annoation.IgnoreUserToken;
import com.vblessings.nhs.model.entity.business.BusMedicationRecord;
import com.vblessings.nhs.model.entity.business.BusTakeMedicineRecord;
import com.vblessings.nhs.model.po.TimeQueryPO;
import com.vblessings.nhs.model.po.business.QueryMedicineRecordPO;
import com.vblessings.nhs.model.po.business.QueryTakeMedicineRecord;
import com.vblessings.nhs.model.result.ResultBody;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.ReportData;
import com.vblessings.nhs.model.vo.bed.SysBuildingInfoQueryVO;
import com.vblessings.nhs.model.vo.business.MedicineRecordReportQueryVO;
import com.vblessings.nhs.model.vo.medicine.TakeMedicineQueryVO;
import com.vblessings.nhs.model.vo.ReportData;
import com.vblessings.nhs.model.vo.business.TakeMedicineReportQueryVO;
import com.vblessings.nhs.model.vo.nurse.BloodSugarReportQueryVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusMedicationRecordService;
import com.vblessings.nhs.service.BusTakeMedicineRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 药物相关api
 */
@RestController
@RequestMapping("/medicine")
@Api(tags = "药物")
@Slf4j
public class medicineController {

    @Resource
    private BusTakeMedicineRecordService busTakeMedicineRecordService;

    @Resource
    private BusMedicationRecordService busMedicationRecordService;

    @ApiOperation("新增带药记录")
    @PostMapping("/addTakeMedicine")
    public ResultBody addTakeMedicine(@RequestBody BusTakeMedicineRecord busTakeMedicineRecord, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        log.info("新增带药记录： "+busTakeMedicineRecord);
        busTakeMedicineRecordService.add(busTakeMedicineRecord,userInfo);
        return ResultBody.newSuccessInstance();
    }

    @ApiOperation("更新带药记录")
    @PostMapping("/updateTakeMedicine")
    public ResultBody updateTakeMedicine(@RequestBody BusTakeMedicineRecord busTakeMedicineRecord, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        log.info("更新带药记录： "+busTakeMedicineRecord);
        busTakeMedicineRecordService.update(busTakeMedicineRecord,userInfo);
        return ResultBody.newSuccessInstance();
    }

    /**
     * 查询带药记录
     */
    @ApiOperation("查询带药记录")
    @PostMapping("/pageTakeMedicine")
    public ResultBody<PageVO<BusTakeMedicineRecord>> pageTakeMedicine(@RequestBody QueryTakeMedicineRecord queryTakeMedicineRecord){
        return ResultBody.newSuccessInstance(busTakeMedicineRecordService.pageTakeMedicine(queryTakeMedicineRecord));
    }

    @ApiOperation("删除带药记录")
    @GetMapping("/del")
    public ResultBody delTakeMedicine(String ids) {
        busTakeMedicineRecordService.delTakeMedicine(ids);
        return ResultBody.newSuccessInstance();
    }

    @ApiOperation("导出自带药记录")
    @GetMapping("/exportTakeMedicine")
    public void exportTakeMedicine(String  ids, HttpServletResponse response) throws IOException {
        busTakeMedicineRecordService.exportTakeMedicine(ids,response);
    }

    @IgnoreUserToken
    @GetMapping("/queryTakeMedicine/no-token")
    @ApiOperation(value = "带药记录报表-notoken")
    public ReportData<List<TakeMedicineReportQueryVO>> queryTakeMedicineNoToken(TimeQueryPO timeQueryPO){
        List<TakeMedicineReportQueryVO> takeMedicineReportQueryVOS = busTakeMedicineRecordService.queryTakeMedicineNoToken(timeQueryPO);
        return ReportData.data(takeMedicineReportQueryVOS);
    }

    @ApiOperation("导出代配药记录")
    @GetMapping("/exportDispensing")
    public void exportDispensing(String  ids, HttpServletResponse response) throws IOException {
        busTakeMedicineRecordService.exportDispensing(ids,response);
    }

    @ApiOperation("新增用药记录")
    @PostMapping("/addMedicationRecord")
    public ResultBody addMedicationRecordS(@RequestBody BusMedicationRecord busMedicationRecord, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        log.info("新增用药记录： "+busMedicationRecord);
        busMedicationRecordService.add(busMedicationRecord,userInfo);
        return ResultBody.newSuccessInstance();
    }

    @ApiOperation("更新用药记录")
    @PostMapping("/updateMedicationRecord")
    public ResultBody updateMedicationRecord(@RequestBody BusMedicationRecord busMedicationRecord, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        log.info("更新用药记录： "+busMedicationRecord);
        busMedicationRecordService.update(busMedicationRecord,userInfo);
        return ResultBody.newSuccessInstance();
    }

    /**
     * 查询带药记录
     */
    @ApiOperation("查询用药记录")
    @PostMapping("/pageMedicationRecord")
    public ResultBody<PageVO<BusMedicationRecord>> pageMedicationRecord(@RequestBody QueryMedicineRecordPO queryMedicineRecordPO){
        return ResultBody.newSuccessInstance(busMedicationRecordService.pageMedicationRecord(queryMedicineRecordPO));
    }
    @ApiOperation("删除用药记录")
    @GetMapping("/delMedicationRecord")
    public ResultBody delMedicationRecord(String ids) {
        busMedicationRecordService.delMedicationRecord(ids);
        return ResultBody.newSuccessInstance();
    }

    @ApiOperation("导出用药记录")
    @GetMapping("/exportMedicationRecord")
    public void exportMedicationRecord(String  ids, HttpServletResponse response) throws IOException {
        busMedicationRecordService.exportMedicationRecord(ids,response);
    }

    @IgnoreUserToken
    @GetMapping("/get-takeMedicationList/no-token")
    @ApiOperation(value = "代配药导出-notoken")
    public ReportData<List<TakeMedicineReportQueryVO>> queryTakeMedicationListGetListNoToken(TimeQueryPO timeQueryPO){
        List<TakeMedicineReportQueryVO> takeMedicineQueryVOS = busTakeMedicineRecordService.queryTakeMedicationListGetListNoToken(timeQueryPO);
        return ReportData.data(takeMedicineQueryVOS);
    }


    @IgnoreUserToken
    @GetMapping("/get-MedicationRecordList/no-token")
    @ApiOperation(value = "用药导出-notoken")
    public ReportData<List<MedicineRecordReportQueryVO>> queryMedicationRecordListGetListNoToken(TimeQueryPO timeQueryPO){
        List<MedicineRecordReportQueryVO> medicineRecordReportQueryVOS = busMedicationRecordService.queryMedicationRecordListGetListNoToken(timeQueryPO);
        return ReportData.data(medicineRecordReportQueryVOS);
    }




}
