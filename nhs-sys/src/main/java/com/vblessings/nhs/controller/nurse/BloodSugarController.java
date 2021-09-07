package com.vblessings.nhs.controller.nurse;

import com.alibaba.fastjson.JSON;
import com.vblessings.nhs.annoation.CurrentUser;
import com.vblessings.nhs.model.po.nurse.BloodSugarInsertPO;
import com.vblessings.nhs.model.po.nurse.BloodSugarQueryPO;
import com.vblessings.nhs.model.po.nurse.BloodSugarUpdatePO;
import com.vblessings.nhs.model.result.ResultBody;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.nurse.BloodSugarQueryVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BloodSugarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/blood-sugar")
@Api(tags = "血糖记录")
@Slf4j
public class BloodSugarController {

    @Resource
    private BloodSugarService bloodSugarService;

    @PostMapping("/insert")
    @ApiOperation(value = "新增血糖记录")
    public ResultBody<Boolean> insertBloodSugar(@RequestBody BloodSugarInsertPO bloodSugarInsertPO, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("新增血糖记录,入参bloodSugarInsertPO:{}", JSON.toJSONString(bloodSugarInsertPO));
        return ResultBody.newSuccessInstance(bloodSugarService.insertBloodSugar(bloodSugarInsertPO, userInfoToken));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新血糖记录")
    public ResultBody<Boolean> updateBloodSugar(@RequestBody BloodSugarUpdatePO bloodSugarUpdatePO, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("更新血糖记录,入参bloodSugarUpdatePO:{}", JSON.toJSONString(bloodSugarUpdatePO));
        return ResultBody.newSuccessInstance(bloodSugarService.updateBloodSugar(bloodSugarUpdatePO, userInfoToken));
    }

    @PostMapping("/query")
    @ApiOperation(value = "查询血糖记录")
    public ResultBody<PageVO<BloodSugarQueryVO>> queryBloodSugar(@RequestBody BloodSugarQueryPO bloodSugarQueryPO){
        log.info("查询血糖记录,入参bloodSugarQueryPO:{}", JSON.toJSONString(bloodSugarQueryPO));
        return ResultBody.newSuccessInstance(bloodSugarService.queryBloodSugar(bloodSugarQueryPO));
    }

    @GetMapping("/delete")
    @ApiOperation(value = "删除血糖记录")
    public ResultBody<Boolean> deleteBloodSugar(Long id, @ApiIgnore @CurrentUser UserInfoToken userInfoToken){
        log.info("删除血糖记录,id:" + id);
        return ResultBody.newSuccessInstance(bloodSugarService.deleteBloodSugar(id, userInfoToken));
    }

    @ApiOperation("导出血糖记录")
    @GetMapping("/export")
    public void exportBloodSugar(String  ids, HttpServletResponse response) throws IOException {
        bloodSugarService.exportBloodSugar(ids,response);
    }
}
