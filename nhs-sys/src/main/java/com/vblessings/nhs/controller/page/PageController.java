package com.vblessings.nhs.controller.page;

import com.alibaba.fastjson.JSON;
import com.vblessings.nhs.annoation.CurrentUser;
import com.vblessings.nhs.model.po.page.PageQueryPO;
import com.vblessings.nhs.model.po.page.PatientQueryPO;
import com.vblessings.nhs.model.result.ResultBody;
import com.vblessings.nhs.model.vo.page.BedPageQueryVO;
import com.vblessings.nhs.model.vo.page.PageQueryVO;
import com.vblessings.nhs.model.vo.page.PatientQueryVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.PageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/page")
@Api(tags = "首页")
@Slf4j
public class PageController {

    @Resource
    private PageService pageService;

    @GetMapping("/bed/query")
    @ApiOperation(value = "左侧列表查询")
    public ResultBody<BedPageQueryVO> queryBedPage(PageQueryPO pageQueryPO){
        log.info("左侧列表查询,入参pageQueryPO:{}", JSON.toJSONString(pageQueryPO));
        return ResultBody.newSuccessInstance(pageService.queryBedPage(pageQueryPO));
    }

    @GetMapping("/query")
    @ApiOperation(value = "首页查询")
    public ResultBody<List<PageQueryVO>> queryPageList(PageQueryPO pageQueryPO, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("首页查询,入参pageQueryPO:{}", JSON.toJSONString(pageQueryPO));
        return ResultBody.newSuccessInstance(pageService.queryPageList(pageQueryPO, userInfoToken));
    }

    @GetMapping("/patient/query")
    @ApiOperation(value = "病人基础信息查询")
    public ResultBody<List<PatientQueryVO>> queryPatientList(PatientQueryPO patientQueryPO, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("病人基础信息查询,入参patientQueryPO:{}", JSON.toJSONString(patientQueryPO));
        return ResultBody.newSuccessInstance(pageService.queryPatientList(patientQueryPO, userInfoToken));
    }
}
