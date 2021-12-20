package com.vblessings.nhs.controller;

import com.vblessings.nhs.annoation.IgnoreUserToken;
import com.vblessings.nhs.model.po.QuerySourcePO;
import com.vblessings.nhs.model.po.QuerySummaryPO;
import com.vblessings.nhs.model.result.ResultBody;
import com.vblessings.nhs.model.po.QueryFigurePO;
import com.vblessings.nhs.model.vo.PeopleSourceVO;
import com.vblessings.nhs.model.vo.QuerySummaryVO;
import com.vblessings.nhs.model.vo.TempData;
import com.vblessings.nhs.service.BusHospitalRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 *汇总统计api
 */
@RestController
@RequestMapping("/summary")
@Api(tags = "汇总统计")
@Slf4j
public class TotalSummaryController {

    @Resource
    private BusHospitalRecordService busHospitalRecordService;

    @ApiOperation("查询汇总信息")
    @PostMapping("/select")
    @IgnoreUserToken
    public ResultBody<QuerySummaryVO> selectTotalByTime(@RequestBody QuerySummaryPO querySummaryPO){
     log.info("查询汇总信息:"+querySummaryPO);

     return ResultBody.newSuccessInstance(busHospitalRecordService.selectTotalByTime(querySummaryPO));
    }

    @ApiOperation("根据日期格式查询汇总信息(对应折线图)")
    @PostMapping("/queryBrokenLine")
    public ResultBody<Map<String, List<TempData>>> queryBrokenLine(@RequestBody QueryFigurePO queryFigurePO) throws ParseException {
        return ResultBody.newSuccessInstance(busHospitalRecordService.queryBrokenLine(queryFigurePO));

    }


    @ApiOperation("根据日期格式查询汇总信息(对应饼状图)")
    @PostMapping("/queryCake")
    public ResultBody<Map<String, List<TempData>>> queryCake(@RequestBody QueryFigurePO queryFigurePO) throws ParseException {
        return ResultBody.newSuccessInstance(busHospitalRecordService.queryCake(queryFigurePO));

    }

    @ApiOperation("根据日期格式查询人员来源和流向")
    @PostMapping("/querySource")
    public ResultBody<PeopleSourceVO> querySource(@RequestBody QuerySourcePO querySourcePO) {
        return ResultBody.newSuccessInstance(busHospitalRecordService.querySource(querySourcePO));
    }

}
