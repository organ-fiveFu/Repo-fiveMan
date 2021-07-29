package com.nurse.healthy.controller.dict;

import com.nurse.healthy.annoation.CurrentUser;
import com.nurse.healthy.model.entity.sys.SysDictType;
import com.nurse.healthy.model.vo.base.PullDownVo;
import com.nurse.healthy.result.UserInfoToken;
import com.nurse.healthy.service.SysDictService;
import com.nurse.healthy.vo.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dictType")
@Api(tags = "字典类型")
@Slf4j
public class DictTypeController {
    @Resource
    private SysDictService sysDictService;


    @ApiOperation("新增字典")
    @PostMapping("/add")
    public ResultBody add(@RequestBody SysDictType sysDictType, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        log.info("新增字典内容： "+sysDictType);
        sysDictService.add(sysDictType,userInfo);
        return ResultBody.newSuccessInstance();
    }

    @ApiOperation("修改字典")
    @PostMapping("/update")
    public ResultBody update(@RequestBody SysDictType sysDictType,@ApiIgnore @CurrentUser UserInfoToken userInfo){
        log.info("修改内容： "+sysDictType);
        sysDictService.update(sysDictType,userInfo);
        return ResultBody.newSuccessInstance();
    }

    @ApiOperation("查询字典")
    @GetMapping("/select")
    public ResultBody select(String keyWord){
        return ResultBody.newSuccessInstance(sysDictService.select(keyWord));
    }


    @ApiOperation("删除字典")
    @GetMapping("/del")
    public ResultBody del(String typeCodes) {
        sysDictService.del(typeCodes);
        return ResultBody.newSuccessInstance();
    }
    @ApiOperation("查询字典明细下拉")
    @PostMapping("/selectPullDown")
    public ResultBody<Map<String,List<PullDownVo>>> selectPullDown(@RequestBody List<String> dictTypeCodes){
        return ResultBody.newSuccessInstance(sysDictService.selectPullDown(dictTypeCodes));
    }

}
