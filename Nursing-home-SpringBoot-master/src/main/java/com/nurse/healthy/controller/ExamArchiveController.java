package com.nurse.healthy.controller;


import com.nurse.healthy.annoation.CurrentUser;
import com.nurse.healthy.model.po.ExamArchivePO;
import com.nurse.healthy.result.UserInfoToken;
import com.nurse.healthy.service.BusExamArchiveService;
import com.nurse.healthy.vo.ResultBody;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * 体检档案
 */
@Slf4j
@RestController
@RequestMapping("/examArchive")
public class ExamArchiveController {

    @Resource
    private BusExamArchiveService busExamArchiveService;

    /**
     * 新增体检档案
     */
    @ApiOperation("新增体检档案")
    @PostMapping("/save")
    public ResultBody add(@RequestBody ExamArchivePO examArchivePO, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        log.info("体检档案入参："+examArchivePO);
        busExamArchiveService.save(examArchivePO,userInfo);
        return ResultBody.newSuccessInstance();
    }


    /**
     * 查询体检档案
     */
    @ApiOperation("查询体检档案")
    @PostMapping("/select")
    public ResultBody<List<ExamArchivePO>> select(String businessNo){
        log.info("查询体检档案入参："+businessNo);
        return ResultBody.newSuccessInstance(busExamArchiveService.select(businessNo));
    }

}
