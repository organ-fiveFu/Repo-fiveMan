package com.vblessings.nhs.controller;

import com.vblessings.nhs.model.result.ResultBody;
import com.vblessings.nhs.model.result.ResultInfoEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *汇总统计api
 */
@RestController
@RequestMapping("/summary")
@Api(tags = "汇总统计")
@Slf4j
public class TotalSummaryController {

    @ApiOperation("查询汇总信息")
    @PostMapping("/select")
    public ResultBody selectTotal(@RequestBody Object obj){
     log.info(obj.toString());
     return ResultBody.newSuccessInstance(ResultInfoEnum.SUCCESS);
    }
}
