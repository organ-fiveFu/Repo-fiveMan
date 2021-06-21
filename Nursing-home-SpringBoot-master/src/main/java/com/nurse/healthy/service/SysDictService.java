package com.nurse.healthy.service;

import com.nurse.healthy.model.entity.sys.SysDictType;

import java.util.List;

public interface SysDictService {
    /**
     * 新增字典大类
     * @param sysDictType
     */
    void add(SysDictType sysDictType);

    /**
     * 修改字典大类
     * @param sysDictType
     */
    void update(SysDictType sysDictType);

    /**
     * 查字典大类
     * @param dictTypeCode
     * @return
     */
    List<SysDictType> select(String dictTypeCode);

    /**
     * 删除字典大类
     * @param dictTypeCode
     */
    void del(String dictTypeCode);
}
