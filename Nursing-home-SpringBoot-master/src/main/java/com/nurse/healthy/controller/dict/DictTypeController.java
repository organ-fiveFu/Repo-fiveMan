package com.nurse.healthy.controller.dict;

import com.nurse.healthy.model.entity.sys.SysDictType;
import com.nurse.healthy.service.SysDictService;
import com.nurse.healthy.vo.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dictType")
@Api(tags = "字典类型")
@Slf4j
public class DictTypeController {
    @Resource
    private SysDictService sysDictService;


    @ApiOperation("新增字典")
    @PostMapping("/add")
    public ResultBody add(@RequestBody SysDictType sysDictType){
        log.info("新增字典内容： "+sysDictType);
        sysDictService.add(sysDictType);
        return ResultBody.newSuccessInstance();
    }

    @ApiOperation("修改字典")
    @PostMapping("/update")
    public ResultBody update(@RequestBody SysDictType sysDictType){
        log.info("修改内容： "+sysDictType);
        sysDictService.update(sysDictType);
        return ResultBody.newSuccessInstance();
    }

    @ApiOperation("查询字典")
    @PostMapping("/select")
    public ResultBody select(String dictTypeCode){
        log.info("字典类型code： "+dictTypeCode);
        return ResultBody.newSuccessInstance(sysDictService.select(dictTypeCode));
    }


    @ApiOperation("删除字典")
    @PostMapping("/del")
    public ResultBody del(String dictTypeCode) {
        sysDictService.del(dictTypeCode);
        return ResultBody.newSuccessInstance();
    }

}
