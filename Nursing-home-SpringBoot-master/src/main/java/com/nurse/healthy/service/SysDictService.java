package com.nurse.healthy.service;

import com.nurse.healthy.model.entity.sys.SysDictType;
import com.nurse.healthy.result.UserInfoToken;

import java.util.List;
import java.util.Map;

public interface SysDictService {
    /**
     * 新增字典大类
     * @param sysDictType
     */
    void add(SysDictType sysDictType, UserInfoToken userInfo);

    /**
     * 修改字典大类
     * @param sysDictType
     */
    void update(SysDictType sysDictType,UserInfoToken userInfo);

    /**
     * 查字典大类
     * @param
     * @return
     */
    List<SysDictType> select(String keyWord);

    /**
     * 删除字典大类
     * @param typeCodes
     */
    void del(String typeCodes);


}
