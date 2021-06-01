package com.nurse.healthy.controller;

import com.nurse.healthy.model.entity.Archive;
import com.nurse.healthy.service.ArchiveService;
import com.nurse.healthy.vo.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 老人档案api
 */
@RestController
@RequestMapping("/archive")
@Api(tags = "老人档案")
@Slf4j
public class ArchiveController {

    @Resource
    private ArchiveService archiveService;
    /**
     * 查询老人基础档案
     */
    @ApiOperation("老人基本档案分页")
    @PostMapping("/baseArchive")
    public ResultBody<Archive> baseArchivePage(Integer id){
    return archiveService.selectInfo();
    }

    /**
     * 查询老人基础档案
     */
    @ApiOperation("老人体检档案分页")
    @PostMapping("/examinationArchive")
    public ResultBody examinationArchivePage(@RequestBody Object obj){
        return null;
    }




}
