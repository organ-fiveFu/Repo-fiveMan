package com.nurse.healthy.controller;


import com.nurse.healthy.annoation.CurrentUser;
import com.nurse.healthy.model.po.business.ExamArchiveInsertPO;
import com.nurse.healthy.model.po.business.ExamArchiveUpdatePO;
import com.nurse.healthy.model.vo.business.ExamArchiveQueryVO;
import com.nurse.healthy.result.UserInfoToken;
import com.nurse.healthy.service.BusExamArchiveService;
import com.nurse.healthy.vo.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * 体检档案
 */
@Slf4j
@RestController
@RequestMapping("/examArchive")
@Api(tags = "体检档案")
public class ExamArchiveController {

    @Resource
    private BusExamArchiveService busExamArchiveService;

    /**
     * 新增体检档案
     */
    @ApiOperation("新增体检档案")
    @PostMapping("/save")
    public ResultBody<Boolean> add(@RequestBody ExamArchiveInsertPO examArchiveInsertPO, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        log.info("体检档案入参："+ examArchiveInsertPO);
        return ResultBody.newSuccessInstance(busExamArchiveService.save(examArchiveInsertPO,userInfo));
    }

    /**
     * 更新体检档案
     * @author linxiazhu
     * @date 15:30 2021/7/6
     * @param examArchiveUpdatePO  入参
     * @param userInfoToken   token
     * @return  com.nurse.healthy.vo.ResultBody<java.lang.Boolean>
     */
    @ApiOperation("更新体检档案")
    @PostMapping("/update")
    public ResultBody<Boolean> update(@RequestBody ExamArchiveUpdatePO examArchiveUpdatePO, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("更新体检档案入参："+ examArchiveUpdatePO);
        return ResultBody.newSuccessInstance(busExamArchiveService.update(examArchiveUpdatePO,userInfoToken));
    }


    /**
     * 查询体检档案
     */
    @ApiOperation("查询体检档案")
    @GetMapping("/select")
    public ResultBody<ExamArchiveQueryVO> select(String archiveId){
        log.info("查询体检档案入参："+ archiveId);
        return ResultBody.newSuccessInstance(busExamArchiveService.select(archiveId));
    }

}
