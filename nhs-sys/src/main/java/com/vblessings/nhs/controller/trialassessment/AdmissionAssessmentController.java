package com.vblessings.nhs.controller.trialassessment;

import com.alibaba.fastjson.JSON;
import com.vblessings.nhs.annoation.CurrentUser;
import com.vblessings.nhs.model.po.trialassessment.AdmissionAssessmentInsertPO;
import com.vblessings.nhs.model.po.trialassessment.AdmissionAssessmentUpdatePO;
import com.vblessings.nhs.model.result.ResultBody;
import com.vblessings.nhs.model.vo.trialassessment.AdmissionAssessmentQueryVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.AdmissionAssessmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admission-assessment")
@Api(tags = "入院评估")
@Slf4j
public class AdmissionAssessmentController {

    @Resource
    private AdmissionAssessmentService admissionAssessmentService;

    @PostMapping("/insert")
    @ApiOperation(value = "新增入院评估")
    public ResultBody<Boolean> insertAdmissionAssessment(@RequestBody AdmissionAssessmentInsertPO admissionAssessmentInsertPO, @CurrentUser @ApiIgnore UserInfoToken userInfoToken){
        log.info("新增入院评估,入参admissionAssessmentInsertPO:{}", JSON.toJSONString(admissionAssessmentInsertPO));
        return ResultBody.newSuccessInstance(admissionAssessmentService.insertAdmissionAssessment(admissionAssessmentInsertPO, userInfoToken));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新入院评估")
    public ResultBody<Boolean> updateAdmissionAssessment(@RequestBody AdmissionAssessmentUpdatePO admissionAssessmentUpdatePO, @CurrentUser @ApiIgnore UserInfoToken userInfoToken){
        log.info("更新入院评估,入参admissionAssessmentUpdatePO:{}", JSON.toJSONString(admissionAssessmentUpdatePO));
        return ResultBody.newSuccessInstance(admissionAssessmentService.updateAdmissionAssessment(admissionAssessmentUpdatePO, userInfoToken));
    }

    @GetMapping("/query")
    @ApiOperation(value = "查询入院评估")
    public ResultBody<AdmissionAssessmentQueryVO> queryAdmissionAssessment(String businessNo){
        log.info("查询入院评估,businessNo:" + businessNo);
        return ResultBody.newSuccessInstance(admissionAssessmentService.queryAdmissionAssessment(businessNo));
    }

    @GetMapping("/delete")
    @ApiOperation("删除入院评估")
    public ResultBody<Boolean> deleteAdmissionAssessment(String businessNo, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("删除入院评估,businessNo:"+ businessNo);
        return ResultBody.newSuccessInstance(admissionAssessmentService.deleteAdmissionAssessment(businessNo, userInfoToken));
    }
}
