package com.nurse.healthy.controller.dict;


import com.nurse.healthy.service.SysDictDataService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dictDate")
@Api(tags = "字典明细")
@Slf4j
public class DictDataController {

    @Resource
    private SysDictDataService sysDictDataService;

}
