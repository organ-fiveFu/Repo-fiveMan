package com.vblessings.nhs.controller.dict;


import com.vblessings.nhs.annoation.CurrentUser;
import com.vblessings.nhs.model.entity.sys.SysDictData;
import com.vblessings.nhs.model.po.QueryDictPO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.SysDictDataService;
import com.vblessings.nhs.model.result.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dictDate")
@Api(tags = "字典明细")
@Slf4j
public class DictDataController {

    @Resource
    private SysDictDataService sysDictDataService;

    @ApiOperation("新增字典明细")
    @PostMapping("/add")
    public ResultBody add(@RequestBody SysDictData sysDictData, @ApiIgnore @CurrentUser UserInfoToken userInfo){
        log.info("新增字典明细： "+sysDictData);
        sysDictDataService.add(sysDictData,userInfo);
        return ResultBody.newSuccessInstance();
    }

    @ApiOperation("修改字典明细")
    @PostMapping("/update")
    public ResultBody update(@RequestBody SysDictData sysDictData,@ApiIgnore @CurrentUser UserInfoToken userInfo){
        log.info("修改内容： "+sysDictData);
        sysDictDataService.update(sysDictData,userInfo);
        return ResultBody.newSuccessInstance();
    }

    @ApiOperation("查询字典明细")
    @PostMapping("/select")
    public ResultBody<PageVO<SysDictData>> select(@RequestBody QueryDictPO queryDictPO){
        return ResultBody.newSuccessInstance(sysDictDataService.select(queryDictPO));
    }

    @ApiOperation("根据字典code删除字典")
    @GetMapping("/del")
    public ResultBody del(String dictCodes) {
        sysDictDataService.del(dictCodes);
        return ResultBody.newSuccessInstance();
    }

}
