package com.nurse.healthy.service;

import com.nurse.healthy.model.entity.sys.SysDictData;
import com.nurse.healthy.model.po.QueryDictPO;
import com.nurse.healthy.result.UserInfoToken;

import java.util.List;
import java.util.Map;

public interface SysDictDataService {

    void add(SysDictData sysDictData, UserInfoToken userInfo);

    void update(SysDictData sysDictData, UserInfoToken userInfo);

    List<SysDictData> select(QueryDictPO queryDictPO);

    void del(List<String> dictCodes);

    /**
     * 根据code获取name
     * @param dictTypeCode
     * @param dictCodeList
     * @return
     */
    Map<String,String> getDictName(String dictTypeCode,List<String> dictCodeList);
}
