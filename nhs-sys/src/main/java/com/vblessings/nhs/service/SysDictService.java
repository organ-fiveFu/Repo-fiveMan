package com.vblessings.nhs.service;

import com.vblessings.nhs.model.entity.sys.SysDictType;
import com.vblessings.nhs.model.vo.base.PullDownVo;
import com.vblessings.nhs.result.UserInfoToken;

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

    Map<String,List<PullDownVo>> selectPullDown(List<String> dictTypeCodes);

}
