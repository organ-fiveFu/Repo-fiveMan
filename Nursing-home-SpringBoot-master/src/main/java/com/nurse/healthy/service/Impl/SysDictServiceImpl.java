package com.nurse.healthy.service.Impl;

import com.nurse.healthy.model.entity.sys.SysDictType;
import com.nurse.healthy.service.SysDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SysDictServiceImpl implements SysDictService {


    @Override
    public void add(SysDictType sysDictType) {

    }

    @Override
    public void update(SysDictType sysDictType) {

    }

    @Override
    public List<SysDictType> select(String dictTypeCode) {
        return null;
    }

    @Override
    public void del(String dictTypeCode) {

    }

}
