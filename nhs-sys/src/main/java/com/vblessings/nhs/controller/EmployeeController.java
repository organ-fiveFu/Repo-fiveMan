package com.vblessings.nhs.controller;

import com.vblessings.nhs.annoation.CurrentUser;
import com.vblessings.nhs.model.entity.sys.SysEmployeeInfo;
import com.vblessings.nhs.model.po.QueryEmployeePO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.SysEmployeeInfoService;
import com.vblessings.nhs.model.result.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

@RestController
@RequestMapping("/employee")
@Api(tags = "员工信息")
@Slf4j
public class EmployeeController {
    @Resource
    private SysEmployeeInfoService sysEmployeeInfoService;


    /**
     * 新增员工
     */
    @ApiOperation("新增员工")
    @PostMapping("/add")
    public ResultBody add(@RequestBody SysEmployeeInfo sysCarerInfo, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        sysEmployeeInfoService.add(sysCarerInfo,userInfo);
        return ResultBody.newSuccessInstance();
    }

    /**
     * 更新员工信息
     * @param sysEmployeeInfo
     * @param userInfo
     * @return
     */
    @ApiOperation("修改员工信息")
    @PostMapping("/update")
    public ResultBody update(@RequestBody SysEmployeeInfo sysEmployeeInfo, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        log.info("修改内容： "+sysEmployeeInfo);
        sysEmployeeInfoService.update(sysEmployeeInfo,userInfo);
        return ResultBody.newSuccessInstance();
    }

    @ApiOperation("查询员工信息")
    @PostMapping("/select")
    public ResultBody<PageVO<SysEmployeeInfo>> select(@RequestBody QueryEmployeePO queryEmployeePO){
        return ResultBody.newSuccessInstance(sysEmployeeInfoService.select(queryEmployeePO));
    }


    @ApiOperation("删除员工信息")
    @GetMapping("/del")
    public ResultBody del(String ids) {
        sysEmployeeInfoService.del(ids);
        return ResultBody.newSuccessInstance();
    }

    @ApiOperation("重置密码")
    @GetMapping("/resetPassword")
    public ResultBody resetPassword(String employeeCode){
        sysEmployeeInfoService.resetPassword(employeeCode);
        return ResultBody.newSuccessInstance();
    }

    @ApiOperation("停用账号")
    @GetMapping("/stop")
    public ResultBody stop(String employeeCode){
        sysEmployeeInfoService.stop(employeeCode);
        return ResultBody.newSuccessInstance();
    }

    @ApiOperation("启用账号")
    @GetMapping("/start")
    public ResultBody start(String employeeCode){
        sysEmployeeInfoService.start(employeeCode);
        return ResultBody.newSuccessInstance();
    }



}
