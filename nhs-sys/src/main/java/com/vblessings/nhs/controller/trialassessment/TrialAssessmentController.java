package com.vblessings.nhs.controller.trialassessment;

import com.alibaba.fastjson.JSON;
import com.vblessings.nhs.annoation.CurrentUser;
import com.vblessings.nhs.model.po.trialassessment.TrialAssessmentInsertPO;
import com.vblessings.nhs.model.po.trialassessment.TrialAssessmentUpdatePO;
import com.vblessings.nhs.model.result.ResultBody;
import com.vblessings.nhs.model.vo.trialassessment.TrialAssessmentQueryVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.TrialAssessmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/trial-assessment")
@Api(tags = "试用期评估")
@Slf4j
public class TrialAssessmentController {

    @Resource
    private TrialAssessmentService trialAssessmentService;

    /**
     * 新增试用期评估信息
     * @author linxiazhu
     * @date 15:29 2021/7/19
     * @param trialAssessmentInsertPO  入参
     * @param userInfoToken   token
     * @return  com.nurse.healthy.vo.ResultBody<java.lang.Boolean>
     */
    @ApiOperation(value = "新增试用期评估信息")
    @PostMapping("/insert")
    public ResultBody<Boolean> insertTrialAssessment(@RequestBody @Valid TrialAssessmentInsertPO trialAssessmentInsertPO, @CurrentUser @ApiIgnore UserInfoToken userInfoToken){
        log.info("新增试用期评估信息,入参trialAssessmentInsertPO:{}", JSON.toJSONString(trialAssessmentInsertPO));
        return ResultBody.newSuccessInstance(trialAssessmentService.insertTrialAssessment(trialAssessmentInsertPO, userInfoToken));
    }

    /**
     * 更新试用期评估信息
     * @author linxiazhu
     * @date 15:56 2021/7/19
     * @param trialAssessmentUpdatePO 入参
     * @param userInfoToken token
     * @return  com.nurse.healthy.vo.ResultBody<java.lang.Boolean>
     */
    @ApiOperation(value = "更新试用期评估信息")
    @PostMapping("/update")
    public ResultBody<Boolean> updateTrialAssessment(@RequestBody @Valid TrialAssessmentUpdatePO trialAssessmentUpdatePO, @CurrentUser @ApiIgnore UserInfoToken userInfoToken){
        log.info("更新试用期评估信息,入参trialAssessmentUpdatePO:{}", JSON.toJSONString(trialAssessmentUpdatePO));
        return ResultBody.newSuccessInstance(trialAssessmentService.updateTrialAssessment(trialAssessmentUpdatePO, userInfoToken));
    }

    /**
     * 查询试用期评估信息
     * @author linxiazhu
     * @date 16:58 2021/7/19
     * @param businessNo   老人档案id
     * @return  com.nurse.healthy.vo.ResultBody<com.nurse.healthy.model.vo.trialassessment.TrialAssessmentQueryVO>
     */
    @ApiModelProperty(value = "查询试用期评估信息")
    @GetMapping("/query")
    public ResultBody<TrialAssessmentQueryVO> queryTrialAssessment(String businessNo){
        log.info("查询试用期评估信息,businessNo:" + businessNo);
        return ResultBody.newSuccessInstance(trialAssessmentService.queryTrialAssessment(businessNo));
    }

    /**
     * 删除试用期评估信息
     * @author linxiazhu
     * @date 17:16 2021/7/19
     * @param businessNo  老人档案id
     * @param userInfoToken   token
     * @return  com.nurse.healthy.vo.ResultBody<com.nurse.healthy.model.vo.trialassessment.TrialAssessmentQueryVO>
     */
    @ApiOperation("删除试用期评估信息")
    @GetMapping("/delete")
    public ResultBody<Boolean> deleteTrialAssessment(String businessNo, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("删除试用期评估信息,businessNo:"+ businessNo);
        return ResultBody.newSuccessInstance(trialAssessmentService.deleteTrialAssessment(businessNo, userInfoToken));
    }
}
